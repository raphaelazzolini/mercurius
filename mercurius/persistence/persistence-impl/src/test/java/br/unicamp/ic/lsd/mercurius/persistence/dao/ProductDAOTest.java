package br.unicamp.ic.lsd.mercurius.persistence.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.unicamp.ic.lsd.mercurius.datatype.Category;
import br.unicamp.ic.lsd.mercurius.datatype.Manufacturer;
import br.unicamp.ic.lsd.mercurius.datatype.Product;
import br.unicamp.ic.lsd.mercurius.datatype.ProductAttribute;
import br.unicamp.ic.lsd.mercurius.datatype.ProductOption;
import br.unicamp.ic.lsd.mercurius.datatype.ProductQuantity;
import br.unicamp.ic.lsd.mercurius.datatype.factory.CategoryFactory;
import br.unicamp.ic.lsd.mercurius.datatype.factory.ManufacturerFactory;
import br.unicamp.ic.lsd.mercurius.datatype.factory.ProductAttributeFactory;
import br.unicamp.ic.lsd.mercurius.datatype.factory.ProductOptionFactory;
import br.unicamp.ic.lsd.mercurius.datatype.factory.ProductQuantityFactory;
import br.unicamp.ic.lsd.mercurius.persistence.dao.CategoryDAO;
import br.unicamp.ic.lsd.mercurius.persistence.dao.ManufactoryDAO;
import br.unicamp.ic.lsd.mercurius.persistence.dao.ProductDAO;

public class ProductDAOTest extends DomainTestContext {

	@EJB
	private ProductDAO productDAO;

	@EJB
	private CategoryDAO categoryDAO;

	@EJB
	private ManufactoryDAO manufactoryDAO;

	@EJB
	private CategoryFactory categoryFactory;

	@EJB
	private ManufacturerFactory manufacturerFactory;

	@EJB
	private ProductQuantityFactory productQuantityFactory;

	@EJB
	private ProductOptionFactory productOptionFactory;

	@EJB
	private ProductAttributeFactory productAttributeFactory;

	private Product product;
	private ProductAttribute attribute1;
	private ProductAttribute attribute2;

	@Before
	public void setUp() {
		try {
			getUtx().begin();

			Category category1 = categoryFactory.createInstance();
			category1.setName("Category 1");
			category1 = categoryDAO.merge(category1);
			Category category2 = categoryFactory.createInstance();
			category2.setName("Category 2");
			category2 = categoryDAO.merge(category2);

			List<Category> categories = new ArrayList<Category>();
			categories.add(category1);
			categories.add(category2);

			Manufacturer manufacturer = manufacturerFactory.createInstance();
			manufacturer.setName("Manufactuerer");
			manufacturer = manufactoryDAO.merge(manufacturer);

			product = productDAO.newInstance();
			product.setName("Product");
			product.setDateAvailable(new Date());
			product.setDateAdded(new Date());
			product.setPrice(BigDecimal.ONE);
			product.setWeight(BigDecimal.ONE);
			product.setWidth(BigDecimal.ONE);
			product.setHeight(BigDecimal.ONE);
			product.setDetails("Details");
			product.setFreeGift(false);
			product.setManufacturer(manufacturer);
			product.setCategories(categories);
			product = productDAO.merge(product);

			ProductOption option = productOptionFactory.createInstance();
			option.setName("Option");
			option = getEm().merge(option);

			attribute1 = productAttributeFactory.createInstance();
			attribute1.setName("Attribute1");
			attribute1.setOption(option);
			attribute1.setValue(BigDecimal.ZERO);
			attribute1 = getEm().merge(attribute1);

			attribute2 = productAttributeFactory.createInstance();
			attribute2.setName("Attribute2");
			attribute2.setOption(option);
			attribute2.setValue(BigDecimal.TEN);
			attribute2 = getEm().merge(attribute2);

			List<ProductAttribute> attributes = new ArrayList<ProductAttribute>();
			attributes.add(attribute1);
			ProductQuantity quantity = productQuantityFactory.createInstance();
			quantity.setProduct(product);
			quantity.setQuantity(5);
			quantity.setSku("SKU1");
			quantity.setProductsAttributes(attributes);
			getEm().merge(quantity);

			attributes = new ArrayList<ProductAttribute>();
			attributes.add(attribute2);
			quantity = productQuantityFactory.createInstance();
			quantity.setProduct(product);
			quantity.setQuantity(5);
			quantity.setSku("SKU2");
			quantity.setProductsAttributes(attributes);
			getEm().merge(quantity);

			getEm().flush();
			getEm().refresh(product);

			getUtx().commit();
		} catch (NotSupportedException | SystemException | SecurityException | IllegalStateException
				| RollbackException | HeuristicMixedException | HeuristicRollbackException e) {
			try {
				getUtx().rollback();
			} catch (IllegalStateException | SecurityException | SystemException e1) {
			}
		}
	}

	@After
	public void cleanDb() {
		truncateTables("ProductQuantity", "Product", "Category", "Manufacturer");
	}

	@Test
	public void testLoadCategories() {
		product = productDAO.findById(product.getId());
		product = productDAO.loadCategories(product);
		Assert.assertNotNull(product.getCategories());
		Assert.assertEquals(2, product.getCategories().size());
	}

	@Test
	public void testGetProductQuantity() {
		List<ProductAttribute> attributes = new ArrayList<ProductAttribute>();
		attributes.add(attribute1);
		ProductQuantity quantity = productDAO.getProductQuantity(product, attributes);
		Assert.assertEquals("SKU1", quantity.getSku());
		Assert.assertTrue(quantity.getProductsAttributes().containsAll(attributes));
	}

}
