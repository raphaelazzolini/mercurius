package br.unicamp.ic.lsd.mercurius.datatype;

import java.io.Serializable;

public interface Address extends Serializable {

	Integer getId();

	String getCity();

	void setCity(String city);

	String getEmailAddress();

	void setEmailAddress(String emailAddress);

	String getAddressName();

	void setAddressName(String addressName);

	String getName();

	void setName(String name);

	String getPostCode();

	void setPostCode(String postCode);

	String getState();

	void setState(String state);

	String getStreetAddress1();

	void setStreetAddress1(String streetAddress1);

	String getStreetAddress2();

	void setStreetAddress2(String streetAddress2);

	String getSuburb();

	void setSuburb(String suburb);

	String getTelephoneNumber();

	void setTelephoneNumber(String telephoneNumber);

	Customer getCustomer();

	void setCustomer(Customer customer);

}
