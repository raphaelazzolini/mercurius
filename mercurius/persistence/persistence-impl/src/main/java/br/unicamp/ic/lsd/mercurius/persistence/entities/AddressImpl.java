package br.unicamp.ic.lsd.mercurius.persistence.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.unicamp.ic.lsd.mercurius.datatype.Address;
import br.unicamp.ic.lsd.mercurius.datatype.Customer;

import com.google.common.base.Objects;

@Entity(name = "Address")
@Table(name = "address")
public class AddressImpl implements Address {

	private static final long serialVersionUID = -7033328255769043764L;

	protected AddressImpl() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "address_id")
	private Integer id;

	@ManyToOne(targetEntity = CustomerImpl.class)
	@JoinColumn(name = "customer_id", nullable = false)
	private Customer customer;

	@Column(name = "city", nullable = false)
	private String city;

	@Column(name = "email_address")
	private String emailAddress;

	@Column(name = "addressName")
	private String addressName;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "post_code", nullable = false)
	private String postCode;

	@Column(name = "state", nullable = false)
	private String state;

	@Column(name = "street_address_1", nullable = false)
	private String streetAddress1;

	@Column(name = "street_address_2")
	private String streetAddress2;

	@Column(name = "suburb", nullable = false)
	private String suburb;

	@Column(name = "telephone_number")
	private String telephoneNumber;

	@Override
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public Customer getCustomer() {
		return customer;
	}

	@Override
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String getCity() {
		return city;
	}

	@Override
	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String getAddressName() {
		return addressName;
	}

	@Override
	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}

	@Override
	public String getEmailAddress() {
		return emailAddress;
	}

	@Override
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getPostCode() {
		return postCode;
	}

	@Override
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	@Override
	public String getState() {
		return state;
	}

	@Override
	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String getStreetAddress1() {
		return streetAddress1;
	}

	@Override
	public void setStreetAddress1(String streetAddress1) {
		this.streetAddress1 = streetAddress1;
	}

	@Override
	public String getStreetAddress2() {
		return streetAddress2;
	}

	@Override
	public void setStreetAddress2(String streetAddress2) {
		this.streetAddress2 = streetAddress2;
	}

	@Override
	public String getSuburb() {
		return suburb;
	}

	@Override
	public void setSuburb(String suburb) {
		this.suburb = suburb;
	}

	@Override
	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	@Override
	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id);
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof AddressImpl) {
			AddressImpl that = (AddressImpl) object;
			return Objects.equal(this.id, that.id);
		}
		return false;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AddressImpl [id=");
		builder.append(id);
		builder.append(", city=");
		builder.append(city);
		builder.append(", emailAddress=");
		builder.append(emailAddress);
		builder.append(", name=");
		builder.append(name);
		builder.append(", postCode=");
		builder.append(postCode);
		builder.append(", state=");
		builder.append(state);
		builder.append(", streetAddress1=");
		builder.append(streetAddress1);
		builder.append(", streetAddress2=");
		builder.append(streetAddress2);
		builder.append(", suburb=");
		builder.append(suburb);
		builder.append(", telephone_number=");
		builder.append(telephoneNumber);
		builder.append("]");
		return builder.toString();
	}

}
