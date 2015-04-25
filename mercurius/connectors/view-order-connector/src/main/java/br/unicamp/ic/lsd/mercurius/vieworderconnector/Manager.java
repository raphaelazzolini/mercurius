package br.unicamp.ic.lsd.mercurius.vieworderconnector;

import br.unicamp.ic.lsd.mercurius.ordermgr.impl.OrderMgrComponentFactory;
import br.unicamp.ic.lsd.mercurius.ordermgr.spec.prov.OrderMgt;
import br.unicamp.ic.lsd.mercurius.view.spec.req.ViewOrderMgt;
import br.unicamp.ic.sed.cosmos.AManager;
import br.unicamp.ic.sed.cosmos.IManager;

class Manager extends AManager implements IManager {

	private static final String ORDER_MGT = "OrderMgt";
	private static final String I_MANAGER = "IManager";
	private static final String VIEW_ORDER_MGT = "ViewOrderMgt";

	Manager() {
		super();
		setProvidedInterfaceType(I_MANAGER, IManager.class);
		setProvidedInterfaceType(VIEW_ORDER_MGT, ViewOrderMgt.class);
		setRequiredInterfaceType(ORDER_MGT, OrderMgt.class);

		IManager orderManager = OrderMgrComponentFactory.createInstance();
		setRequiredInterface(ORDER_MGT, orderManager.getProvidedInterface(ORDER_MGT));

		setProvidedInterface(I_MANAGER, this);
		setProvidedInterface(VIEW_ORDER_MGT, new ViewOrderAdapter(this));
	}

}
