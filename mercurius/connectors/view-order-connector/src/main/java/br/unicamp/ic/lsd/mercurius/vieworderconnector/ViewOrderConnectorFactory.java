package br.unicamp.ic.lsd.mercurius.vieworderconnector;

import br.unicamp.ic.sed.cosmos.IManager;

public class ViewOrderConnectorFactory {

	private ViewOrderConnectorFactory() {
		super();
	}

	public static IManager createInstance() {
		return new Manager();
	}

}
