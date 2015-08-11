package br.unicamp.ic.lsd.mercurius.productpromotionconnector;

import br.unicamp.ic.lsd.mercurius.productmgr.spec.req.ProductPromotionMgt;
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
		setProvidedInterfaceType("ProductPromotionMgt", ProductPromotionMgt.class);
		setProvidedInterface("ProductPromotionMgt", new PromotionAdapter(this));
	}

}
