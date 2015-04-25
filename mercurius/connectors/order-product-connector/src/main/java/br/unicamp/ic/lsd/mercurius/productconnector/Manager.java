package br.unicamp.ic.lsd.mercurius.productconnector;

import br.unicamp.ic.lsd.mercurius.ordermgr.spec.req.OrderProductMgt;
import br.unicamp.ic.lsd.mercurius.productmgr.impl.ProductMgrComponentFactory;
import br.unicamp.ic.lsd.mercurius.productmgr.spec.prov.ProductMgt;
import br.unicamp.ic.sed.cosmos.AManager;
import br.unicamp.ic.sed.cosmos.IManager;

class Manager extends AManager implements IManager {

	Manager() {
		super();
		setProvidedInterfaceType("IManager", IManager.class);
		setProvidedInterfaceType("OrderProductMgt", OrderProductMgt.class);
		setRequiredInterfaceType("ProductMgt", ProductMgt.class);

		IManager productManager = ProductMgrComponentFactory.createInstance();
		ProductMgt productMgt = (ProductMgt) productManager.getProvidedInterface("ProductMgt");
		setRequiredInterface("ProductMgt", productMgt);

		setProvidedInterface("IManager", this);
		setProvidedInterface("OrderProductMgt", new ProductAdapter(this));
	}

}
