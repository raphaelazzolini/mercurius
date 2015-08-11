package br.unicamp.ic.lsd.mercurius.orderloggingconnector;

import br.unicamp.ic.sed.cosmos.IManager;

public class OrderLoggingConnectorComponentFactory {

	private OrderLoggingConnectorComponentFactory() {
		super();
	}

	public static IManager createInstance() {
		return new Manager();
	}

}
