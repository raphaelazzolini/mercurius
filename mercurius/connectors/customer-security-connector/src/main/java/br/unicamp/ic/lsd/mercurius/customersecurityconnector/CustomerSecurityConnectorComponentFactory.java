package br.unicamp.ic.lsd.mercurius.customersecurityconnector;

import br.unicamp.ic.sed.cosmos.IManager;

public class CustomerSecurityConnectorComponentFactory {

	private CustomerSecurityConnectorComponentFactory() {
		super();
	}

	public static IManager createInstance() {
		return new Manager();
	}

}
