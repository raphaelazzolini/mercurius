package br.unicamp.ic.lsd.mercurius.emailmarketingcustomerconnector;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

import br.unicamp.ic.lsd.mercurius.customermgr.spec.prov.CustomerMgt;
import br.unicamp.ic.lsd.mercurius.datatype.Customer;
import br.unicamp.ic.lsd.mercurius.emailmarketingmgr.spec.req.EmailMarketingCustomerMgt;
import br.unicamp.ic.sed.cosmos.IManager;

public class CustomerMgtAdapter implements EmailMarketingCustomerMgt {

	private final IManager manager;
	private CustomerMgt customerMgt;
	
	public CustomerMgtAdapter(Manager manager) {
		super();
		this.manager = manager;
		customerMgt = (CustomerMgt) this.manager.getRequiredInterface("CustomerMgt");
	}

	@Override
	public List<String> getCustomers() {
		
		List<Customer> customers = customerMgt.getCustomers();
		List<String> customersEmail = new ArrayList<String>();
		
		for(Customer c: customers)
			customersEmail.add(c.getEmailAddress());
			
		return customersEmail;
	}

}
