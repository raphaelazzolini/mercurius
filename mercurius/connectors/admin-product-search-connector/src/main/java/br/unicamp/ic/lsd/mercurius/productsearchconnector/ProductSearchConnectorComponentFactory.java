package br.unicamp.ic.lsd.mercurius.productsearchconnector;

import br.unicamp.ic.sed.cosmos.IManager;

public class ProductSearchConnectorComponentFactory {

	private ProductSearchConnectorComponentFactory() {
		super();
	}

	public static IManager createInstance() {
		return new Manager();
	}

}
