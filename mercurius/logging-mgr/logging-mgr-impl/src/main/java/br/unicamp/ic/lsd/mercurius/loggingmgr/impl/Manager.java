package br.unicamp.ic.lsd.mercurius.loggingmgr.impl;

import br.unicamp.ic.lsd.mercurius.loggingmgr.spec.prov.LoggingManager;
import br.unicamp.ic.lsd.mercurius.loggingmgr.spec.prov.LoggingMgt;
import br.unicamp.ic.sed.cosmos.AManager;
import br.unicamp.ic.sed.cosmos.IManager;

class Manager extends AManager implements LoggingManager {

	Manager() {
		super();
		setProvidedInterfaceType("IManager", IManager.class);
		setProvidedInterfaceType("LoggingMgt", LoggingMgt.class);
		setProvidedInterface("IManager", this);
		setProvidedInterface("LoggingMgt", new LoggingFacade(this));
	}

}
