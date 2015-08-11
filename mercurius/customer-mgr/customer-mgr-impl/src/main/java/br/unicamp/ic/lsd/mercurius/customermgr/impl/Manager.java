package br.unicamp.ic.lsd.mercurius.customermgr.impl;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.unicamp.ic.lsd.mercurius.addressmgr.impl.AddressMgrComponentFactory;
import br.unicamp.ic.lsd.mercurius.customermgr.spec.prov.CustomerManager;
import br.unicamp.ic.lsd.mercurius.customermgr.spec.prov.CustomerMgt;
import br.unicamp.ic.lsd.mercurius.customermgr.spec.req.CustomerSecurityMgt;
import br.unicamp.ic.lsd.mercurius.customersecurityconnector.CustomerSecurityConnectorComponentFactory;
import br.unicamp.ic.lsd.mercurius.persistence.dao.CustomerDAO;
import br.unicamp.ic.sed.cosmos.AManagerComposite;
import br.unicamp.ic.sed.cosmos.IManager;

class Manager extends AManagerComposite implements CustomerManager {

	private static final String ADDRESS_MGR = "AddressMgr";
	private static final String I_MANAGER = "IManager";
	private static final String CUSTOMER_MGT = "CustomerMgt";

	private CustomerDAO customerDAO;

	Manager() {
		super();
		try {
			Context context = new InitialContext();
			customerDAO = (CustomerDAO) context.lookup("java:app/persistence/customerDAO");
			setInternalComponent(ADDRESS_MGR, AddressMgrComponentFactory.createInstance());

			setRequiredInterfaceType("CustomerSecurityMgt", CustomerSecurityMgt.class);
			IManager manager = CustomerSecurityConnectorComponentFactory.createInstance();
			setRequiredInterface("CustomerSecurityMgt", manager.getProvidedInterface("CustomerSecurityMgt"));

			setProvidedInterfaceType(I_MANAGER, IManager.class);
			setProvidedInterface(I_MANAGER, this);
			setProvidedInterfaceType(CUSTOMER_MGT, CustomerMgt.class);
			setProvidedInterface(CUSTOMER_MGT, new CustomerFacade(this));
		} catch (NamingException e) {
		}
	}

	@Override
	public CustomerDAO getCustomerDAO() {
		return customerDAO;
	}

}
