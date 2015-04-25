package br.unicamp.ic.lsd.mercurius.addressmgr.impl;

import br.unicamp.ic.lsd.mercurius.addressmgr.spec.prov.AddressManager;
import br.unicamp.ic.lsd.mercurius.datatype.Address;

class AddressMgtImpl {

	private AddressManager manager;

	AddressMgtImpl(AddressManager manager) {
		super();
		this.manager = manager;
	}

	Address saveAddress(Address address) {
		return manager.getAddressDAO().merge(address);
	}

	void deleteAddress(Address address) {
		manager.getAddressDAO().removeById(address.getId());
	}

}
