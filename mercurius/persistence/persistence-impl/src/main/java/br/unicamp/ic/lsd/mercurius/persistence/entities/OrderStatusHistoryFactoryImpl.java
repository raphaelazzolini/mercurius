package br.unicamp.ic.lsd.mercurius.persistence.entities;

import javax.ejb.Local;
import javax.ejb.Singleton;

import br.unicamp.ic.lsd.mercurius.datatype.OrderStatusHistory;
import br.unicamp.ic.lsd.mercurius.datatype.factory.OrderStatusHistoryFactory;

@Singleton(name = "orderStatusHistoryFactory")
@Local(OrderStatusHistoryFactory.class)
public class OrderStatusHistoryFactoryImpl implements OrderStatusHistoryFactory {

	private OrderStatusHistoryFactoryImpl() {
		super();
	}

	@Override
	public OrderStatusHistory createInstance() {
		return new OrderStatusHistoryImpl();
	}

}
