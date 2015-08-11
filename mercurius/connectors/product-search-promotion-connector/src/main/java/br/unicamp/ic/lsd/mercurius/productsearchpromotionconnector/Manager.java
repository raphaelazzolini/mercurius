package br.unicamp.ic.lsd.mercurius.productsearchpromotionconnector;

import br.unicamp.ic.lsd.mercurius.productsearchmgr.spec.req.ProductSearchPromotionMgt;
import br.unicamp.ic.lsd.mercurius.promotionmgr.impl.PromotionMgrComponentFactory;
import br.unicamp.ic.lsd.mercurius.promotionmgr.spec.prov.PromotionMgt;
import br.unicamp.ic.sed.cosmos.AManager;
import br.unicamp.ic.sed.cosmos.IManager;

class Manager extends AManager {

	Manager() {
		super();
		setRequiredInterfaceType("PromotionMgt", PromotionMgt.class);
		IManager promotionManager = PromotionMgrComponentFactory.createInstance();
		setRequiredInterface("PromotionMgt", promotionManager.getProvidedInterface("PromotionMgt"));
		setProvidedInterfaceType("IManager", IManager.class);
		setProvidedInterface("IManager", this);
		setProvidedInterfaceType("ProductSearchPromotionMgt", ProductSearchPromotionMgt.class);
		setProvidedInterface("ProductSearchPromotionMgt", new PromotionAdapter(this));
	}

}
