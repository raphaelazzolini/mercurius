package br.unicamp.ic.lsd.mercurius.persistence.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.lucene.search.Query;
import org.hibernate.Hibernate;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;

import br.unicamp.ic.lsd.mercurius.datatype.Product;
import br.unicamp.ic.lsd.mercurius.datatype.ProductAttribute;
import br.unicamp.ic.lsd.mercurius.datatype.ProductQuantity;
import br.unicamp.ic.lsd.mercurius.datatype.factory.ProductFactory;
import br.unicamp.ic.lsd.mercurius.datatype.factory.ProductQuantityFactory;
import br.unicamp.ic.lsd.mercurius.persistence.dao.ProductDAO;
import br.unicamp.ic.lsd.mercurius.persistence.dao.ProductImageDAO;
import br.unicamp.ic.lsd.mercurius.persistence.dao.ProductQuantityDAO;
import br.unicamp.ic.lsd.mercurius.persistence.entities.ProductImpl;
import br.unicamp.ic.lsd.mercurius.persistence.spec.req.PersistenceCacheMgt;
import br.unicamp.ic.lsd.mercurius.persistencecacheconnector.CacheConnectorComponentFactory;

import com.google.common.base.Strings;

@Stateless(name = "productDAO")
@Local(ProductDAO.class)
public class ProductDAOImpl extends AbstractDAO<Product, Integer> implements ProductDAO {

	private static final long serialVersionUID = 7210156543647887978L;

	@PersistenceContext
	private EntityManager em;

	@EJB
	private ProductQuantityDAO productQuantityDAO;

	@EJB
	private ProductImageDAO productImageDAO;

	@EJB
	private ProductFactory productFactory;

	@EJB
	private ProductQuantityFactory productQuantityFactory;

	private static Class<? extends Product> entityClass;

	@Override
	public Product newInstance() {
		return productFactory.createInstance();
	}

	@Override
	protected Class<? extends Product> getEntityClass() {
		if (entityClass == null) {
			Product entity = productFactory.createInstance();
			entityClass = entity.getClass();
		}
		return entityClass;
	}

	@Override
	public Product loadCategories(Product product) {
		PersistenceCacheMgt cacheMgt = (PersistenceCacheMgt) CacheConnectorComponentFactory.createInstance()
				.getProvidedInterface("PersistenceCacheMgt");
		Product cachedValue = null;
		if (cacheMgt != null) {
			cachedValue = cacheMgt.getFromProductWithCategoriesCache(product);
		}
		if (cachedValue == null) {
			cachedValue = findById(product.getId());
			Hibernate.initialize(cachedValue.getCategories());
			loadImagesFromQuantities(cachedValue.getQuantities());
			cacheMgt.putOnProductWithCategoriesCache(product, cachedValue);
		}
		return cachedValue;
	}

	@Override
	public ProductQuantity getProductQuantity(Product product, List<ProductAttribute> attributes) {
		product = findById(product.getId());
		Hibernate.initialize(product.getQuantities());
		for (ProductQuantity quantity : product.getQuantities()) {
			Hibernate.initialize(quantity.getProductsAttributes());
			if (quantity.getProductsAttributes().containsAll(attributes)) {
				return quantity;
			}
		}
		return null;
	}

	@Override
	public EntityManager getEntityManager() {
		return em;
	}

	@Override
	public List<Product> searchByText(String text) {
		PersistenceCacheMgt cacheMgt = (PersistenceCacheMgt) CacheConnectorComponentFactory.createInstance()
				.getProvidedInterface("PersistenceCacheMgt");
		List<Product> cachedValue = new ArrayList<Product>();
		if (cacheMgt != null) {
			cachedValue = cacheMgt.getFromProductSearchCache(text);
		}
		if (cachedValue == null) {
			Product productEntity = newInstance();
			FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(em);
			/*
			 * try { fullTextEntityManager.createIndexer().startAndWait(); }
			 * catch (InterruptedException e) { // TODO Auto-generated catch
			 * block e.printStackTrace(); }
			 */
			QueryBuilder qb = fullTextEntityManager.getSearchFactory().buildQueryBuilder()
					.forEntity(productEntity.getClass()).get();
			if (!Strings.isNullOrEmpty(text)) {
				Query query = qb.keyword().onField("name").andField("details").andField("technicalDetails")
						.matching(text).createQuery();

				javax.persistence.Query persistenceQuery = fullTextEntityManager.createFullTextQuery(query,
						productEntity.getClass());
				List<Product> result = persistenceQuery.getResultList();

				if (CollectionUtils.isNotEmpty(result)) {
					for (Product product : result) {
						loadImagesFromQuantities(product.getQuantities());
					}
					cachedValue = result;
					if (cacheMgt != null) {
						cacheMgt.putOnProductSearchCache(text, cachedValue);
					}
					return cachedValue;
				}
			}
		} else {
			return cachedValue;
		}
		return Collections.emptyList();
	}

	@Override
	public Product loadProductImages(Product product) {
		Product cachedValue = null;
		if (product != null) {
			PersistenceCacheMgt cacheMgt = (PersistenceCacheMgt) CacheConnectorComponentFactory.createInstance()
					.getProvidedInterface("PersistenceCacheMgt");

			if (cacheMgt != null) {
				cachedValue = findById(product.getId());
				loadImagesFromQuantities(cachedValue.getQuantities());
			}
			if (cacheMgt != null) {
				cacheMgt.putOnProductWithImagesCache(product, cachedValue);
			}
		}
		return cachedValue;
	}

	private void loadImagesFromQuantities(List<ProductQuantity> quantities) {
		if (CollectionUtils.isNotEmpty(quantities)) {
			for (ProductQuantity quantity : quantities) {
				Hibernate.initialize(quantity.getProductsAttributes());
				Hibernate.initialize(quantity.getProductImages());
			}
		}
	}

	@Override
	public Long getProductCount() {
		TypedQuery<Long> query = em.createQuery("select count(p.id) from Product p", Long.class);
		return query.getSingleResult();
	}

	@Override
	public void indexProductSearch() {
		FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(em);
		fullTextEntityManager.purgeAll(ProductImpl.class);
		fullTextEntityManager.createIndexer().start();
	}

	@Override
	public Collection<Product> getRandomProducts(Integer quantity) {
		TypedQuery<Product> query = em
				.createQuery(
						"select p from Product p where p.id >= (select floor((max(id) - :quantity + 1) * rand()) from Product)",
						Product.class);
		query.setParameter("quantity", quantity);
		query.setMaxResults(quantity);

		List<Product> result = query.getResultList();

		if (CollectionUtils.isNotEmpty(result)) {
			for (Product product : result) {
				loadImagesFromQuantities(product.getQuantities());
			}
		}
		return result;
	}

}
