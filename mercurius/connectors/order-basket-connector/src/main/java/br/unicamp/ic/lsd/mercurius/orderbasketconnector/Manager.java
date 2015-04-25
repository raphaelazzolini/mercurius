package br.unicamp.ic.lsd.mercurius.orderbasketconnector;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.unicamp.ic.lsd.mercurius.basketmgr.impl.BasketMgrComponentFactory;
import br.unicamp.ic.lsd.mercurius.basketmgr.spec.prov.BasketMgt;
import br.unicamp.ic.lsd.mercurius.ordermgr.spec.req.OrderBasketMgt;
import br.unicamp.ic.sed.cosmos.AManager;
import br.unicamp.ic.sed.cosmos.IManager;

@Stateless(name = "basketOrderConnectorManager")
@EJB(name = "ejb/basketOrderConnectorManager", beanInterface = BasketOrderConnectorManager.class,
		beanName = "basketOrderConnectorManager")
public class Manager extends AManager implements BasketOrderConnectorManager {

	private static final String ORDER_BASKET_MGT = "OrderBasketMgt";
	private static final String I_MANAGER = "IManager";
	private static final String BASKET_MGT = "BasketMgt";

	Manager() {
		super();
		setProvidedInterfaceType(I_MANAGER, IManager.class);
		setRequiredInterfaceType(BASKET_MGT, BasketMgt.class);
		setProvidedInterfaceType(ORDER_BASKET_MGT, OrderBasketMgt.class);

		IManager basketManager = BasketMgrComponentFactory.createInstance();
		setRequiredInterface(BASKET_MGT, basketManager.getProvidedInterface(BASKET_MGT));

		setProvidedInterface(I_MANAGER, this);
		setProvidedInterface(ORDER_BASKET_MGT, new BasketMgtAdapter(this));
	}

}
