package br.unicamp.ic.lsd.mercurius.addressmgr.impl;

import br.unicamp.ic.sed.cosmos.IManager;

public class AddressMgrComponentFactory {

	private static final String MANAGER_JNDI = "java:comp/env/ejb/addressManager";

	private AddressMgrComponentFactory() {
		super();
	}

	public static IManager createInstance() {
		return new Manager();
	}

}
