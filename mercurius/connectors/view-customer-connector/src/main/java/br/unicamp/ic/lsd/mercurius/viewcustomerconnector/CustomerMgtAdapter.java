package br.unicamp.ic.lsd.mercurius.viewcustomerconnector;

import br.unicamp.ic.lsd.mercurius.customermgr.spec.prov.CustomerMgt;
import br.unicamp.ic.lsd.mercurius.datatype.Address;
import br.unicamp.ic.lsd.mercurius.datatype.Customer;
import br.unicamp.ic.lsd.mercurius.excpetionhandler.exceptions.DuplicatedDocumentException;
import br.unicamp.ic.lsd.mercurius.excpetionhandler.exceptions.DuplicatedEmailExcpetion;
import br.unicamp.ic.lsd.mercurius.view.spec.req.ViewCustomerMgt;
import br.unicamp.ic.sed.cosmos.IManager;

class CustomerMgtAdapter implements ViewCustomerMgt {

	private static final String CUSTOMER_MGT = "CustomerMgt";

	private final IManager manager;
	private CustomerMgt customerMgt;

	CustomerMgtAdapter(IManager manager) {
		super();
		this.manager = manager;
		this.customerMgt = (CustomerMgt) this.manager.getRequiredInterface(CUSTOMER_MGT);
	}

	@Override
	public Customer login(String email, String password) {
		return customerMgt.login(email, password);
	}

	@Override
	public Customer registerCustomer(Customer customer, Address address, String password, String passwordConfirm)
			throws DuplicatedEmailExcpetion, DuplicatedDocumentException {
		if (password.equals(passwordConfirm)) {
			customer.setPassword(password);
			customer = customerMgt.registerCustomer(customer);

			address.setCustomer(customer);
			StringBuilder nameBuilder = new StringBuilder(customer.getFirstName());
			nameBuilder.append(" ");
			nameBuilder.append(customer.getLastName());
			address.setName(nameBuilder.toString());
			address.setEmailAddress(customer.getEmailAddress());
			address.setTelephoneNumber(customer.getTelephoneNumber());

			customerMgt.saveAddress(address);

			customer.setDefaultAddress(address);
			customerMgt.editCustomer(customer);
			return customerMgt.login(customer.getEmailAddress(), password);
		}
		return null;
	}

	@Override
	public Customer newCustomer() {
		return customerMgt.newCustomer();
	}

	@Override
	public Address newAddress() {
		return customerMgt.newAddress();
	}

	@Override
	public void logout(Customer customer) {
		customerMgt.logout(customer);
	}
}
