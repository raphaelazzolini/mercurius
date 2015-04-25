package br.unicamp.ic.lsd.mercurius.viewbasketconnector;

import br.unicamp.ic.sed.cosmos.IManager;

public class ViewBasketConnectorFactory {

	private ViewBasketConnectorFactory() {
		super();
	}

	public static IManager createInstance() {
		return new Manager();
	}

}
