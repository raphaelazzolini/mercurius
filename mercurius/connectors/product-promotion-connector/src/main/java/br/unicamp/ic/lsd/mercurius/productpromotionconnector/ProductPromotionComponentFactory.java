package br.unicamp.ic.lsd.mercurius.productpromotionconnector;

import br.unicamp.ic.sed.cosmos.IManager;

public class ProductPromotionComponentFactory {

	private ProductPromotionComponentFactory() {
		super();
	}

	public static IManager createInstance() {
		return new Manager();
	}

}
