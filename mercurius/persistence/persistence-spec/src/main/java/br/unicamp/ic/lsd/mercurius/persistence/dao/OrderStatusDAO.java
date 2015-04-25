package br.unicamp.ic.lsd.mercurius.persistence.dao;

import br.unicamp.ic.lsd.mercurius.datatype.OrderStatus;

public interface OrderStatusDAO extends DAO<OrderStatus, Integer> {

	OrderStatus getFirstOrderStatus();

	OrderStatus getCanceledOrderStatus();

	OrderStatus getPaymentConfirmedOrderStatus();

	OrderStatus getPaymentFailedOrderStatus();

	OrderStatus getOrderSentStatus();

	OrderStatus getOrderArrivedStatus();

}
