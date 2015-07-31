package br.unicamp.ic.lsd.mercurius.admin.impl;

import br.unicamp.ic.lsd.mercurius.admin.spec.prov.AdminManager;
import br.unicamp.ic.lsd.mercurius.admin.spec.prov.AdminMgt;
import br.unicamp.ic.lsd.mercurius.admin.spec.req.AdminProductMgt;
import br.unicamp.ic.lsd.mercurius.admin.spec.req.AdminProductSearchMgt;
import br.unicamp.ic.lsd.mercurius.productconnector.ProductConnectorComponentFactory;
import br.unicamp.ic.lsd.mercurius.productsearchconnector.ProductSearchConnectorComponentFactory;
import br.unicamp.ic.sed.cosmos.AManager;
import br.unicamp.ic.sed.cosmos.IManager;

class Manager extends AManager implements AdminManager {

	Manager() {
		super();

		setProvidedInterface("AdminMgt", new AdminMgtImpl(this));
		setProvidedInterface("IManager", this);

		setProvidedInterfaceType("AdminMgt", AdminMgt.class);
		setProvidedInterfaceType("IManager", IManager.class);

		setRequiredInterfaceType("AdminProductMgt", AdminProductMgt.class);
		setRequiredInterfaceType("AdminProductSearchMgt", AdminProductSearchMgt.class);

		IManager productConnectorManager = ProductConnectorComponentFactory.createInstance();
		AdminProductMgt productMgt = (AdminProductMgt) productConnectorManager.getProvidedInterface("AdminProductMgt");
		setRequiredInterface("AdminProductMgt", productMgt);

		IManager productSearchConnectorManager = ProductSearchConnectorComponentFactory.createInstance();
		AdminProductSearchMgt productSearchMgt = (AdminProductSearchMgt) productSearchConnectorManager
				.getProvidedInterface("AdminProductSearchMgt");
		setRequiredInterface("AdminProductSearchMgt", productSearchMgt);
	}

}
