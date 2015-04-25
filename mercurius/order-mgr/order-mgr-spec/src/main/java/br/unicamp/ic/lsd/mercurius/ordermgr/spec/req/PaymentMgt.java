package br.unicamp.ic.lsd.mercurius.ordermgr.spec.req;

import java.util.List;

import br.unicamp.ic.lsd.mercurius.datatype.Order;
import br.unicamp.ic.lsd.mercurius.datatype.Payment;
import br.unicamp.ic.lsd.mercurius.datatype.PaymentMethod;
import br.unicamp.ic.lsd.mercurius.datatype.PaymentNotification;

public interface PaymentMgt {

	/**
	 * Returns the available payment methods for the informed order.
	 * 
	 * @param order
	 * @return
	 */
	List<PaymentMethod> getPaymentMethods(Order order);

	/**
	 * Makes a payment and returns a {@link PaymentNotification} with the result
	 * of the payment try.
	 * 
	 * @param payment
	 * @return
	 */
	PaymentNotification makeAPayment(Payment payment);

	/**
	 * Return a {@link List} with the notification of the transaction with the
	 * informed id.
	 * 
	 * @param transactionId
	 * @return
	 */
	List<PaymentNotification> checkPaymentNotificatons(String transactionId);

	/**
	 * Creates a payment
	 * 
	 * @param value
	 * @param paymentInformation
	 * @param paymentMethod
	 * @return
	 */
	Payment createPayment(Order order, String paymentInformation, PaymentMethod paymentMethod);

}
