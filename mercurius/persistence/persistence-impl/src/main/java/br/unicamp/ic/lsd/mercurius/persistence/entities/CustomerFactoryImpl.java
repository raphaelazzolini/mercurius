package br.unicamp.ic.lsd.mercurius.persistence.entities;

import javax.ejb.Local;
import javax.ejb.Singleton;

import br.unicamp.ic.lsd.mercurius.datatype.Customer;
import br.unicamp.ic.lsd.mercurius.datatype.factory.CustomerFactory;

@Singleton(name = "customerFactory")
@Local(CustomerFactory.class)
public class CustomerFactoryImpl implements CustomerFactory {

	private CustomerFactoryImpl() {
		super();
	}

	@Override
	public Customer createInstance() {
		return new CustomerImpl();
	}

}
