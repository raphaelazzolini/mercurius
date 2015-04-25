package br.unicamp.ic.lsd.mercurius.categorymgr.impl;

import br.unicamp.ic.sed.cosmos.IManager;

public class CategoryMgrComponentFactory {

	private CategoryMgrComponentFactory() {
		super();
	}

	public static IManager createInstance() {
		return new Manager();
	}

}
