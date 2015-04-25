package br.unicamp.ic.lsd.mercurius.productmgr.impl;

import br.unicamp.ic.sed.cosmos.IManager;

public class ProductMgrComponentFactory {

	private ProductMgrComponentFactory() {
		super();
	}

	public static IManager createInstance() {
		return new Manager();
	}

}
