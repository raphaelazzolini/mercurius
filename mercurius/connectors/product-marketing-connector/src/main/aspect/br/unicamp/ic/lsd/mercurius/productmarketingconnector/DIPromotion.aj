package br.unicamp.ic.lsd.mercurius.productmarketingconnector;

import java.util.List;

import br.unicamp.ic.lsd.mercurius.datatype.OrderTotal;
import br.unicamp.ic.lsd.mercurius.datatype.Product;
import br.unicamp.ic.lsd.mercurius.promotionmgr.aspects.AAPromotion;

public aspect DIPromotion extends AAPromotion {

	public pointcut productDiscount(Product product) : execution(public Product MarketingAdapter.getProductWithPromotion(Product)) && args(product);

	public pointcut orderTotalDiscount(List<Product> products, OrderTotal totalDiscount) : execution(public Product MarketingAdapter.none()) && args(products, totalDiscount);

}
