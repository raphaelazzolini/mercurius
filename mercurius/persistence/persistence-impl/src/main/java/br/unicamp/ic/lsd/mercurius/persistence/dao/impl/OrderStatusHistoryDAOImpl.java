package br.unicamp.ic.lsd.mercurius.persistence.dao.impl;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import br.unicamp.ic.lsd.mercurius.datatype.OrderStatusHistory;
import br.unicamp.ic.lsd.mercurius.datatype.factory.OrderStatusHistoryFactory;
import br.unicamp.ic.lsd.mercurius.persistence.dao.OrderStatusHistoryDAO;

@Stateless(name = "orderStatusHistoryDAO")
@Local(OrderStatusHistoryDAO.class)
public class OrderStatusHistoryDAOImpl extends AbstractDAO<OrderStatusHistory, Integer> implements
		OrderStatusHistoryDAO {

	private static final long serialVersionUID = -6117425369285952126L;

	@EJB
	private OrderStatusHistoryFactory orderStatusHistoryFactory;

	private Class<? extends OrderStatusHistory> entityClass;

	@Override
	public OrderStatusHistory newInstance() {
		return orderStatusHistoryFactory.createInstance();
	}

	@Override
	protected Class<? extends OrderStatusHistory> getEntityClass() {
		if (entityClass == null) {
			OrderStatusHistory entity = orderStatusHistoryFactory.createInstance();
			entityClass = entity.getClass();
		}

		return entityClass;
	}

}
