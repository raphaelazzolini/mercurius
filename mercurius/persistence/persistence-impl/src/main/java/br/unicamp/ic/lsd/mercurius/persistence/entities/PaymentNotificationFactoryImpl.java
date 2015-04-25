package br.unicamp.ic.lsd.mercurius.persistence.entities;

import javax.ejb.Local;
import javax.ejb.Singleton;

import br.unicamp.ic.lsd.mercurius.datatype.PaymentNotification;
import br.unicamp.ic.lsd.mercurius.datatype.factory.PaymentNotificationFactory;

@Singleton(name = "paymentNotificationFactory")
@Local(PaymentNotificationFactory.class)
public class PaymentNotificationFactoryImpl implements PaymentNotificationFactory {

	@Override
	public PaymentNotification createInstance() {
		return new PaymentNotificationImpl();
	}

}
