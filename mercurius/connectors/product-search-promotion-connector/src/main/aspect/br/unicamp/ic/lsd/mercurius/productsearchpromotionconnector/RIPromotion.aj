package br.unicamp.ic.lsd.mercurius.productsearchpromotionconnector;

import java.util.ArrayList;
import java.util.List;

import br.unicamp.ic.lsd.mercurius.productsearchmgr.aspects.XPIProductSearch;
import br.unicamp.ic.lsd.mercurius.datatype.Product;

public aspect RIPromotion {

	private PromotionAdapter promotionAdapter = new PromotionAdapter();

	List<Product> around() : XPIProductSearch.productPointcut() {
		List<Product> returnList = new ArrayList<Product>();
		for (Product product : proceed()) {
			returnList.add(promotionAdapter.getProductWithPromotion(product));
		}
		return returnList;
	}

}
