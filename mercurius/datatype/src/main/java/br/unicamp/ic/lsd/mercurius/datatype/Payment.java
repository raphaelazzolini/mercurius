package br.unicamp.ic.lsd.mercurius.datatype;

import java.io.Serializable;
import java.math.BigDecimal;

public interface Payment extends Serializable {

	/**
	 * Returns the payment method.
	 * 
	 * @return
	 */
	PaymentMethod getPaymentMethod();

	/**
	 * Sets the payment method.
	 * 
	 * @param paymentMethod
	 */
	void setPaymentMethod(PaymentMethod paymentMethod);

	/**
	 * Returns the payment value.
	 * 
	 * @return
	 */
	BigDecimal getValue();

	/**
	 * Sets the payment value.
	 * 
	 * @param paymentMethod
	 */
	void setValue(BigDecimal value);

	/**
	 * Returns the payment information.
	 * 
	 * @return
	 */
	String getPaymentInformation();

	/**
	 * Sets the payment information.
	 * 
	 * @param paymentMethod
	 */
	void setPaymentInformation(String information);

	/**
	 * Returns the payment order.
	 * 
	 * @return
	 */
	Order getOrder();

	/**
	 * Sets the payment order.
	 * 
	 * @param paymentMethod
	 */
	void setOrder(Order order);

}
