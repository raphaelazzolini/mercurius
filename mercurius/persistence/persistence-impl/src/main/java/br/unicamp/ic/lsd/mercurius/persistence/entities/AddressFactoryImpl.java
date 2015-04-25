package br.unicamp.ic.lsd.mercurius.persistence.entities;

import javax.ejb.Local;
import javax.ejb.Singleton;

import br.unicamp.ic.lsd.mercurius.datatype.Address;
import br.unicamp.ic.lsd.mercurius.datatype.factory.AddressFactory;

@Singleton(name = "addressFactory")
@Local(AddressFactory.class)
public class AddressFactoryImpl implements AddressFactory {

	@Override
	public Address createInstance() {
		return new AddressImpl();
	}

}
