package br.unicamp.ic.lsd.mercurius.productsearchloggingconnector;

import br.unicamp.ic.sed.cosmos.IManager;

public class ProductSearchLoggingConnectorComponentFactory {

	private ProductSearchLoggingConnectorComponentFactory() {
		super();
	}

	public static IManager createInstance() {
		return new Manager();
	}

}
