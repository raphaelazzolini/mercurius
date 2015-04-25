package br.unicamp.ic.lsd.mercurius.persistence.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.unicamp.ic.lsd.mercurius.datatype.Address;
import br.unicamp.ic.lsd.mercurius.datatype.Basket;
import br.unicamp.ic.lsd.mercurius.datatype.Customer;
import br.unicamp.ic.lsd.mercurius.datatype.CustomerGroup;
import br.unicamp.ic.lsd.mercurius.datatype.Order;

import com.google.common.base.Objects;

@Entity(name = "Customer")
@Table(name = "customer")
public class CustomerImpl implements Customer {

	private static final long serialVersionUID = 8950918929763650236L;

	protected CustomerImpl() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "customer_id")
	private Integer id;

	@Override
	public Integer getId() {
		return id;
	}

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, targetEntity = AddressImpl.class)
	private List<Address> addresses;

	@OneToOne(targetEntity = BasketImpl.class)
	@JoinColumn(name = "basket_id")
	private Basket basket;

	@Column(name = "birth_date", nullable = false)
	private Date birthDate;

	@ManyToMany(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH },
			targetEntity = CustomerGroupImpl.class)
	@JoinTable(name = "customer_to_customer_group", joinColumns = @JoinColumn(name = "customer_id",
			referencedColumnName = "customer_id"), inverseJoinColumns = @JoinColumn(name = "customer_group_id",
			referencedColumnName = "customer_group_id"))
	private List<CustomerGroup> customerGroups;

	@OneToOne(targetEntity = AddressImpl.class)
	@JoinColumn(name = "address_id")
	private Address defaultAddress;

	@Column(name = "email_address", nullable = false)
	private String emailAddress;

	@Column(name = "telephone_number")
	private String telephoneNumber;

	@Column(name = "mobile_phone_number")
	private String mobilePhoneNumber;

	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "last_name", nullable = false)
	private String lastName;

	@Column(name = "gender", nullable = false)
	private Character gender;

	@Column(name = "last_logon")
	private Date lastLogon;

	@Column(name = "number_of_logons")
	private Integer numberOfLogons;

	@Column(name = "password", nullable = false)
	private String password;

	@OneToMany(mappedBy = "customer", cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH }, targetEntity = OrderImpl.class)
	private List<Order> orders;

	@Column(name = "register_date", nullable = false)
	private Date registerDate;

	@Column(name = "document", nullable = false)
	private String document;

	@Override
	public List<Address> getAddresses() {
		return addresses;
	}

	@Override
	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	@Override
	public Basket getBasket() {
		return basket;
	}

	@Override
	public void setBasket(Basket basket) {
		this.basket = basket;
	}

	@Override
	public Date getBirthDate() {
		return birthDate;
	}

	@Override
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public List<CustomerGroup> getCustomerGroups() {
		return customerGroups;
	}

	@Override
	public void setCustomerGroups(List<CustomerGroup> customerGroups) {
		this.customerGroups = customerGroups;
	}

	@Override
	public Address getDefaultAddress() {
		return defaultAddress;
	}

	@Override
	public void setDefaultAddress(Address defaultAddress) {
		this.defaultAddress = defaultAddress;
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
	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	@Override
	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	@Override
	public String getMobilePhoneNumber() {
		return mobilePhoneNumber;
	}

	@Override
	public void setMobilePhoneNumber(String mobilePhoneNumber) {
		this.mobilePhoneNumber = mobilePhoneNumber;
	}

	@Override
	public String getFirstName() {
		return firstName;
	}

	@Override
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Override
	public String getLastName() {
		return lastName;
	}

	@Override
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public Character getGender() {
		return gender;
	}

	@Override
	public void setGender(Character gender) {
		this.gender = gender;
	}

	@Override
	public Date getLastLogon() {
		return lastLogon;
	}

	@Override
	public void setLastLogon(Date lastLogon) {
		this.lastLogon = lastLogon;
	}

	@Override
	public Integer getNumberOfLogons() {
		return numberOfLogons;
	}

	@Override
	public void setNumberOfLogons(Integer numberOfLogons) {
		this.numberOfLogons = numberOfLogons;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public List<Order> getOrders() {
		return orders;
	}

	@Override
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	@Override
	public Date getRegisterDate() {
		return registerDate;
	}

	@Override
	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String getDocument() {
		return document;
	}

	@Override
	public void setDocument(String document) {
		this.document = document;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id);
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof CustomerImpl) {
			CustomerImpl that = (CustomerImpl) object;
			return Objects.equal(this.id, that.id);
		}
		return false;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CustomerImpl [id=");
		builder.append(id);
		builder.append(", addresses=");
		builder.append(addresses);
		builder.append(", basket=");
		builder.append(basket);
		builder.append(", birthDate=");
		builder.append(birthDate);
		builder.append(", customerGroups=");
		builder.append(customerGroups);
		builder.append(", defaultAddress=");
		builder.append(defaultAddress);
		builder.append(", emailAddress=");
		builder.append(emailAddress);
		builder.append(", telephoneNumber=");
		builder.append(telephoneNumber);
		builder.append(", mobilePhoneNumber=");
		builder.append(mobilePhoneNumber);
		builder.append(", firstName=");
		builder.append(firstName);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append(", gender=");
		builder.append(gender);
		builder.append(", lastLogon=");
		builder.append(lastLogon);
		builder.append(", numberOfLogons=");
		builder.append(numberOfLogons);
		builder.append(", password=");
		builder.append(password);
		builder.append(", orders=");
		builder.append(orders);
		builder.append(", registerDate=");
		builder.append(registerDate);
		builder.append("]");
		return builder.toString();
	}

}
