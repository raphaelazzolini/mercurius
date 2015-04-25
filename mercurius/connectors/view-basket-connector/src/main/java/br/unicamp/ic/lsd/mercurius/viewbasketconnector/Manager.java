package br.unicamp.ic.lsd.mercurius.viewbasketconnector;

import br.unicamp.ic.lsd.mercurius.basketmgr.impl.BasketMgrComponentFactory;
import br.unicamp.ic.lsd.mercurius.basketmgr.spec.prov.BasketMgt;
import br.unicamp.ic.lsd.mercurius.view.spec.req.ViewBasketMgt;
import br.unicamp.ic.sed.cosmos.AManager;
import br.unicamp.ic.sed.cosmos.IManager;

class Manager extends AManager implements ViewBasketConnectorManager {

	private static final String I_MANAGER = "IManager";
	private static final String BASKET_MGT = "BasketMgt";
	private static final String VIEW_BASKET_MGT = "ViewBasketMgt";

	Manager() {
		super();
		setProvidedInterfaceType(VIEW_BASKET_MGT, ViewBasketMgt.class);
		setProvidedInterfaceType(I_MANAGER, IManager.class);
		setRequiredInterfaceType(BASKET_MGT, BasketMgt.class);

		IManager basketManager = BasketMgrComponentFactory.createInstance();
		BasketMgt basketMgt = (BasketMgt) basketManager.getProvidedInterface(BASKET_MGT);
		setRequiredInterface(BASKET_MGT, basketMgt);

		setProvidedInterface(I_MANAGER, this);
		setProvidedInterface(VIEW_BASKET_MGT, new BasketMgtAdapter(this));
	}

}
