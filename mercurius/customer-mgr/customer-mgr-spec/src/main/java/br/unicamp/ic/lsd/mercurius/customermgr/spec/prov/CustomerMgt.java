package br.unicamp.ic.lsd.mercurius.customermgr.spec.prov;

import java.util.List;

import br.unicamp.ic.lsd.mercurius.datatype.Address;
import br.unicamp.ic.lsd.mercurius.datatype.Customer;
import br.unicamp.ic.lsd.mercurius.excpetionhandler.exceptions.DuplicatedDocumentException;
import br.unicamp.ic.lsd.mercurius.excpetionhandler.exceptions.DuplicatedEmailExcpetion;

public interface CustomerMgt {

	/**
	 * Realizes the customers login and returns the created session id.
	 * 
	 * @param email
	 * @param password
	 * @return the logged customer or <code>null</code> if the login was not
	 *         successful.
	 */
	Customer login(String email, String password);

	/**
	 * Realizes the login with a customer's social network id, like facebook or
	 * twitter.
	 * 
	 * @param socialNetworkId
	 * @return the logged customer or <code>null</code> if the login was not
	 *         successful.
	 */
	Customer socialNetworkLogin(String socialNetworkId);

	/**
	 * Realizes the login with a saved session id.
	 * 
	 * @param sessionId
	 * @return the logged customer or <code>null</code> if the login was not
	 *         successful.
	 */
	Customer loginBySession(String sessionId);

	/**
	 * Invalidates the customers session id.
	 */
	void logout(Customer customer);

	/**
	 * Saves the customer's session so he can login with the method
	 * {@link CustomerManager#loginBySession(String)}.
	 * 
	 * @param session
	 */
	void saveCustomerSession(String session);

	/**
	 * Register a new customer in the system.
	 * 
	 * @param customer
	 * @return
	 */
	Customer registerCustomer(Customer customer) throws DuplicatedEmailExcpetion, DuplicatedDocumentException;

	/**
	 * Updates the customer's register.
	 * 
	 * @param customer
	 * @return
	 */
	Customer editCustomer(Customer customer);

	/**
	 * Removes the customer from the system.
	 * 
	 * @param customer
	 */
	void deleteCustomer(Customer customer);

	/**
	 * Loads the customer's {@link Address} {@link List}.
	 * 
	 * @param customer
	 * @return
	 */
	Customer loadCustomerAddresses(Customer customer);

	/**
	 * Makes the informed address be the default address of the customer.
	 * 
	 * @param customer
	 * @param address
	 */
	void setCustomerDefaultAddress(Customer customer, Address address);

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
	 * Changes the customer password.
	 * 
	 * @param customer
	 * @param oldPassword
	 * @param newPassword
	 * @return
	 */
	Customer changePassword(Customer customer, String oldPassword, String newPassword);

	/**
	 * Creates a new {@link Customer} instance.
	 * 
	 * @return
	 */
	Customer newCustomer();

	/**
	 * Creates a new {@link Address} instance.
	 * 
	 * @return
	 */
	Address newAddress();

	List<Customer> getCustomers();
}
