package br.unicamp.ic.lsd.mercurius.view.impl;

import br.unicamp.ic.lsd.mercurius.view.spec.req.ViewCustomerMgt;
import br.unicamp.ic.sed.cosmos.AManager;
import br.unicamp.ic.sed.cosmos.IManager;

class Manager extends AManager implements IManager {

	Manager() {
		super();
		setProvidedInterfaceType("IManager", IManager.class);
		setProvidedInterface("IManager", this);
		setRequiredInterfaceType("ViewCustomerMgt", ViewCustomerMgt.class);

		// IManager viewCustomerConnectorManager =
		// ViewCustomerConnectorFactory.createInstance();
		// setRequiredInterface("ViewCustomerMgt",
		// viewCustomerConnectorManager.getProvidedInterface("ViewCustomerMgt"));
	}

}
