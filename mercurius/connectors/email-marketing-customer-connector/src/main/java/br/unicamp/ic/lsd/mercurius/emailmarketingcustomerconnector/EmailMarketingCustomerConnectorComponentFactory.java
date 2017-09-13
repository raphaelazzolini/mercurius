package br.unicamp.ic.lsd.mercurius.emailmarketingcustomerconnector;

import br.unicamp.ic.sed.cosmos.IManager;

public class EmailMarketingCustomerConnectorComponentFactory {

	private EmailMarketingCustomerConnectorComponentFactory() {
		super();
	}

	public static IManager createInstance() {
		return new Manager();
	}

}
