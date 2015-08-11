package br.unicamp.ic.lsd.mercurius.productpromotionconnector;

import br.unicamp.ic.lsd.mercurius.datatype.Product;
import br.unicamp.ic.lsd.mercurius.productmgr.spec.req.ProductPromotionMgt;
import br.unicamp.ic.lsd.mercurius.promotionmgr.spec.prov.PromotionMgt;
import br.unicamp.ic.sed.cosmos.IManager;

class PromotionAdapter implements ProductPromotionMgt {

	private final IManager manager;

	PromotionAdapter(IManager manager) {
		super();
		this.manager = manager;
	}

	@Override
	public Product getProductWithPromotion(Product product) {
		PromotionMgt promotionMgt = (PromotionMgt) manager.getRequiredInterface("PromotionMgt");
		return promotionMgt.productDiscount(product);
	}

}
