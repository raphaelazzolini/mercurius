package br.unicamp.ic.lsd.mercurius.productconnector;

import br.unicamp.ic.lsd.mercurius.admin.spec.req.AdminProductMgt;
import br.unicamp.ic.lsd.mercurius.productmgr.impl.ProductMgrComponentFactory;
import br.unicamp.ic.lsd.mercurius.productmgr.spec.prov.ProductCategoryMgt;
import br.unicamp.ic.lsd.mercurius.productmgr.spec.prov.ProductMgt;
import br.unicamp.ic.sed.cosmos.AManager;
import br.unicamp.ic.sed.cosmos.IManager;

class Manager extends AManager implements IManager {

	Manager() {
		super();
		setProvidedInterfaceType("IManager", IManager.class);
		setProvidedInterfaceType("AdminProductMgt", AdminProductMgt.class);
		setRequiredInterfaceType("ProductMgt", ProductMgt.class);
		setRequiredInterfaceType("ProductMgt", ProductMgt.class);
		setRequiredInterfaceType("CategoryMgt", ProductCategoryMgt.class);

		IManager productManager = ProductMgrComponentFactory.createInstance();
		ProductMgt productMgt = (ProductMgt) productManager.getProvidedInterface("ProductMgt");
		setRequiredInterface("ProductMgt", productMgt);

		ProductCategoryMgt categoryMgt = (ProductCategoryMgt) productManager.getProvidedInterface("CategoryMgt");
		setRequiredInterface("CategoryMgt", categoryMgt);

		setProvidedInterface("IManager", this);
		setProvidedInterface("AdminProductMgt", new ProductAdapter(this));
	}

}
