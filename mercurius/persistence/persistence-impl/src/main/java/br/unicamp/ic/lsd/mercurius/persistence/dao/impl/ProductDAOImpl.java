package br.unicamp.ic.lsd.mercurius.persistence.dao.impl;

import java.util.Collections;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
import br.unicamp.ic.lsd.mercurius.persistence.dao.ProductQuantityDAO;

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
		product = findById(product.getId());
		Hibernate.initialize(product.getCategories());
		loadImagesFromQuantities(product.getQuantities());
		return product;
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
		Product productEntity = newInstance();
		FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(em);
		/*
		 * try { fullTextEntityManager.createIndexer().startAndWait(); } catch
		 * (InterruptedException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
		QueryBuilder qb = fullTextEntityManager.getSearchFactory().buildQueryBuilder()
				.forEntity(productEntity.getClass()).get();
		if (!Strings.isNullOrEmpty(text)) {
			Query query = qb.keyword().onField("name").andField("details").andField("technicalDetails").matching(text)
					.createQuery();

			javax.persistence.Query persistenceQuery = fullTextEntityManager.createFullTextQuery(query,
					productEntity.getClass());
			List<Product> result = persistenceQuery.getResultList();

			if (CollectionUtils.isNotEmpty(result)) {
				for (Product product : result) {
					loadImagesFromQuantities(product.getQuantities());
				}
			}
			return result;
		}
		return Collections.emptyList();
	}

	@Override
	public Product loadProductImages(Product product) {
		if (product != null) {
			product = findById(product.getId());
			loadImagesFromQuantities(product.getQuantities());
		}
		return product;
	}

	private void loadImagesFromQuantities(List<ProductQuantity> quantities) {
		if (CollectionUtils.isNotEmpty(quantities)) {
			for (ProductQuantity quantity : quantities) {
				Hibernate.initialize(quantity.getProductImages());
			}
		}
	}

}
