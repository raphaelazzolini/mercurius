package br.unicamp.ic.lsd.mercurius.orderloggingconnector;

import br.unicamp.ic.lsd.mercurius.loggingmgr.impl.LoggingMgrComponentFactory;
import br.unicamp.ic.lsd.mercurius.loggingmgr.spec.prov.LoggingMgt;
import br.unicamp.ic.lsd.mercurius.ordermgr.spec.req.OrderLoggingMgt;
import br.unicamp.ic.sed.cosmos.AManager;
import br.unicamp.ic.sed.cosmos.IManager;

class Manager extends AManager implements IManager {

	Manager() {
		super();
		setProvidedInterfaceType("IManager", IManager.class);
		setProvidedInterfaceType("OrderLoggingMgt", OrderLoggingMgt.class);
		setRequiredInterfaceType("LoggingMgt", LoggingMgt.class);

		IManager loggingManager = LoggingMgrComponentFactory.createInstance();
		setRequiredInterface("LoggingMgt", loggingManager.getProvidedInterface("LoggingMgt"));

		setProvidedInterface("IManager", IManager.class);
		setProvidedInterface("OrderLoggingMgt", new LoggingMgtAdapter(this));
	}

}
