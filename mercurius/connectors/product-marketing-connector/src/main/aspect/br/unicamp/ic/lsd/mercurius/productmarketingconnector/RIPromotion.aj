package br.unicamp.ic.lsd.mercurius.productmarketingconnector;

import br.unicamp.ic.lsd.mercurius.productmgr.aspects.XPIProduct;
import br.unicamp.ic.lsd.mercurius.datatype.Product;
import br.unicamp.ic.lsd.mercurius.datatype.ProductQuantity;

public aspect RIPromotion {

	private MarketingAdapter marketingAdapter = new MarketingAdapter();

	Product around() : XPIProduct.productPointcut() {
		return marketingAdapter.getProductWithPromotion(proceed());
	}

	ProductQuantity around() : XPIProduct.productQuantityPointcut() {
		ProductQuantity quantity = proceed();
		Product product = marketingAdapter.getProductWithPromotion(quantity.getProduct());
		quantity.setProduct(product);
		return quantity;
	}

}
