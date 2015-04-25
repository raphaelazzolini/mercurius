package br.unicamp.ic.lsd.mercurius.persistence.entities;

import javax.ejb.Local;
import javax.ejb.Singleton;

import br.unicamp.ic.lsd.mercurius.datatype.PaymentMethod;
import br.unicamp.ic.lsd.mercurius.datatype.factory.PaymentMethodFactory;

@Singleton(name = "paymentMethodFactory")
@Local(PaymentMethodFactory.class)
public class PaymentMethodFactoryImpl implements PaymentMethodFactory {

	@Override
	public PaymentMethod createInstance() {
		return new PaymentMethodImpl();
	}

}
