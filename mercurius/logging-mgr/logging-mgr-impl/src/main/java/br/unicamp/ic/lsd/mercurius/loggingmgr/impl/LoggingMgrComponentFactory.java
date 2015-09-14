package br.unicamp.ic.lsd.mercurius.loggingmgr.impl;

import br.unicamp.ic.sed.cosmos.IManager;

public class LoggingMgrComponentFactory {

	private LoggingMgrComponentFactory() {
		super();
	}

	public static IManager createInstance() {
		return new Manager();
	}

}
