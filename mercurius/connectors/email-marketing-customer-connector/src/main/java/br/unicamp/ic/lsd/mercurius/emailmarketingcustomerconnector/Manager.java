package br.unicamp.ic.lsd.mercurius.emailmarketingcustomerconnector;

import br.unicamp.ic.lsd.mercurius.emailmarketingmgr.spec.req.EmailMarketingCustomerMgt;
import br.unicamp.ic.lsd.mercurius.customermgr.impl.CustomerMgrComponentFactory;
import br.unicamp.ic.lsd.mercurius.customermgr.spec.prov.CustomerMgt;
import br.unicamp.ic.lsd.mercurius.emailmarketingcustomerconnector.CustomerMgtAdapter;
import br.unicamp.ic.sed.cosmos.AManager;
import br.unicamp.ic.sed.cosmos.IManager;

class Manager extends AManager implements IManager {

	Manager() {
		super();
		
		setRequiredInterfaceType("CustomerMgt", CustomerMgt.class);
		//TODO criacao de dois customers?
		IManager CustomerManager = CustomerMgrComponentFactory.createInstance();
		setRequiredInterface("CustomerMgt", CustomerManager.getProvidedInterface("CustomerMgt"));
		
		setProvidedInterfaceType("IManager", IManager.class);
		setProvidedInterfaceType("EmailMarketingCustomerMgt", EmailMarketingCustomerMgt.class);
		setProvidedInterface("IManager", IManager.class);
		setProvidedInterface("EmailMarketingCustomerMgt", new CustomerMgtAdapter(this));
	}

}
