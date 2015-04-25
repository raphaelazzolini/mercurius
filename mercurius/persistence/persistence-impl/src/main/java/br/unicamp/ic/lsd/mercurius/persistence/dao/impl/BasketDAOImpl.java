package br.unicamp.ic.lsd.mercurius.persistence.dao.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.commons.collections4.CollectionUtils;
import org.hibernate.Hibernate;

import br.unicamp.ic.lsd.mercurius.datatype.Basket;
import br.unicamp.ic.lsd.mercurius.datatype.Customer;
import br.unicamp.ic.lsd.mercurius.datatype.factory.BasketFactory;
import br.unicamp.ic.lsd.mercurius.persistence.dao.BasketDAO;

@Stateless(name = "basketDAO")
@Local(BasketDAO.class)
public class BasketDAOImpl extends AbstractDAO<Basket, Integer> implements BasketDAO {

	private static final long serialVersionUID = -9145564757539533052L;

	private static final String FROM_BASKET_WHERE_SESSION_ID_SESSION_ID = "from Basket where sessionId = :sessionId";
	private static final String FROM_BASKET_WHERE_CUSTOMER_CUSTOMER = "from Basket where customer = :customer";
	private static final String SESSION_ID = "sessionId";
	private static final String CUSTOMER = "customer";

	@EJB
	private BasketFactory basketFactory;

	private static Class<? extends Basket> entityClass;

	@Override
	public Basket newInstance() {
		return basketFactory.createInstance();
	}

	@Override
	protected Class<? extends Basket> getEntityClass() {
		if (entityClass == null) {
			Basket entity = basketFactory.createInstance();
			entityClass = entity.getClass();
		}
		return entityClass;
	}

	@Override
	public Basket getBasketFromCustomer(Customer customer) {
		EntityManager em = getEntityManager();
		TypedQuery<? extends Basket> query = em.createQuery(FROM_BASKET_WHERE_CUSTOMER_CUSTOMER, getEntityClass());
		query.setParameter(CUSTOMER, customer);
		List<? extends Basket> resultList = query.getResultList();
		if (CollectionUtils.isNotEmpty(resultList)) {
			return resultList.get(0);
		}
		return null;
	}

	@Override
	public Basket getBasketFromSession(String sessionId) {
		EntityManager em = getEntityManager();
		TypedQuery<? extends Basket> query = em.createQuery(FROM_BASKET_WHERE_SESSION_ID_SESSION_ID, getEntityClass());
		query.setParameter(SESSION_ID, sessionId);
		List<? extends Basket> resultList = query.getResultList();
		if (CollectionUtils.isNotEmpty(resultList)) {
			return resultList.get(0);
		}
		return null;
	}

	@Override
	public void loadBasketItems(Basket basket) {
		Hibernate.initialize(basket.getBasketItems());
	}

}
