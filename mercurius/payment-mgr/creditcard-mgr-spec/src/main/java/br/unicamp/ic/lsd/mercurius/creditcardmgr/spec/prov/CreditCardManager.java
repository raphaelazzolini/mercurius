package br.unicamp.ic.lsd.mercurius.creditcardmgr.spec.prov;

import br.unicamp.ic.lsd.mercurius.datatype.factory.PaymentFactory;
import br.unicamp.ic.lsd.mercurius.datatype.factory.PaymentMethodFactory;
import br.unicamp.ic.lsd.mercurius.datatype.factory.PaymentNotificationFactory;
import br.unicamp.ic.sed.cosmos.IManager;

public interface CreditCardManager extends IManager {

	PaymentFactory getPaymentFactory();

	PaymentMethodFactory getPaymentMethodFactory();

	PaymentNotificationFactory getPaymentNotificationFactory();

}
