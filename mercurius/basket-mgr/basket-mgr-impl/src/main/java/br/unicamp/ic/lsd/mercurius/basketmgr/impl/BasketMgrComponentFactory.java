package br.unicamp.ic.lsd.mercurius.basketmgr.impl;

import br.unicamp.ic.sed.cosmos.IManager;

public class BasketMgrComponentFactory {

	private BasketMgrComponentFactory() {
		super();
	}

	public static IManager createInstance() {
		return new Manager();
	}

}
