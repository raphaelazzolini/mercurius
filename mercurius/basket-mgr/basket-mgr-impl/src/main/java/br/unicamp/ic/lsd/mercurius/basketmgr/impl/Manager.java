package br.unicamp.ic.lsd.mercurius.basketmgr.impl;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.unicamp.ic.lsd.mercurius.basketloggingconnector.BasketLoggingConnectorComponentFactory;
import br.unicamp.ic.lsd.mercurius.basketmgr.spec.prov.BasketManager;
import br.unicamp.ic.lsd.mercurius.basketmgr.spec.prov.BasketMgt;
import br.unicamp.ic.lsd.mercurius.basketmgr.spec.req.BasketLoggingMgt;
import br.unicamp.ic.lsd.mercurius.basketmgr.spec.req.BasketProductMgt;
import br.unicamp.ic.lsd.mercurius.basketmgr.spec.req.SaveBasketMgt;
import br.unicamp.ic.lsd.mercurius.basketproductconnector.ProductConnectorComponentFactory;
import br.unicamp.ic.lsd.mercurius.datatype.factory.BasketItemFactory;
import br.unicamp.ic.lsd.mercurius.persistence.dao.BasketDAO;
import br.unicamp.ic.sed.cosmos.AManager;
import br.unicamp.ic.sed.cosmos.IManager;

class Manager extends AManager implements BasketManager {

	private static final String BASKET_MGT = "BasketMgt";
	private static final String I_MANAGER = "IManager";
	private static final String SAVE_BASKET_MGT = "SaveBasketMgt";
	private static final String BASKET_PRODUCT_MGT = "BasketProductMgt";

	private BasketDAO basketDAO;

	private BasketItemFactory basketItemFactory;

	Manager() {
		super();
		setProvidedInterfaceType(BASKET_MGT, BasketMgt.class);
		setProvidedInterfaceType(I_MANAGER, IManager.class);
		setRequiredInterfaceType(BASKET_PRODUCT_MGT, BasketProductMgt.class);
		setRequiredInterfaceType(SAVE_BASKET_MGT, SaveBasketMgt.class);
		setRequiredInterfaceType("BasketLoggingMgt", BasketLoggingMgt.class);

		IManager productConnectorManager = ProductConnectorComponentFactory.createInstance();
		setRequiredInterface(BASKET_PRODUCT_MGT, productConnectorManager.getProvidedInterface(BASKET_PRODUCT_MGT));
		IManager loggingConnectorManager = BasketLoggingConnectorComponentFactory.createInstance();
		setRequiredInterface("BasketLoggingMgt", loggingConnectorManager.getProvidedInterface("BasketLoggingMgt"));

		setProvidedInterface(I_MANAGER, this);
		setProvidedInterface(BASKET_MGT, new BasketFacade(this));
	}

	@Override
	public BasketDAO getBasketDAO() {
		try {
			if (basketDAO == null) {
				Context context = new InitialContext();
				basketDAO = (BasketDAO) context.lookup("java:app/persistence/basketDAO");
			}
		} catch (NamingException e) {
		}
		return basketDAO;
	}

	@Override
	public BasketItemFactory getBasketItemFactory() {
		try {
			if (basketItemFactory == null) {
				Context context = new InitialContext();
				basketItemFactory = (BasketItemFactory) context.lookup("java:app/persistence/basketItemFactory");
			}
		} catch (NamingException e) {
		}
		return basketItemFactory;
	}
}
