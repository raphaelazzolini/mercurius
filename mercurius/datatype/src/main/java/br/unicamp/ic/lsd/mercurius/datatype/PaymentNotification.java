package br.unicamp.ic.lsd.mercurius.datatype;

import java.io.Serializable;

public interface PaymentNotification extends Serializable {

	/**
	 * Returns the payment status
	 * 
	 * @return
	 */
	PaymentStatus getPaymentStatus();

	/**
	 * Sets the payment status
	 * 
	 * @param status
	 */
	void setPaymentStatus(PaymentStatus status);

	/**
	 * Return the order.
	 * 
	 * @return
	 */
	Order getOrder();

	/**
	 * Sets the order.
	 * 
	 * @param order
	 */
	void setOrder(Order order);

}
