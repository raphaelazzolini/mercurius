package br.unicamp.ic.lsd.mercurius.emailmarketingmgr.impl;

import br.unicamp.ic.sed.cosmos.IManager;

public class EmailMarketingMgrComponentFactory {

	private EmailMarketingMgrComponentFactory() {
		super();
	}

	public static IManager createInstance() {
		return new Manager();
	}

}