package br.unicamp.ic.lsd.mercurius.viewproductsearchconnector;

import br.unicamp.ic.sed.cosmos.IManager;

public class ViewProductSearchConnectorFactory {

	private ViewProductSearchConnectorFactory() {
		super();
	}

	public static IManager createInstance() {
		return new Manager();
	}

}
