package br.unicamp.ic.lsd.mercurius.view.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;

import br.unicamp.ic.lsd.mercurius.datatype.Address;
import br.unicamp.ic.lsd.mercurius.datatype.Customer;
import br.unicamp.ic.lsd.mercurius.excpetionhandler.exceptions.DuplicatedDocumentException;
import br.unicamp.ic.lsd.mercurius.excpetionhandler.exceptions.DuplicatedEmailExcpetion;
import br.unicamp.ic.lsd.mercurius.view.spec.req.ViewCustomerMgt;
import br.unicamp.ic.lsd.mercurius.viewcustomerconnector.ViewCustomerConnectorFactory;
import br.unicamp.ic.sed.cosmos.IManager;

@Named("cadastroManagedBean")
@ConversationScoped
public class CadastroManagedBean implements Serializable {

	private static final long serialVersionUID = 7989254509074577296L;

	private Customer customer;
	private Address address;
	private String password;
	private String passwordConfirm;
	private String numeroEndereco;
	private String emailCadastro;

	private IManager viewCustomerConnector;
	private ViewCustomerMgt customerMgt;

	@Inject
	private Instance<CustomerManagedBean> customerManagedBean;

	@PostConstruct
	public void init() {
		viewCustomerConnector = ViewCustomerConnectorFactory.createInstance();
		customerMgt = (ViewCustomerMgt) viewCustomerConnector.getProvidedInterface("ViewCustomerMgt");
		customer = customerMgt.newCustomer();
		address = customerMgt.newAddress();
	}

	public String novoCadastro() {
		if (customerManagedBean.get().getCustomer() == null && StringUtils.isNoneBlank(emailCadastro)) {
			customer = customerMgt.newCustomer();
			customer.setEmailAddress(emailCadastro);
			return "cadastro";
		}
		return null;
	}

	public String cadastrar() throws DuplicatedEmailExcpetion, DuplicatedDocumentException {
		if (password.equals(passwordConfirm)) {
			customer.setPassword(password);
			customer = customerMgt.registerCustomer(customer, address, password, passwordConfirm);
			if (customer.getId() != null) {
				customerManagedBean.get().setCustomer(customer);
				return "index";
			}
		}
		return null;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getNumeroEndereco() {
		return numeroEndereco;
	}

	public void setNumeroEndereco(String numeroEndereco) {
		this.numeroEndereco = numeroEndereco;
	}

	public String getEmailCadastro() {
		return emailCadastro;
	}

	public void setEmailCadastro(String emailCadastro) {
		this.emailCadastro = emailCadastro;
	}

}
