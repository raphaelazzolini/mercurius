package br.unicamp.ic.lsd.mercurius.recommendedproductsconnector;

import br.unicamp.ic.lsd.mercurius.productmgr.spec.req.ProductRecommendedProductsMgt;
import br.unicamp.ic.lsd.mercurius.recommendedproducts.impl.RecommendedProductsMgrComponentFactory;
import br.unicamp.ic.lsd.mercurius.recommendedproducts.spec.prov.RecommendedProductsMgt;
import br.unicamp.ic.sed.cosmos.AManager;
import br.unicamp.ic.sed.cosmos.IManager;

class Manager extends AManager {

	Manager() {
		super();
		setRequiredInterfaceType("RecommendedProductsMgt", RecommendedProductsMgt.class);
		IManager recommendedProductsManager = RecommendedProductsMgrComponentFactory.createInstance();
		setRequiredInterface("RecommendedProductsMgt",
				recommendedProductsManager.getProvidedInterface("RecommendedProductsMgt"));
		setProvidedInterfaceType("IManager", IManager.class);
		setProvidedInterface("IManager", this);
		setProvidedInterfaceType("ProductRecommendedProductsMgt", ProductRecommendedProductsMgt.class);
		setProvidedInterface("ProductRecommendedProductsMgt", new RecommendedProductsAdapter(this));
	}

}
