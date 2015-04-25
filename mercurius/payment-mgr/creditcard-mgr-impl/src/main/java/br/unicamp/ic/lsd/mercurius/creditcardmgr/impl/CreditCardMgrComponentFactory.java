package br.unicamp.ic.lsd.mercurius.creditcardmgr.impl;

import br.unicamp.ic.sed.cosmos.IManager;

public class CreditCardMgrComponentFactory {

	private CreditCardMgrComponentFactory() {
		super();
	}

	public static IManager createInstance() {
		return new Manager();
	}

}
