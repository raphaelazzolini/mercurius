package br.unicamp.ic.lsd.mercurius.persistence.dao.impl;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import br.unicamp.ic.lsd.mercurius.datatype.OrderTotal;
import br.unicamp.ic.lsd.mercurius.datatype.factory.OrderTotalFactory;
import br.unicamp.ic.lsd.mercurius.persistence.dao.OrderTotalDAO;

@Stateless(name = "orderTotalDAO")
@Local(OrderTotalDAO.class)
public class OrderTotalDAOImpl extends AbstractDAO<OrderTotal, Integer> implements OrderTotalDAO {

	private static final long serialVersionUID = -192605764783011865L;

	@EJB
	private OrderTotalFactory orderTotalFactory;

	private Class<? extends OrderTotal> entityClass;

	@Override
	public OrderTotal newInstance() {
		return orderTotalFactory.createInstance();
	}

	@Override
	protected Class<? extends OrderTotal> getEntityClass() {
		if (entityClass == null) {
			entityClass = newInstance().getClass();
		}
		return entityClass;
	}

}
