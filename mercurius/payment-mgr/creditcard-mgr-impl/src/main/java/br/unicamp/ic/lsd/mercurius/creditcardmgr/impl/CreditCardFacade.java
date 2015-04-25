package br.unicamp.ic.lsd.mercurius.creditcardmgr.impl;

import java.math.BigDecimal;

import br.unicamp.ic.lsd.mercurius.creditcardmgr.spec.prov.CreditCardManager;
import br.unicamp.ic.lsd.mercurius.creditcardmgr.spec.prov.CreditCardMgt;
import br.unicamp.ic.lsd.mercurius.datatype.Payment;
import br.unicamp.ic.lsd.mercurius.datatype.PaymentMethod;
import br.unicamp.ic.lsd.mercurius.datatype.PaymentNotification;
import br.unicamp.ic.sed.cosmos.IManager;

class CreditCardFacade implements CreditCardMgt {

	private final CreditCardManager manager;
	private CreditCardMgtImpl creditCardMgtImpl;

	CreditCardFacade(IManager manager) {
		super();
		this.manager = (CreditCardManager) manager;
		this.creditCardMgtImpl = new CreditCardMgtImpl(this.manager);
	}

	@Override
	public PaymentMethod getPaymentMethod() {
		return creditCardMgtImpl.getPaymentMethod();
	}

	@Override
	public Payment createPayment(BigDecimal value, String paymentInformation) {
		return creditCardMgtImpl.createPayment(value, paymentInformation);
	}

	@Override
	public PaymentNotification makeAPayment(Payment payment) {
		return creditCardMgtImpl.makeAPayment(payment);
	}

}
