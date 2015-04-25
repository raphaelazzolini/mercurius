package br.unicamp.ic.lsd.mercurius.customermgr.impl;

import br.unicamp.ic.sed.cosmos.IManager;

public class CustomerMgrComponentFactory {

	private CustomerMgrComponentFactory() {
		super();
	}

	public static IManager createInstance() {
		return new Manager();
	}

}
