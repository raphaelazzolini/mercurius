package br.unicamp.ic.lsd.mercurius.promotionmgr.adapter;

import java.util.List;

import br.unicamp.ic.lsd.mercurius.datatype.OrderTotal;
import br.unicamp.ic.lsd.mercurius.datatype.OrderTotalModule;
import br.unicamp.ic.lsd.mercurius.datatype.Product;

public class PromotionAdapter {

	public PromotionAdapter() {
		super();
	}

	public OrderTotal getOrderDiscount(List<Product> product, OrderTotal totalDiscount) {
		totalDiscount.setModule(OrderTotalModule.DISCOUNT);
		return totalDiscount;
	}

	public Product getProductWithPromotion(Product product) {
		return product;
	}

}
