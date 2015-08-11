package br.unicamp.ic.lsd.mercurius.basketloggingconnector;

import br.unicamp.ic.sed.cosmos.IManager;

public class BasketLoggingConnectorComponentFactory {

	private BasketLoggingConnectorComponentFactory() {
		super();
	}

	public static IManager createInstance() {
		return new Manager();
	}

}
