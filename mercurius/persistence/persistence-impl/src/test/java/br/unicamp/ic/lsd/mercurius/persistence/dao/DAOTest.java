package br.unicamp.ic.lsd.mercurius.persistence.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.unicamp.ic.lsd.mercurius.datatype.Manufacturer;
import br.unicamp.ic.lsd.mercurius.datatype.Product;
import br.unicamp.ic.lsd.mercurius.datatype.factory.ManufacturerFactory;
import br.unicamp.ic.lsd.mercurius.persistence.dao.ManufactoryDAO;
import br.unicamp.ic.lsd.mercurius.persistence.dao.ProductDAO;

public class DAOTest extends DomainTestContext {

	@EJB
	private ProductDAO productDao;

	@EJB
	private ManufactoryDAO manufactoryDAO;

	@EJB
	private ManufacturerFactory manufacturerFactory;

	private Manufacturer manufacturer;

	@Before
	public void setUp() {
		manufacturer = manufacturerFactory.createInstance();
		manufacturer.setName("Manufactuerer");
		manufacturer = manufactoryDAO.merge(manufacturer);

		productDao.persist(generateProduct("Product 1", manufacturer));
		productDao.persist(generateProduct("Product 2", manufacturer));
		productDao.persist(generateProduct("Product 3", manufacturer));
	}

	@After
	public void deleteDb() {
		truncateTables("Product");
	}

	@Test
	public void testGetAll() {
		List<Product> list = productDao.getAll();
		Assert.assertEquals(3, list.size());
	}

	@Test
	public void testMerge() {
		Product product = generateProduct("ProductTest", manufacturer);
		Assert.assertNull(product.getId());
		product = productDao.merge(product);
		Assert.assertNotNull(product.getId());
	}

	@Test
	public void testfindById() {
		Product product = generateProduct("ProductTest", manufacturer);
		product = productDao.merge(product);
		Product searchedProduct = productDao.findById(product.getId());
		Assert.assertEquals(product, searchedProduct);
	}

	@Test
	public void testRemoveById() {
		Product product = generateProduct("ProductTest", manufacturer);
		product = productDao.merge(product);
		List<Product> list = productDao.getAll();
		Assert.assertEquals(4, list.size());
		productDao.removeById(product.getId());
		list = productDao.getAll();
		Assert.assertEquals(3, list.size());
		product = productDao.findById(product.getId());
		Assert.assertNull(product);
	}

	private Product generateProduct(String name, Manufacturer manufacturer) {
		Product product = productDao.newInstance();
		product.setName(name);
		product.setDateAvailable(new Date());
		product.setDateAdded(new Date());
		product.setPrice(BigDecimal.ONE);
		product.setWeight(BigDecimal.ONE);
		product.setWidth(BigDecimal.ONE);
		product.setHeight(BigDecimal.ONE);
		product.setDetails("Details");
		product.setFreeGift(false);
		product.setManufacturer(manufacturer);
		return product;
	}

}
