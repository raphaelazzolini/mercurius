package br.unicamp.ic.lsd.mercurius.admin.impl;

import br.unicamp.ic.sed.cosmos.IManager;

public class AdminComponentFactory {

	private AdminComponentFactory() {
		super();
	}

	public static IManager createInstance() {
		return new Manager();
	}

}
