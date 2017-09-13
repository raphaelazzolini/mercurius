package br.unicamp.ic.lsd.mercurius.persistence.dao.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import org.apache.commons.collections4.CollectionUtils;
import org.hibernate.Hibernate;

import br.unicamp.ic.lsd.mercurius.datatype.Customer;
import br.unicamp.ic.lsd.mercurius.datatype.factory.CustomerFactory;
import br.unicamp.ic.lsd.mercurius.persistence.dao.CustomerDAO;

@Stateless(name = "customerDAO")
@Local(CustomerDAO.class)
public class CustomerDAOImpl extends AbstractDAO<Customer, Integer> implements CustomerDAO {

	private static final String FROM_CUSTOMER_WHERE_EMAIL_ADDRESS_EMAIL_AND_PASSWORD_PASSWORD = "from Customer where emailAddress = :email and password = :password";

	private static final long serialVersionUID = -4130565006369216763L;

	@EJB
	private CustomerFactory customerFactory;

	private static Class<? extends Customer> entityClass;

	@Override
	public Customer newInstance() {
		return customerFactory.createInstance();
	}

	@Override
	protected Class<? extends Customer> getEntityClass() {
		if (entityClass == null) {
			Customer entity = customerFactory.createInstance();
			entityClass = entity.getClass();
		}
		return entityClass;
	}

	@Override
	public Customer getCustomer(String email, String password) {
		TypedQuery<? extends Customer> query = getEntityManager().createQuery(
				FROM_CUSTOMER_WHERE_EMAIL_ADDRESS_EMAIL_AND_PASSWORD_PASSWORD, getEntityClass());
		query.setParameter("email", email);
		query.setParameter("password", password);
		List<? extends Customer> resultList = query.getResultList();
		if (CollectionUtils.isNotEmpty(resultList)) {
			return resultList.get(0);
		}
		return null;
	}
	
	@Override
	public List<? extends Customer> getCustomers() {
		TypedQuery<? extends Customer> query = getEntityManager().createQuery(
				"from Customer", getEntityClass());
		List<? extends Customer> resultList = query.getResultList();
		if (CollectionUtils.isNotEmpty(resultList)) {
			return resultList;
		}
		return null;
	}

	@Override
	public Customer loadCustomerAddresses(Customer customer) {
		customer = findById(customer.getId());
		Hibernate.initialize(customer.getAddresses());
		return customer;
	}

}
