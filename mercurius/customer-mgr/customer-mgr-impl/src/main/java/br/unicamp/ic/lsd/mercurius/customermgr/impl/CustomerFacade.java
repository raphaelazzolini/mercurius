package br.unicamp.ic.lsd.mercurius.customermgr.impl;

import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import br.unicamp.ic.lsd.mercurius.addressmgr.spec.prov.AddressMgt;
import br.unicamp.ic.lsd.mercurius.customermgr.spec.prov.CustomerManager;
import br.unicamp.ic.lsd.mercurius.customermgr.spec.prov.CustomerMgt;
import br.unicamp.ic.lsd.mercurius.datatype.Address;
import br.unicamp.ic.lsd.mercurius.datatype.Customer;
import br.unicamp.ic.lsd.mercurius.excpetionhandler.exceptions.DuplicatedDocumentException;
import br.unicamp.ic.lsd.mercurius.excpetionhandler.exceptions.DuplicatedEmailExcpetion;
import br.unicamp.ic.sed.cosmos.IManager;

public class CustomerFacade implements CustomerMgt {

	private static final String ADDRESS_MGT = "AddressMgt";
	private static final String ADDRESS_MGR = "AddressMgr";

	private CustomerManager manager;
	private CustomerMgtImpl customerMgtImpl;
	private AddressMgt addressMgt;

	CustomerFacade(CustomerManager manager) {
		super();
		this.manager = manager;
		this.customerMgtImpl = new CustomerMgtImpl(this.manager);
		IManager addressManager = this.manager.getInternalComponent(ADDRESS_MGR);
		this.addressMgt = (AddressMgt) addressManager.getProvidedInterface(ADDRESS_MGT);
	}

	@Override
	public Customer login(String email, String password) {
		return customerMgtImpl.login(email, password);
	}

	@Override
	public Customer socialNetworkLogin(String socialNetworkId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer loginBySession(String sessionId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void logout(Customer customer) {

	}

	@Override
	public void saveCustomerSession(String session) {
		// TODO Auto-generated method stub

	}

	@Override
	public Customer registerCustomer(Customer customer) throws DuplicatedEmailExcpetion, DuplicatedDocumentException {
		return customerMgtImpl.registerCustomer(customer);
	}

	@Override
	public Customer editCustomer(Customer customer) {
		return customerMgtImpl.editCustomer(customer);
	}

	@Override
	public void deleteCustomer(Customer customer) {
		customerMgtImpl.deleteCustomer(customer);
	}

	@Override
	public Customer loadCustomerAddresses(Customer customer) {
		return customerMgtImpl.loadCustomerAddresses(customer);
	}

	@Override
	public void setCustomerDefaultAddress(Customer customer, Address address) {
		if (customer.getId() != null && address.getCustomer().equals(customer)) {
			if (address.getId() == null) {
				address = saveAddress(address);
			}
			customerMgtImpl.setCustomerDefaultAddress(customer, address);
		}

	}

	@Override
	public Address saveAddress(Address address) {
		return addressMgt.saveAddress(address);
	}

	@Override
	public void deleteAddress(Address address) {
		addressMgt.deleteAddress(address);
	}

	@Override
	public Customer changePassword(Customer customer, String oldPassword, String newPassword) {
		customer = customerMgtImpl.login(customer.getEmailAddress(), oldPassword);
		if (customer != null) {
			customer.setPassword(newPassword);
			return customerMgtImpl.editCustomer(customer);
		}
		return null;
	}

	CustomerMgtImpl getCustomerMgtImpl() {
		return customerMgtImpl;
	}

	void setCustomerMgtImpl(CustomerMgtImpl customerMgtImpl) {
		this.customerMgtImpl = customerMgtImpl;
	}

	@Override
	public Customer newCustomer() {
		return manager.getCustomerDAO().newInstance();
	}

	@Override
	public Address newAddress() {
		return addressMgt.newAddress();
	}

	@Override
	public void sendEmail(List<Customer> customers) {
	}
	
	@Override
	public void sendEmailMarketing() {
		List<Customer> customers = (List<Customer>) manager.getCustomerDAO().getCustomers();
		sendEmail(customers);
	}

}
