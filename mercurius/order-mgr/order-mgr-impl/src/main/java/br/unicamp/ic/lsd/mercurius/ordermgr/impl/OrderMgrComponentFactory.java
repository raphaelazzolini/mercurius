package br.unicamp.ic.lsd.mercurius.ordermgr.impl;

import br.unicamp.ic.sed.cosmos.IManager;

public class OrderMgrComponentFactory {

	private OrderMgrComponentFactory() {
		super();
	}

	public static IManager createInstance() {
		return new Manager();
	}

}
