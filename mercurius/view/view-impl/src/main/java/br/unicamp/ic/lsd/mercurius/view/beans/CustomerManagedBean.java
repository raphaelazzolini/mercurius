package br.unicamp.ic.lsd.mercurius.view.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;

import br.unicamp.ic.lsd.mercurius.datatype.Customer;
import br.unicamp.ic.lsd.mercurius.view.spec.req.ViewCustomerMgt;
import br.unicamp.ic.lsd.mercurius.viewcustomerconnector.ViewCustomerConnectorFactory;
import br.unicamp.ic.sed.cosmos.IManager;

@Named("customerManagedBean")
@SessionScoped
public class CustomerManagedBean implements Serializable {

	private static final long serialVersionUID = -6325583753256205538L;

	private String emailLogin;
	private String passwordLogin;
	private Customer customer;

	private IManager viewCustomerConnector;
	private ViewCustomerMgt customerMgt;

	@PostConstruct
	public void init() {
		viewCustomerConnector = ViewCustomerConnectorFactory.createInstance();
		customerMgt = (ViewCustomerMgt) viewCustomerConnector.getProvidedInterface("ViewCustomerMgt");
	}

	public String login() {
		if (StringUtils.isNotBlank(emailLogin) && StringUtils.isNotBlank(passwordLogin)) {
			customer = customerMgt.login(emailLogin, passwordLogin);
			if (customer != null) {
				return "index";
			}
		}
		return null;
	}

	public String logout() {
		customerMgt.logout(customer);
		customer = null;
		emailLogin = null;
		passwordLogin = null;
		return "login";
	}

	public String loginPage() {
		return "login";
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getEmailLogin() {
		return emailLogin;
	}

	public void setEmailLogin(String emailLogin) {
		this.emailLogin = emailLogin;
	}

	public String getPasswordLogin() {
		return passwordLogin;
	}

	public void setPasswordLogin(String passwordLogin) {
		this.passwordLogin = passwordLogin;
	}

}
