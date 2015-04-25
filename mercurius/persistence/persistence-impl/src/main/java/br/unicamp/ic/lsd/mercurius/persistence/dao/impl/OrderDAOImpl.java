package br.unicamp.ic.lsd.mercurius.persistence.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.hibernate.Hibernate;

import br.unicamp.ic.lsd.mercurius.datatype.Order;
import br.unicamp.ic.lsd.mercurius.datatype.OrderStatus;
import br.unicamp.ic.lsd.mercurius.datatype.factory.OrderFactory;
import br.unicamp.ic.lsd.mercurius.persistence.dao.OrderDAO;

@Stateless(name = "orderDAO")
@Local(OrderDAO.class)
public class OrderDAOImpl extends AbstractDAO<Order, Integer> implements OrderDAO {

	private static final long serialVersionUID = -8056774819453338332L;

	private static final String ORDER_STATUS = "orderStatus";
	private static final String FROM_ORDER_WHERE_ORDER_ORDER_STATUS_ORDER_STATUS = "from Order order where order.orderStatus = :orderStatus";

	@EJB
	private OrderFactory orderFactory;

	private static Class<? extends Order> entityClass;

	@Override
	public Order newInstance() {
		return orderFactory.createInstance();
	}

	@Override
	protected Class<? extends Order> getEntityClass() {
		if (entityClass == null) {
			Order entity = orderFactory.createInstance();
			entityClass = entity.getClass();
		}
		return entityClass;
	}

	@Override
	public List<Order> getOrdersWithOrderStatus(OrderStatus orderStatus) {
		EntityManager em = getEntityManager();
		TypedQuery<? extends Order> query = em.createQuery(FROM_ORDER_WHERE_ORDER_ORDER_STATUS_ORDER_STATUS,
				entityClass);
		query.setParameter(ORDER_STATUS, orderStatus);
		List<? extends Order> resultList = query.getResultList();
		List<Order> orders = new ArrayList<Order>();
		orders.addAll(resultList);
		return orders;
	}

	@Override
	public void loadOrderPaymentNotifications(Order order) {
		Hibernate.initialize(order.getPaymentNotifications());
	}

}
