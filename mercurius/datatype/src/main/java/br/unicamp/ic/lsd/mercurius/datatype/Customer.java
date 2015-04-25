package br.unicamp.ic.lsd.mercurius.datatype;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public interface Customer extends Serializable {

	/**
	 * Returns the customer id
	 * 
	 * @return
	 */
	Integer getId();

	List<Address> getAddresses();

	void setAddresses(List<Address> address);

	Basket getBasket();

	void setBasket(Basket basket);

	Date getBirthDate();

	void setBirthDate(Date birthDate);

	List<CustomerGroup> getCustomerGroups();

	void setCustomerGroups(List<CustomerGroup> customerGroups);

	Address getDefaultAddress();

	void setDefaultAddress(Address address);

	String getEmailAddress();

	void setEmailAddress(String emailAddress);

	String getTelephoneNumber();

	void setTelephoneNumber(String telephoneNumber);

	String getMobilePhoneNumber();

	void setMobilePhoneNumber(String mobilePhoneNumber);

	String getFirstName();

	void setFirstName(String firstName);

	String getLastName();

	void setLastName(String lastName);

	Character getGender();

	void setGender(Character gender);

	Date getLastLogon();

	void setLastLogon(Date lastLogon);

	Integer getNumberOfLogons();

	void setNumberOfLogons(Integer numberOfLogons);

	String getPassword();

	void setPassword(String password);

	List<Order> getOrders();

	void setOrders(List<Order> order);

	Date getRegisterDate();

	void setRegisterDate(Date date);

	String getDocument();

	void setDocument(String document);

}
