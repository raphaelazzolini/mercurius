package br.unicamp.ic.lsd.mercurius.persistence.entities;

import javax.ejb.Local;
import javax.ejb.Singleton;

import br.unicamp.ic.lsd.mercurius.datatype.ProductOption;
import br.unicamp.ic.lsd.mercurius.datatype.factory.ProductOptionFactory;

@Singleton(name = "productOptionFactory")
@Local(ProductOptionFactory.class)
public class ProductOptionFactoryImpl implements ProductOptionFactory {

	private ProductOptionFactoryImpl() {
		super();
	}

	@Override
	public ProductOption createInstance() {
		return new ProductOptionImpl();
	}

}
