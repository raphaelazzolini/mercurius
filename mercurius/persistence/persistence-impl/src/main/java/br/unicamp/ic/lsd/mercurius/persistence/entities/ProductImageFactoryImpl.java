package br.unicamp.ic.lsd.mercurius.persistence.entities;

import javax.ejb.Local;
import javax.ejb.Singleton;

import br.unicamp.ic.lsd.mercurius.datatype.ProductImage;
import br.unicamp.ic.lsd.mercurius.datatype.factory.ProductImageFactory;

@Singleton(name = "productImageFactory")
@Local(ProductImageFactory.class)
public class ProductImageFactoryImpl implements ProductImageFactory {

	private ProductImageFactoryImpl() {
		super();
	}

	@Override
	public ProductImage createInstance() {
		return new ProductImageImpl();
	}

}
