package br.unicamp.ic.lsd.mercurius.viewproductsearchconnector;

import br.unicamp.ic.lsd.mercurius.productsearchmgr.impl.ProductSearchMgrComponentFactory;
import br.unicamp.ic.lsd.mercurius.productsearchmgr.spec.prov.ProductSearchMgt;
import br.unicamp.ic.lsd.mercurius.view.spec.req.ViewProductSearchMgt;
import br.unicamp.ic.sed.cosmos.AManager;
import br.unicamp.ic.sed.cosmos.IManager;

class Manager extends AManager implements ViewProductSearchConnectorManager {

	private static final String PRODUCT_SEARCH_MGT = "ProductSearchMgt";
	private static final String VIEW_PRODUCT_SEARCH_MGT = "ViewProductSearchMgt";
	private static final String I_MANAGER = "IManager";

	Manager() {
		super();
		setProvidedInterfaceType(I_MANAGER, IManager.class);
		setProvidedInterfaceType(VIEW_PRODUCT_SEARCH_MGT, ViewProductSearchMgt.class);
		setRequiredInterfaceType(PRODUCT_SEARCH_MGT, ProductSearchMgt.class);

		IManager productSearchManager = ProductSearchMgrComponentFactory.createInstance();
		setRequiredInterface(PRODUCT_SEARCH_MGT, productSearchManager.getProvidedInterface(PRODUCT_SEARCH_MGT));

		setProvidedInterface(I_MANAGER, this);
		setProvidedInterface(VIEW_PRODUCT_SEARCH_MGT, new ProductSearchMgtAdapter(this));
	}

}
