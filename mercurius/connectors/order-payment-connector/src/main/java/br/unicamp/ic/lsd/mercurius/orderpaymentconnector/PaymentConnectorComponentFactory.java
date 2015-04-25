package br.unicamp.ic.lsd.mercurius.orderpaymentconnector;

import br.unicamp.ic.sed.cosmos.IManager;

public class PaymentConnectorComponentFactory {

	private PaymentConnectorComponentFactory() {
		super();
	}

	public static IManager createInstance() {
		return new Manager();
	}

}
