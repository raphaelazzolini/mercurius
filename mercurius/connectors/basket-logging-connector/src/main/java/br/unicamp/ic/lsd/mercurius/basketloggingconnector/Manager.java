package br.unicamp.ic.lsd.mercurius.basketloggingconnector;

import br.unicamp.ic.lsd.mercurius.basketmgr.spec.req.BasketLoggingMgt;
import br.unicamp.ic.lsd.mercurius.loggingmgr.impl.LoggingMgrComponentFactory;
import br.unicamp.ic.lsd.mercurius.loggingmgr.spec.prov.LoggingMgt;
import br.unicamp.ic.sed.cosmos.AManager;
import br.unicamp.ic.sed.cosmos.IManager;

class Manager extends AManager implements IManager {

	Manager() {
		super();
		setProvidedInterfaceType("IManager", IManager.class);
		setProvidedInterfaceType("BasketLoggingMgt", BasketLoggingMgt.class);
		setRequiredInterfaceType("LoggingMgt", LoggingMgt.class);

		IManager loggingManager = LoggingMgrComponentFactory.createInstance();
		setRequiredInterface("LoggingMgt", loggingManager.getProvidedInterface("LoggingMgt"));

		setProvidedInterface("IManager", IManager.class);
		setProvidedInterface("BasketLoggingMgt", new LoggingMgtAdapter(this));
	}

}
