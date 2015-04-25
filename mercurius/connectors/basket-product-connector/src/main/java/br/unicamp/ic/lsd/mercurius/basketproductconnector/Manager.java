package br.unicamp.ic.lsd.mercurius.basketproductconnector;

import br.unicamp.ic.lsd.mercurius.basketmgr.spec.req.BasketProductMgt;
import br.unicamp.ic.lsd.mercurius.productmgr.impl.ProductMgrComponentFactory;
import br.unicamp.ic.lsd.mercurius.productmgr.spec.prov.ProductMgt;
import br.unicamp.ic.sed.cosmos.AManager;
import br.unicamp.ic.sed.cosmos.IManager;

class Manager extends AManager implements ProductBasketConnectorManager {

	private static final String I_MANAGER = "IManager";
	private static final String PRODUCT_MGT = "ProductMgt";
	private static final String BASKET_PRODUCT_MGT = "BasketProductMgt";

	Manager() {
		super();
		setProvidedInterfaceType(I_MANAGER, IManager.class);
		setProvidedInterfaceType(BASKET_PRODUCT_MGT, BasketProductMgt.class);
		setRequiredInterfaceType(PRODUCT_MGT, ProductMgt.class);

		IManager productManager = ProductMgrComponentFactory.createInstance();
		setRequiredInterface(PRODUCT_MGT, productManager.getProvidedInterface(PRODUCT_MGT));

		setProvidedInterface(BASKET_PRODUCT_MGT, new ProductMgtAdapter(this));
		setProvidedInterface(I_MANAGER, this);
	}

}
