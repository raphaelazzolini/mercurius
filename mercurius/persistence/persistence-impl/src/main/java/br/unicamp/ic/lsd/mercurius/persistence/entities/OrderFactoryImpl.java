package br.unicamp.ic.lsd.mercurius.persistence.entities;

import javax.ejb.Local;
import javax.ejb.Singleton;

import br.unicamp.ic.lsd.mercurius.datatype.Order;
import br.unicamp.ic.lsd.mercurius.datatype.factory.OrderFactory;

@Singleton(name = "orderFactory")
@Local(OrderFactory.class)
public class OrderFactoryImpl implements OrderFactory {

	private OrderFactoryImpl() {
		super();
	}

	@Override
	public Order createInstance() {
		return new OrderImpl();
	}

}
