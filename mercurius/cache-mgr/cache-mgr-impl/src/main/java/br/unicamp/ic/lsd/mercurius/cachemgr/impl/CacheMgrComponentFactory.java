package br.unicamp.ic.lsd.mercurius.cachemgr.impl;

import br.unicamp.ic.sed.cosmos.IManager;

public class CacheMgrComponentFactory {

	private CacheMgrComponentFactory() {
		super();
	}

	public static IManager createInstance() {
		return new Manager();
	}

}
