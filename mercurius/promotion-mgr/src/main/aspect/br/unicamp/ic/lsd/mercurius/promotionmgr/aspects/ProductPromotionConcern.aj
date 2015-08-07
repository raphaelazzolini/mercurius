package br.unicamp.ic.lsd.mercurius.promotionmgr.aspects;

import java.util.List;

import br.unicamp.ic.lsd.mercurius.datatype.OrderTotal;
import br.unicamp.ic.lsd.mercurius.datatype.Product;
import br.unicamp.ic.lsd.mercurius.datatype.ProductQuantity;
import br.unicamp.ic.lsd.mercurius.promotionmgr.adapter.PromotionAdapter;

public aspect ProductPromotionConcern extends AAPromotion {

	private PromotionAdapter promotionAdapter = new PromotionAdapter();

	public pointcut productDiscount(Product product) : execution(public Product PromotionAdapter.getProductWithPromotion(Product)) && args(product);

	public pointcut orderTotalDiscount(List<Product> products, OrderTotal totalDiscount) : execution(public Product PromotionAdapter.none()) && args(products, totalDiscount);

	Product around() : execution(public Product br.unicamp.ic.lsd.mercurius.productmgr.impl.ProductFacade.*(..)) {
		return promotionAdapter.getProductWithPromotion(proceed());
	}

	ProductQuantity around() : execution(public ProductQuantity br.unicamp.ic.lsd.mercurius.productmgr.impl.ProductFacade.*(..)) {
		ProductQuantity quantity = proceed();
		Product product = promotionAdapter.getProductWithPromotion(quantity.getProduct());
		quantity.setProduct(product);
		return quantity;
	}

}
