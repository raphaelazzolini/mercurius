package br.unicamp.ic.lsd.mercurius.creditcardmgr.spec.prov;

import java.math.BigDecimal;

import br.unicamp.ic.lsd.mercurius.datatype.Payment;
import br.unicamp.ic.lsd.mercurius.datatype.PaymentMethod;
import br.unicamp.ic.lsd.mercurius.datatype.PaymentNotification;

public interface CreditCardMgt {

	/**
	 * Returns the credit card payment method.
	 * 
	 * @param order
	 * @return
	 */
	PaymentMethod getPaymentMethod();

	/**
	 * Creates a payment
	 * 
	 * @param value
	 * @param paymentInformation
	 * @return
	 */
	Payment createPayment(BigDecimal value, String paymentInformation);

	/**
	 * Makes a payment and returns a {@link PaymentNotification} with the result
	 * of the payment try.
	 * 
	 * @param payment
	 * @return
	 */
	PaymentNotification makeAPayment(Payment payment);

}
