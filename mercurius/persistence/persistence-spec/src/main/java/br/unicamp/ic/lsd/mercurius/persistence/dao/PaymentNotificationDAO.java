package br.unicamp.ic.lsd.mercurius.persistence.dao;

import br.unicamp.ic.lsd.mercurius.datatype.Order;
import br.unicamp.ic.lsd.mercurius.datatype.PaymentNotification;

public interface PaymentNotificationDAO extends DAO<PaymentNotification, Integer> {

	String getOrderTransactionId(Order order);

}
