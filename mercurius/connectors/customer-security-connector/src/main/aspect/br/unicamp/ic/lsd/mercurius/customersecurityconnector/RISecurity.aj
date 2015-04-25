package br.unicamp.ic.lsd.mercurius.customersecurityconnector;

import br.unicamp.ic.lsd.mercurius.datatype.Customer;
import br.unicamp.ic.lsd.mercurius.customermgr.aspects.XPICustomer;

public privileged aspect RISecurity {

	SecurityCustomerAdapter adapter = new SecurityCustomerAdapter();

	public pointcut login(String password) : XPICustomer.login(String) && args(*, password);
	public pointcut registerCustomer(Customer customer) : XPICustomer.registerCustomer(Customer) && args(customer);
	public pointcut changePassword(String oldPassword, String newPassword) : XPICustomer.changePassword(String, String) && args(*, oldPassword, newPassword);

	Customer around(String password) : login(password) {
		if (password != null) {
			password = adapter.encryptPassword(password);
		}
		return proceed(password);
	}

	Customer around(Customer customer) : registerCustomer(customer) {
		if (customer != null) {
			String newPassword = adapter.encryptPassword(customer.getPassword());
			customer.setPassword(newPassword);
		}
		return proceed(customer);
	}

	Customer around(String oldPassword, String newPassword) : changePassword(oldPassword, newPassword) {
		if (oldPassword != null && newPassword != null) {
			oldPassword = adapter.encryptPassword(oldPassword);
			newPassword = adapter.encryptPassword(newPassword);
		}
		return proceed(oldPassword, newPassword);
	}

}
