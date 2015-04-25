package br.unicamp.ic.lsd.mercurius.viewcustomerconnector;

import br.unicamp.ic.lsd.mercurius.customermgr.impl.CustomerMgrComponentFactory;
import br.unicamp.ic.lsd.mercurius.customermgr.spec.prov.CustomerMgt;
import br.unicamp.ic.lsd.mercurius.view.spec.req.ViewCustomerMgt;
import br.unicamp.ic.sed.cosmos.AManager;
import br.unicamp.ic.sed.cosmos.IManager;

class Manager extends AManager implements ViewCustomerConnectorManager {

	private static final String I_MANAGER = "IManager";
	private static final String CUSTOMER_MGT = "CustomerMgt";
	private static final String VIEW_CUSTOMER_MGT = "ViewCustomerMgt";

	Manager() {
		super();
		setProvidedInterfaceType(I_MANAGER, IManager.class);
		setProvidedInterfaceType(VIEW_CUSTOMER_MGT, ViewCustomerMgt.class);
		setRequiredInterfaceType(CUSTOMER_MGT, CustomerMgt.class);

		IManager customerManager = CustomerMgrComponentFactory.createInstance();
		setRequiredInterface(CUSTOMER_MGT, customerManager.getProvidedInterface(CUSTOMER_MGT));

		setProvidedInterface(I_MANAGER, this);
		setProvidedInterface(VIEW_CUSTOMER_MGT, new CustomerMgtAdapter(this));
	}

}
