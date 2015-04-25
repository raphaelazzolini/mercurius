package br.unicamp.ic.lsd.mercurius.addressmgr.impl;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.unicamp.ic.lsd.mercurius.addressmgr.spec.prov.AddressManager;
import br.unicamp.ic.lsd.mercurius.addressmgr.spec.prov.AddressMgt;
import br.unicamp.ic.lsd.mercurius.persistence.dao.AddressDAO;
import br.unicamp.ic.sed.cosmos.AManager;
import br.unicamp.ic.sed.cosmos.IManager;

class Manager extends AManager implements AddressManager {

	private static final String ADDRESS_MGT = "AddressMgt";
	private static final String I_MANAGER = "IManager";

	private AddressDAO addressDAO;

	Manager() {
		super();
		try {
			Context context = new InitialContext();
			addressDAO = (AddressDAO) context.lookup("java:app/persistence/addressDAO");
			setProvidedInterface(I_MANAGER, this);
			setProvidedInterfaceType(I_MANAGER, IManager.class);
			setProvidedInterfaceType(ADDRESS_MGT, AddressMgt.class);
			setProvidedInterface(ADDRESS_MGT, new AddressFacade(this));
		} catch (NamingException e) {
		}
	}

	@Override
	public AddressDAO getAddressDAO() {
		return addressDAO;
	}

}
