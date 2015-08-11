package br.unicamp.ic.lsd.mercurius.productsearchpromotionconnector;

import java.util.ArrayList;
import java.util.List;

import br.unicamp.ic.lsd.mercurius.datatype.Product;
import br.unicamp.ic.lsd.mercurius.productsearchmgr.spec.req.ProductSearchPromotionMgt;
import br.unicamp.ic.lsd.mercurius.promotionmgr.spec.prov.PromotionMgt;
import br.unicamp.ic.sed.cosmos.IManager;

class PromotionAdapter implements ProductSearchPromotionMgt {

	private final IManager manager;

	PromotionAdapter(IManager manager) {
		super();
		this.manager = manager;
	}

	@Override
	public List<Product> getProductsWithPromotion(List<Product> products) {
		PromotionMgt promotionMgt = (PromotionMgt) manager.getRequiredInterface("PromotionMgt");
		List<Product> productsWithDiscount = new ArrayList<>();
		for (Product product : products) {
			productsWithDiscount.add(promotionMgt.productDiscount(product));
		}
		return productsWithDiscount;
	}

}
