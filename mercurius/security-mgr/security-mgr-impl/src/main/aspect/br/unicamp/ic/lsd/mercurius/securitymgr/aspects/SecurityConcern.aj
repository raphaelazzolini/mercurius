package br.unicamp.ic.lsd.mercurius.securitymgr.aspects;

import br.unicamp.ic.lsd.mercurius.datatype.Customer;
import br.unicamp.ic.lsd.mercurius.securitymgr.impl.SecurityMgrComponentFactory;
import br.unicamp.ic.lsd.mercurius.securitymgr.spec.prov.SecurityMgt;
import br.unicamp.ic.lsd.mercurius.customermgr.spec.prov.CustomerMgt;

public aspect SecurityConcern {

	private SecurityMgt securityMgt = (SecurityMgt) SecurityMgrComponentFactory.createInstance().getProvidedInterface("SecurityMgt");

	public pointcut login(String password) :
		execution(public Customer CustomerMgt.login(String, String)) && args(*, password);

	public pointcut registerCustomer(Customer customer) :
		execution(public Customer CustomerMgt.registerCustomer(Customer)) && args(customer);

	public pointcut changePassword(String oldPassword, String newPassword) :
		execution(public Customer CustomerMgt.changePassword(Customer, String, String)) && args(*, oldPassword, newPassword);

	Customer around(String password) : login(password) {
		if (password != null) {
			password = securityMgt.encryptPassword(password);
		}
		return proceed(password);
	}

	Customer around(Customer customer) : registerCustomer(customer) {
		if (customer != null) {
			String newPassword = securityMgt.encryptPassword(customer.getPassword());
			customer.setPassword(newPassword);
		}
		return proceed(customer);
	}

	Customer around(String oldPassword, String newPassword) : changePassword(oldPassword, newPassword) {
		if (oldPassword != null && newPassword != null) {
			oldPassword = securityMgt.encryptPassword(oldPassword);
			newPassword = securityMgt.encryptPassword(newPassword);
		}
		return proceed(oldPassword, newPassword);
	}

}
