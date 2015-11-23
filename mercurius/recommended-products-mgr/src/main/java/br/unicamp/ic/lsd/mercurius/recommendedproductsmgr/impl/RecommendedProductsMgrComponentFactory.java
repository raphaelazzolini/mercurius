package br.unicamp.ic.lsd.mercurius.recommendedproductsmgr.impl;

import br.unicamp.ic.sed.cosmos.IManager;

public class RecommendedProductsMgrComponentFactory {

	private RecommendedProductsMgrComponentFactory() {
		super();
	}

	public static IManager createInstance() {
		return new Manager();
	}

}
