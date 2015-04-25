package br.unicamp.ic.lsd.mercurius.addressmgr.impl;

import br.unicamp.ic.lsd.mercurius.addressmgr.spec.prov.AddressManager;
import br.unicamp.ic.lsd.mercurius.addressmgr.spec.prov.AddressMgt;
import br.unicamp.ic.lsd.mercurius.datatype.Address;

class AddressFacade implements AddressMgt {

	private AddressManager manager;
	private AddressMgtImpl addressMgtImpl;

	AddressFacade(AddressManager manager) {
		super();
		this.manager = manager;
		this.addressMgtImpl = new AddressMgtImpl(this.manager);
	}

	@Override
	public Address saveAddress(Address address) {
		return addressMgtImpl.saveAddress(address);
	}

	@Override
	public void deleteAddress(Address address) {
		addressMgtImpl.deleteAddress(address);
	}

	@Override
	public Address newAddress() {
		return manager.getAddressDAO().newInstance();
	}

}
