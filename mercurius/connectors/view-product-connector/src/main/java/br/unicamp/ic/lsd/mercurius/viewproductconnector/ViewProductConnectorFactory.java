package br.unicamp.ic.lsd.mercurius.viewproductconnector;

import br.unicamp.ic.sed.cosmos.IManager;

public class ViewProductConnectorFactory {

	private ViewProductConnectorFactory() {
		super();
	}

	public static IManager createInstance() {
		return new Manager();
	}

}
