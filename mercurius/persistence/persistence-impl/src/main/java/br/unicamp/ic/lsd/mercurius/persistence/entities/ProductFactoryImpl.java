package br.unicamp.ic.lsd.mercurius.persistence.entities;

import javax.ejb.Local;
import javax.ejb.Singleton;

import br.unicamp.ic.lsd.mercurius.datatype.Product;
import br.unicamp.ic.lsd.mercurius.datatype.factory.ProductFactory;

@Singleton(name = "productFactory")
@Local(ProductFactory.class)
public class ProductFactoryImpl implements ProductFactory {

	private ProductFactoryImpl() {
		super();
	}

	@Override
	public Product createInstance() {
		return new ProductImpl();
	}

}
