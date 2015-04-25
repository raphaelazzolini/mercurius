package br.unicamp.ic.lsd.mercurius.persistence.entities;

import javax.ejb.Local;
import javax.ejb.Singleton;

import br.unicamp.ic.lsd.mercurius.datatype.Payment;
import br.unicamp.ic.lsd.mercurius.datatype.factory.PaymentFactory;

@Singleton(name = "paymentFactory")
@Local(PaymentFactory.class)
public class PaymentFactoryImpl implements PaymentFactory {

	@Override
	public Payment createInstance() {
		return new PaymentImpl();
	}

}
