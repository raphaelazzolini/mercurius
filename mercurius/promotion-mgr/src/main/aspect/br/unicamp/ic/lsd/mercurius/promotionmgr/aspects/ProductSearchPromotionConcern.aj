package br.unicamp.ic.lsd.mercurius.promotionmgr.aspects;

import java.util.ArrayList;
import java.util.List;

import br.unicamp.ic.lsd.mercurius.datatype.OrderTotal;
import br.unicamp.ic.lsd.mercurius.datatype.Product;
import br.unicamp.ic.lsd.mercurius.promotionmgr.adapter.PromotionAdapter;

public aspect ProductSearchPromotionConcern extends AAPromotion {

	private PromotionAdapter promotionAdapter = new PromotionAdapter();

	public pointcut productDiscount(Product product) : execution(public Product PromotionAdapter.getProductWithPromotion(Product)) && args(product);

	public pointcut orderTotalDiscount(List<Product> products, OrderTotal totalDiscount) : execution(public Product PromotionAdapter.none()) && args(products, totalDiscount);

	List<Product> around() : execution(public List<Product> br.unicamp.ic.lsd.mercurius.productsearchmgr.spec.prov.ProductSearchMgt.*(..)) {
		List<Product> returnList = new ArrayList<Product>();
		for (Product product : proceed()) {
			returnList.add(promotionAdapter.getProductWithPromotion(product));
		}
		return returnList;
	}

}
