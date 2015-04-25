package br.unicamp.ic.lsd.mercurius.view.spec.req;

import br.unicamp.ic.lsd.mercurius.datatype.Address;
import br.unicamp.ic.lsd.mercurius.datatype.Customer;
import br.unicamp.ic.lsd.mercurius.excpetionhandler.exceptions.DuplicatedDocumentException;
import br.unicamp.ic.lsd.mercurius.excpetionhandler.exceptions.DuplicatedEmailExcpetion;

public interface ViewCustomerMgt {

	Customer newCustomer();

	Address newAddress();

	Customer login(String email, String password);

	Customer registerCustomer(Customer customer, Address address, String password, String passwordConfirm)
			throws DuplicatedEmailExcpetion, DuplicatedDocumentException;

	void logout(Customer customer);

}
