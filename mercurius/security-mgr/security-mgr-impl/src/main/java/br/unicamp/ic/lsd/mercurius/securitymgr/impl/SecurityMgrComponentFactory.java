package br.unicamp.ic.lsd.mercurius.securitymgr.impl;

import br.unicamp.ic.sed.cosmos.IManager;

public class SecurityMgrComponentFactory {

	private SecurityMgrComponentFactory() {
		super();
	}

	public static IManager createInstance() {
		return new Manager();
	}

}
