package br.unicamp.ic.lsd.mercurius.orderpromotionconnector;

import java.util.List;

import br.unicamp.ic.lsd.mercurius.datatype.OrderTotal;
import br.unicamp.ic.lsd.mercurius.datatype.Product;
import br.unicamp.ic.lsd.mercurius.promotionmgr.aspects.AAPromotion;

public aspect DIPromotion extends AAPromotion {

	public pointcut productDiscount(Product product) : execution(public Product PromotionAdapter.none(..)) && args(product);

	public pointcut orderTotalDiscount(List<Product> products, OrderTotal totalDiscount) : execution(public OrderTotal PromotionAdapter.getOrderDiscount(List<Product>, OrderTotal)) && args(products, totalDiscount);

}
