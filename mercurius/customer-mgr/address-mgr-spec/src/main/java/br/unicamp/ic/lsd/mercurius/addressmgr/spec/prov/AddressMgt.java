package br.unicamp.ic.lsd.mercurius.addressmgr.spec.prov;

import br.unicamp.ic.lsd.mercurius.datatype.Address;

public interface AddressMgt {

	/**
	 * Saves the address.
	 * 
	 * @param address
	 * @return
	 */
	Address saveAddress(Address address);

	/**
	 * Removes the address.
	 * 
	 * @param address
	 */
	void deleteAddress(Address address);

	/**
	 * Creates a new {@link Address} instance.
	 * 
	 * @return
	 */
	Address newAddress();

}
