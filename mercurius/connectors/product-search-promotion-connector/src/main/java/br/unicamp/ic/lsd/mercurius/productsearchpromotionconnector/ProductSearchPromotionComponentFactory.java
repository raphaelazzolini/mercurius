package br.unicamp.ic.lsd.mercurius.productsearchpromotionconnector;

import br.unicamp.ic.sed.cosmos.IManager;

public class ProductSearchPromotionComponentFactory {

	private ProductSearchPromotionComponentFactory() {
		super();
	}

	public static IManager createInstance() {
		return new Manager();
	}

}
