package br.unicamp.ic.lsd.mercurius.persistence.dao.impl;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import br.unicamp.ic.lsd.mercurius.datatype.OrderProduct;
import br.unicamp.ic.lsd.mercurius.datatype.factory.OrderProductFactory;
import br.unicamp.ic.lsd.mercurius.persistence.dao.OrderProductDAO;

@Stateless(name = "orderProductDAO")
@Local(OrderProductDAO.class)
public class OrderProductDAOImpl extends AbstractDAO<OrderProduct, Serializable> implements OrderProductDAO {

	private static final long serialVersionUID = -8869304137596324297L;

	@EJB
	private OrderProductFactory orderProductFactory;

	@Override
	public OrderProduct newInstance() {
		return null;
	}

	@Override
	protected Class<? extends OrderProduct> getEntityClass() {
		return null;
	}

}
