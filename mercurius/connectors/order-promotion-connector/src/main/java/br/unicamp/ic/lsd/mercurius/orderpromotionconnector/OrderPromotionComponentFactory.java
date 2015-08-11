package br.unicamp.ic.lsd.mercurius.orderpromotionconnector;

import br.unicamp.ic.sed.cosmos.IManager;

public class OrderPromotionComponentFactory {

	private OrderPromotionComponentFactory() {
		super();
	}

	public static IManager createInstance() {
		return new Manager();
	}

}
