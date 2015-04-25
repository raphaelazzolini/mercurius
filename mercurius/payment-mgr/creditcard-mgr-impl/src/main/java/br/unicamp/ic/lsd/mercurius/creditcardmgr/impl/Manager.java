package br.unicamp.ic.lsd.mercurius.creditcardmgr.impl;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.unicamp.ic.lsd.mercurius.creditcardmgr.spec.prov.CreditCardManager;
import br.unicamp.ic.lsd.mercurius.creditcardmgr.spec.prov.CreditCardMgt;
import br.unicamp.ic.lsd.mercurius.datatype.factory.PaymentFactory;
import br.unicamp.ic.lsd.mercurius.datatype.factory.PaymentMethodFactory;
import br.unicamp.ic.lsd.mercurius.datatype.factory.PaymentNotificationFactory;
import br.unicamp.ic.sed.cosmos.AManager;
import br.unicamp.ic.sed.cosmos.IManager;

class Manager extends AManager implements CreditCardManager {

	private PaymentFactory paymentFactory;
	private PaymentMethodFactory paymentMethodFactory;
	private PaymentNotificationFactory paymentNotificationFactory;

	Manager() {
		super();
		Context context;
		try {
			context = new InitialContext();
			paymentFactory = (PaymentFactory) context.lookup("java:app/persistence/paymentFactory");
			paymentMethodFactory = (PaymentMethodFactory) context.lookup("java:app/persistence/paymentMethodFactory");
			paymentNotificationFactory = (PaymentNotificationFactory) context
					.lookup("java:app/persistence/paymentNotificationFactory");

			setProvidedInterfaceType("IManager", IManager.class);
			setProvidedInterfaceType("CreditCardMgt", CreditCardMgt.class);
			setProvidedInterface("IManager", this);
			setProvidedInterface("CreditCardMgt", new CreditCardFacade(this));
		} catch (NamingException e) {
		}
	}

	@Override
	public PaymentFactory getPaymentFactory() {
		return paymentFactory;
	}

	@Override
	public PaymentMethodFactory getPaymentMethodFactory() {
		return paymentMethodFactory;
	}

	@Override
	public PaymentNotificationFactory getPaymentNotificationFactory() {
		return paymentNotificationFactory;
	}

}
