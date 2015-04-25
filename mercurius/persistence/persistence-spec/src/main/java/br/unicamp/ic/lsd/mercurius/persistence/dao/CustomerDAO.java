package br.unicamp.ic.lsd.mercurius.persistence.dao;

import br.unicamp.ic.lsd.mercurius.datatype.Customer;

public interface CustomerDAO extends DAO<Customer, Integer> {

	Customer getCustomer(String email, String password);

	Customer loadCustomerAddresses(Customer customer);

}
