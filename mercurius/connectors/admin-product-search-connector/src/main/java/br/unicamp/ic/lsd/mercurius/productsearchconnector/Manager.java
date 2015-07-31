package br.unicamp.ic.lsd.mercurius.productsearchconnector;

import br.unicamp.ic.lsd.mercurius.admin.spec.req.AdminProductSearchMgt;
import br.unicamp.ic.lsd.mercurius.productsearchmgr.impl.ProductSearchMgrComponentFactory;
import br.unicamp.ic.lsd.mercurius.productsearchmgr.spec.prov.ProductSearchMgt;
import br.unicamp.ic.sed.cosmos.AManager;
import br.unicamp.ic.sed.cosmos.IManager;

class Manager extends AManager implements IManager {

	Manager() {
		super();
		setProvidedInterfaceType("IManager", IManager.class);
		setProvidedInterfaceType("AdminProductSearchMgt", AdminProductSearchMgt.class);
		setRequiredInterfaceType("ProductSearchMgt", ProductSearchMgt.class);
		setRequiredInterfaceType("ProductSearchMgt", ProductSearchMgt.class);

		IManager productSearchManager = ProductSearchMgrComponentFactory.createInstance();
		ProductSearchMgt productSearchMgt = (ProductSearchMgt) productSearchManager
				.getProvidedInterface("ProductSearchMgt");
		setRequiredInterface("ProductSearchMgt", productSearchMgt);

		setProvidedInterface("IManager", this);
		setProvidedInterface("AdminProductSearchMgt", new ProductSearchAdapter(this));
	}

}
