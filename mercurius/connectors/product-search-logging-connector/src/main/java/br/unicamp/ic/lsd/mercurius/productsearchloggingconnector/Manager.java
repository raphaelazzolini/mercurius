package br.unicamp.ic.lsd.mercurius.productsearchloggingconnector;

import br.unicamp.ic.lsd.mercurius.loggingmgr.impl.LoggingMgrComponentFactory;
import br.unicamp.ic.lsd.mercurius.loggingmgr.spec.prov.LoggingMgt;
import br.unicamp.ic.lsd.mercurius.productsearchmgr.spec.req.ProductSearchLoggingMgt;
import br.unicamp.ic.sed.cosmos.AManager;
import br.unicamp.ic.sed.cosmos.IManager;

class Manager extends AManager implements IManager {

	Manager() {
		super();
		setProvidedInterfaceType("IManager", IManager.class);
		setProvidedInterfaceType("ProductSearchLoggingMgt", ProductSearchLoggingMgt.class);
		setRequiredInterfaceType("LoggingMgt", LoggingMgt.class);

		IManager loggingManager = LoggingMgrComponentFactory.createInstance();
		setRequiredInterface("LoggingMgt", loggingManager.getProvidedInterface("LoggingMgt"));

		setProvidedInterface("IManager", IManager.class);
		setProvidedInterface("ProductSearchLoggingMgt", new LoggingMgtAdapter(this));
	}

}
