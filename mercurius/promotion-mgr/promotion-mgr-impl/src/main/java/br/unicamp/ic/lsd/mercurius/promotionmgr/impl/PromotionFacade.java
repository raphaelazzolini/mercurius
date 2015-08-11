package br.unicamp.ic.lsd.mercurius.promotionmgr.impl;

import java.util.List;

import br.unicamp.ic.lsd.mercurius.datatype.OrderTotal;
import br.unicamp.ic.lsd.mercurius.datatype.Product;
import br.unicamp.ic.lsd.mercurius.promotionmgr.spec.prov.PromotionManager;
import br.unicamp.ic.lsd.mercurius.promotionmgr.spec.prov.PromotionMgt;

class PromotionFacade implements PromotionMgt {

	private final PromotionManager manager;
	private final PromotionDiscountMgt promotionDiscountMgt;

	PromotionFacade(PromotionManager manager) {
		super();
		this.manager = manager;
		this.promotionDiscountMgt = new PromotionDiscountMgt(this.manager);
	}

	@Override
	public Product productDiscount(Product product) {
		return promotionDiscountMgt.productDiscount(product);
	}

	@Override
	public OrderTotal orderTotalDiscount(List<Product> products, OrderTotal totalDiscount) {
		return promotionDiscountMgt.orderTotalDiscount(products, totalDiscount);
	}

}
