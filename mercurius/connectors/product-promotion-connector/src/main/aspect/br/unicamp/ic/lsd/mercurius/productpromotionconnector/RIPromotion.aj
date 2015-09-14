package br.unicamp.ic.lsd.mercurius.productpromotionconnector;

import br.unicamp.ic.lsd.mercurius.productmgr.aspects.XPIProduct;
import br.unicamp.ic.lsd.mercurius.datatype.Product;
import br.unicamp.ic.lsd.mercurius.datatype.ProductQuantity;

public aspect RIPromotion {

	private PromotionAdapter promotionAdapter = new PromotionAdapter();

	Product around() : XPIProduct.productPointcut() {
		return promotionAdapter.getProductWithPromotion(proceed());
	}

	ProductQuantity around() : XPIProduct.productQuantityPointcut() {
		ProductQuantity quantity = proceed();
		Product product = promotionAdapter.getProductWithPromotion(quantity.getProduct());
		quantity.setProduct(product);
		return quantity;
	}

}
