package br.unicamp.ic.lsd.mercurius.persistence.entities;

import javax.ejb.Local;
import javax.ejb.Singleton;

import br.unicamp.ic.lsd.mercurius.datatype.ProductAttribute;
import br.unicamp.ic.lsd.mercurius.datatype.factory.ProductAttributeFactory;

@Singleton(name = "productAttributeFactory")
@Local(ProductAttributeFactory.class)
public class ProductAttributeFactoryImpl implements ProductAttributeFactory {

	private ProductAttributeFactoryImpl() {
		super();
	}

	@Override
	public ProductAttribute createInstance() {
		return new ProductAttributeImpl();
	}

}
