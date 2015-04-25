package br.unicamp.ic.lsd.mercurius.orderpaymentconnector;

import br.unicamp.ic.lsd.mercurius.creditcardmgr.impl.CreditCardMgrComponentFactory;
import br.unicamp.ic.lsd.mercurius.creditcardmgr.spec.prov.CreditCardMgt;
import br.unicamp.ic.lsd.mercurius.ordermgr.spec.req.PaymentMgt;
import br.unicamp.ic.sed.cosmos.AManager;
import br.unicamp.ic.sed.cosmos.IManager;

class Manager extends AManager implements IManager {

	Manager() {
		super();
		setProvidedInterfaceType("IManager", IManager.class);
		setProvidedInterfaceType("PaymentMgt", PaymentMgt.class);
		setRequiredInterfaceType("CreditCardMgt", CreditCardMgt.class);

		IManager creditCardManager = CreditCardMgrComponentFactory.createInstance();
		CreditCardMgt creditCardMgt = (CreditCardMgt) creditCardManager.getProvidedInterface("CreditCardMgt");
		setRequiredInterface("CreditCardMgt", creditCardMgt);

		setProvidedInterface("IManager", this);
		setProvidedInterface("PaymentMgt", new PaymentAdapter(this));
	}

}
