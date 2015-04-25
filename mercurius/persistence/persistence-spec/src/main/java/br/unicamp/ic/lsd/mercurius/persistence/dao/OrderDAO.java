package br.unicamp.ic.lsd.mercurius.persistence.dao;

import java.util.List;

import br.unicamp.ic.lsd.mercurius.datatype.Order;
import br.unicamp.ic.lsd.mercurius.datatype.OrderStatus;

public interface OrderDAO extends DAO<Order, Integer> {

	/**
	 * Return a {@link List} with {@link Order} entities with the informed
	 * {@link OrderStatus}.
	 * 
	 * @param orderStatus
	 * @return
	 */
	List<Order> getOrdersWithOrderStatus(OrderStatus orderStatus);

	/**
	 * Loads the order payment notifications.
	 */
	void loadOrderPaymentNotifications(Order order);

}
