package br.unicamp.ic.lsd.mercurius.customersecurityconnector;

import br.unicamp.ic.lsd.mercurius.customermgr.spec.req.CustomerSecurityMgt;
import br.unicamp.ic.lsd.mercurius.securitymgr.impl.SecurityMgrComponentFactory;
import br.unicamp.ic.lsd.mercurius.securitymgr.spec.prov.SecurityMgt;
import br.unicamp.ic.sed.cosmos.AManager;
import br.unicamp.ic.sed.cosmos.IManager;

class Manager extends AManager implements IManager {

	Manager() {
		super();
		setProvidedInterfaceType("IManager", IManager.class);
		setProvidedInterfaceType("CustomerSecurityMgt", CustomerSecurityMgt.class);
		setRequiredInterfaceType("SecurityMgt", SecurityMgt.class);

		IManager loggingManager = SecurityMgrComponentFactory.createInstance();
		setRequiredInterface("SecurityMgt", loggingManager.getProvidedInterface("SecurityMgt"));

		setProvidedInterface("IManager", IManager.class);
		setProvidedInterface("CustomerSecurityMgt", new SecurityMgtAdapter(this));
	}

}
