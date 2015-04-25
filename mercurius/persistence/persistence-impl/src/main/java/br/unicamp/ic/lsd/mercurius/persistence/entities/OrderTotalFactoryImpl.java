package br.unicamp.ic.lsd.mercurius.persistence.entities;

import javax.ejb.Local;
import javax.ejb.Singleton;

import br.unicamp.ic.lsd.mercurius.datatype.OrderTotal;
import br.unicamp.ic.lsd.mercurius.datatype.factory.OrderTotalFactory;

@Singleton(name = "orderTotalFactory")
@Local(OrderTotalFactory.class)
public class OrderTotalFactoryImpl implements OrderTotalFactory {

	private OrderTotalFactoryImpl() {
		super();
	}

	@Override
	public OrderTotal createInstance() {
		return new OrderTotalImpl();
	}

}
