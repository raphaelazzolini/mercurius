package br.unicamp.ic.lsd.mercurius.persistence.entities;

import javax.ejb.Local;
import javax.ejb.Singleton;

import br.unicamp.ic.lsd.mercurius.datatype.ProductQuantity;
import br.unicamp.ic.lsd.mercurius.datatype.factory.ProductQuantityFactory;

@Singleton(name = "productQuantityFactory")
@Local(ProductQuantityFactory.class)
public class ProductQuantityFactoryImpl implements ProductQuantityFactory {

	private ProductQuantityFactoryImpl() {
		super();
	}

	@Override
	public ProductQuantity createInstance() {
		return new ProductQuantityImpl();
	}

}
