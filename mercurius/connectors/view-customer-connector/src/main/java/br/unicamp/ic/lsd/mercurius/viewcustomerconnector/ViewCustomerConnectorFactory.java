package br.unicamp.ic.lsd.mercurius.viewcustomerconnector;

import br.unicamp.ic.sed.cosmos.IManager;

public class ViewCustomerConnectorFactory {

	private ViewCustomerConnectorFactory() {
		super();
	}

	public static IManager createInstance() {
		return new Manager();
	}

}
