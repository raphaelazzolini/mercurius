package br.unicamp.ic.lsd.mercurius.persistence.entities;

import javax.ejb.Local;
import javax.ejb.Singleton;

import br.unicamp.ic.lsd.mercurius.datatype.OrderStatus;
import br.unicamp.ic.lsd.mercurius.datatype.factory.OrderStatusFactory;

@Singleton(name = "orderStatusFactory")
@Local(OrderStatusFactory.class)
public class OrderStatusFactoryImpl implements OrderStatusFactory {

	private OrderStatusFactoryImpl() {
		super();
	}

	@Override
	public OrderStatus createInstance() {
		return new OrderStatusImpl();
	}

}
