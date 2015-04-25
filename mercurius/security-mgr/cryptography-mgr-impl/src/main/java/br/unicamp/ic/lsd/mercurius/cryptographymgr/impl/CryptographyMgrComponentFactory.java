package br.unicamp.ic.lsd.mercurius.cryptographymgr.impl;

import br.unicamp.ic.sed.cosmos.IManager;

public class CryptographyMgrComponentFactory {

	private CryptographyMgrComponentFactory() {
		super();
	}

	public static IManager createInstance() {
		return new Manager();
	}

}
