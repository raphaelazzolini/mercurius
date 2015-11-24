package br.unicamp.ic.lsd.mercurius.recommendedproductsconnector;

import br.unicamp.ic.sed.cosmos.IManager;

public class ProductRecommendedProductsComponentFactory {

	private ProductRecommendedProductsComponentFactory() {
		super();
	}

	public static IManager createInstance() {
		return new Manager();
	}

}
