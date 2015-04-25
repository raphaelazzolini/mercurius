package br.unicamp.ic.lsd.mercurius.customermgr.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;

import br.unicamp.ic.lsd.mercurius.customermgr.spec.prov.CustomerManager;
import br.unicamp.ic.lsd.mercurius.datatype.Address;
import br.unicamp.ic.lsd.mercurius.datatype.Customer;
import br.unicamp.ic.lsd.mercurius.excpetionhandler.exceptions.DuplicatedDocumentException;
import br.unicamp.ic.lsd.mercurius.excpetionhandler.exceptions.DuplicatedEmailExcpetion;
import br.unicamp.ic.lsd.mercurius.persistence.dao.CustomerDAO;

class CustomerMgtImpl {

	private CustomerManager manager;
	private CustomerDAO customerDAO;

	CustomerMgtImpl(CustomerManager manager) {
		super();
		this.manager = manager;
		customerDAO = this.manager.getCustomerDAO();
	}

	Customer registerCustomer(Customer customer) throws DuplicatedEmailExcpetion, DuplicatedDocumentException {
		customer.setRegisterDate(new Date());

		Map<String, Object> parameters = new HashMap<>();
		List<Customer> resultList;
		parameters.put("emailAddress", customer.getEmailAddress());
		resultList = customerDAO.getListFromQuery("from Customer where emailAddress = :emailAddress", parameters);
		if (CollectionUtils.isNotEmpty(resultList)) {
			throw new DuplicatedEmailExcpetion();
		}

		parameters = new HashMap<>();
		parameters.put("document", customer.getEmailAddress());
		resultList = customerDAO.getListFromQuery("from Customer where document = :document", parameters);
		if (CollectionUtils.isNotEmpty(resultList)) {
			throw new DuplicatedDocumentException();
		}

		return customerDAO.merge(customer);
	}

	Customer editCustomer(Customer customer) {
		return customerDAO.merge(customer);
	}

	void deleteCustomer(Customer customer) {
		customerDAO.removeById(customer.getId());
	}

	Customer login(String email, String password) {
		return customerDAO.getCustomer(email, password);
	}

	void logout(Customer customer) {
		Integer numberOfLogons = customer.getNumberOfLogons();
		if (numberOfLogons == null) {
			numberOfLogons = 0;
		}
		numberOfLogons++;
		customer.setNumberOfLogons(numberOfLogons);

		customer.setLastLogon(new Date());
		customerDAO.merge(customer);
	}

	void setCustomerDefaultAddress(Customer customer, Address address) {
		customer.setDefaultAddress(address);
		customerDAO.merge(customer);
	}

	Customer loadCustomerAddresses(Customer customer) {
		return customerDAO.loadCustomerAddresses(customer);
	}

}
