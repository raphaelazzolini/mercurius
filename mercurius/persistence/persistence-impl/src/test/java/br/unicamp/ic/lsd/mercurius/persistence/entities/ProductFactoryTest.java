package br.unicamp.ic.lsd.mercurius.persistence.entities;

import javax.ejb.EJB;

import org.junit.Assert;
import org.junit.Test;

import br.unicamp.ic.lsd.mercurius.datatype.Product;
import br.unicamp.ic.lsd.mercurius.datatype.factory.ProductFactory;
import br.unicamp.ic.lsd.mercurius.persistence.dao.DomainTestContext;

public class ProductFactoryTest extends DomainTestContext {

	@EJB
	private ProductFactory productFactory;

	@Test
	public void testCreateProduct() {
		Product product = productFactory.createInstance();
		Assert.assertNotNull(product);
	}

}
