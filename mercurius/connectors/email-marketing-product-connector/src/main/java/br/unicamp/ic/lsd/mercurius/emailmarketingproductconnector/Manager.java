package br.unicamp.ic.lsd.mercurius.emailmarketingproductconnector;

import br.unicamp.ic.lsd.mercurius.emailmarketingmgr.spec.req.EmailMarketingProductMgt;
import br.unicamp.ic.lsd.mercurius.productmgr.impl.ProductMgrComponentFactory;
import br.unicamp.ic.lsd.mercurius.productmgr.spec.prov.ProductMgt;
import br.unicamp.ic.lsd.mercurius.emailmarketingproductconnector.ProductMgtAdapter;
import br.unicamp.ic.sed.cosmos.AManager;
import br.unicamp.ic.sed.cosmos.IManager;

class Manager extends AManager implements IManager {

	Manager() {
		super();
		
		setRequiredInterfaceType("ProductMgt", ProductMgt.class);
		//TODO criacao de dois products?
		IManager ProductManager = ProductMgrComponentFactory.createInstance();
		setRequiredInterface("ProductMgt", ProductManager.getProvidedInterface("ProductMgt"));
		
		setProvidedInterfaceType("IManager", IManager.class);
		setProvidedInterfaceType("EmailMarketingProductMgt", EmailMarketingProductMgt.class);
		setProvidedInterface("IManager", IManager.class);
		setProvidedInterface("EmailMarketingProductMgt", new ProductMgtAdapter(this));
	}

}