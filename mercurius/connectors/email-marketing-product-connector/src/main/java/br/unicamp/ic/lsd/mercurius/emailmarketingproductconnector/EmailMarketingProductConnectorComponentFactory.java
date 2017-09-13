package br.unicamp.ic.lsd.mercurius.emailmarketingproductconnector;

import br.unicamp.ic.sed.cosmos.IManager;

public class EmailMarketingProductConnectorComponentFactory {

	private EmailMarketingProductConnectorComponentFactory() {
		super();
	}

	public static IManager createInstance() {
		return new Manager();
	}

}
