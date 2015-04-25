package br.unicamp.ic.lsd.mercurius.viewproductconnector;

import br.unicamp.ic.lsd.mercurius.productmgr.impl.ProductMgrComponentFactory;
import br.unicamp.ic.lsd.mercurius.productmgr.spec.prov.ProductMgt;
import br.unicamp.ic.lsd.mercurius.view.spec.req.ViewProductMgt;
import br.unicamp.ic.sed.cosmos.AManager;
import br.unicamp.ic.sed.cosmos.IManager;

class Manager extends AManager implements ViewProductConnectorManager {

	private static final String PRODUCT_MGT = "ProductMgt";
	private static final String VIEW_PRODUCT_MGT = "ViewProductMgt";
	private static final String I_MANAGER = "IManager";

	Manager() {
		super();
		setProvidedInterfaceType(I_MANAGER, IManager.class);
		setProvidedInterfaceType(VIEW_PRODUCT_MGT, ViewProductMgt.class);
		setRequiredInterfaceType(PRODUCT_MGT, ProductMgt.class);

		IManager productManager = ProductMgrComponentFactory.createInstance();
		setRequiredInterface(PRODUCT_MGT, productManager.getProvidedInterface(PRODUCT_MGT));

		setProvidedInterface(I_MANAGER, this);
		setProvidedInterface(VIEW_PRODUCT_MGT, new ViewProductAdapter(this));
	}

}
