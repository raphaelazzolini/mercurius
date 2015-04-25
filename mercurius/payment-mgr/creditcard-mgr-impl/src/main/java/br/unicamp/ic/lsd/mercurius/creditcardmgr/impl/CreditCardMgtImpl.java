package br.unicamp.ic.lsd.mercurius.creditcardmgr.impl;

import java.math.BigDecimal;

import br.unicamp.ic.lsd.mercurius.creditcardmgr.spec.prov.CreditCardManager;
import br.unicamp.ic.lsd.mercurius.datatype.Payment;
import br.unicamp.ic.lsd.mercurius.datatype.PaymentMethod;
import br.unicamp.ic.lsd.mercurius.datatype.PaymentNotification;
import br.unicamp.ic.lsd.mercurius.datatype.PaymentStatus;

class CreditCardMgtImpl {

	private final CreditCardManager manager;

	CreditCardMgtImpl(CreditCardManager manager) {
		super();
		this.manager = manager;
	}

	public PaymentMethod getPaymentMethod() {
		PaymentMethod paymentMethod = manager.getPaymentMethodFactory().createInstance();
		paymentMethod.setName("Cartão de Crédito");
		return paymentMethod;
	}

	public Payment createPayment(BigDecimal value, String paymentInformation) {
		Payment payment = manager.getPaymentFactory().createInstance();
		payment.setPaymentMethod(getPaymentMethod());
		payment.setValue(value);
		payment.setPaymentInformation(paymentInformation);
		return payment;
	}

	public PaymentNotification makeAPayment(Payment payment) {
		PaymentNotification paymentNotification = manager.getPaymentNotificationFactory().createInstance();
		paymentNotification.setPaymentStatus(PaymentStatus.IN_PROGRESS);
		paymentNotification.setOrder(payment.getOrder());
		return paymentNotification;
	}

}
