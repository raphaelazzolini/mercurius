package br.unicamp.ic.lsd.mercurius.productsearchmgr.impl;

import br.unicamp.ic.sed.cosmos.IManager;

public class ProductSearchMgrComponentFactory {

	private ProductSearchMgrComponentFactory() {
		super();
	}

	public static IManager createInstance() {
		return new Manager();
	}

}
