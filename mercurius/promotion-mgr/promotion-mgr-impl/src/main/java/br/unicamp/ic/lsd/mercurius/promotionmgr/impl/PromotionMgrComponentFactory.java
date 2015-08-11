package br.unicamp.ic.lsd.mercurius.promotionmgr.impl;

import br.unicamp.ic.sed.cosmos.IManager;

public class PromotionMgrComponentFactory {

	private PromotionMgrComponentFactory() {
		super();
	}

	public static IManager createInstance() {
		return new Manager();
	}

}
