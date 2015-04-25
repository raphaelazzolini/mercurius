package br.unicamp.ic.lsd.mercurius.persistence.dao;

import java.util.Date;

import javax.ejb.EJB;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.unicamp.ic.lsd.mercurius.datatype.Basket;
import br.unicamp.ic.lsd.mercurius.datatype.Customer;
import br.unicamp.ic.lsd.mercurius.datatype.factory.CustomerFactory;
import br.unicamp.ic.lsd.mercurius.persistence.dao.BasketDAO;

public class BasketDAOTest extends DomainTestContext {

	@EJB
	private BasketDAO basketDAO;

	@EJB
	private CustomerFactory customerFactory;

	private Customer customer1;
	private Customer customer2;

	@Before
	public void setUp() {
		try {
			getUtx().begin();

			Basket basket = basketDAO.newInstance();
			customer1 = customerFactory.createInstance();
			customer1.setBirthDate(new Date());
			customer1.setEmailAddress("email");
			customer1.setFirstName("name");
			customer1.setLastName("lastName");
			customer1.setGender('M');
			customer1.setPassword("pass");
			customer1.setRegisterDate(new Date());
			customer1.setDocument("1234");
			customer1 = getEm().merge(customer1);
			basket.setCustomer(customer1);
			basket.setDateCreated(new Date());
			basket.setSessionId("3");
			basketDAO.merge(basket);

			basket = basketDAO.newInstance();
			customer2 = customerFactory.createInstance();
			customer2.setBirthDate(new Date());
			customer2.setEmailAddress("email");
			customer2.setFirstName("name");
			customer2.setLastName("lastName");
			customer2.setGender('M');
			customer2.setPassword("pass");
			customer2.setRegisterDate(new Date());
			customer2.setDocument("1234");
			customer2 = getEm().merge(customer2);
			basket.setCustomer(customer2);
			basket.setDateCreated(new Date());
			basket.setSessionId("4");
			basketDAO.merge(basket);

			basket = basketDAO.newInstance();
			basket.setDateCreated(new Date());
			basket.setSessionId("1");
			basketDAO.merge(basket);

			basket = basketDAO.newInstance();
			basket.setDateCreated(new Date());
			basket.setSessionId("2");
			basketDAO.merge(basket);

			getUtx().commit();
		} catch (NotSupportedException | SystemException | SecurityException | IllegalStateException
				| RollbackException | HeuristicMixedException | HeuristicRollbackException e) {
			try {
				getUtx().rollback();
			} catch (IllegalStateException | SecurityException | SystemException e1) {
			}
		}
	}

	@After
	public void cleanDb() {
		truncateTables("Basket", "Customer");
	}

	@Test
	public void testGetBasketFromCustomer() {
		Basket basket = basketDAO.getBasketFromCustomer(customer1);
		Assert.assertEquals(customer1, basket.getCustomer());
		Assert.assertEquals("3", basket.getSessionId());
	}

	@Test
	public void testGetBasketFromSessionId() {
		Basket basket = basketDAO.getBasketFromSession("2");
		Assert.assertNull(basket.getCustomer());
		Assert.assertEquals("2", basket.getSessionId());
	}

}
