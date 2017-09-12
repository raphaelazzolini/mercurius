package br.unicamp.ic.lsd.mercurius.customermgr.aspects;

import br.unicamp.ic.lsd.mercurius.datatype.Customer;

import java.util.List;

import br.unicamp.ic.lsd.mercurius.customermgr.spec.prov.CustomerMgt;

public aspect XPICustomer {

	public pointcut login(String password) :
		execution(public Customer CustomerMgt.login(String, String)) && args(*, password);

	public pointcut registerCustomer(Customer customer) :
		execution(public Customer CustomerMgt.registerCustomer(Customer)) && args(customer);

	public pointcut changePassword(String oldPassword, String newPassword) :
		execution(public Customer CustomerMgt.changePassword(Customer, String, String)) && args(*, oldPassword, newPassword);
	
	public pointcut sendEmail(List<Customer> customers) :
		execution(public void CustomerMgt.sendEmail(List<Customer>)) && args(customers);
	
}
