package br.unicamp.ic.lsd.mercurius.productsearchmgr.spec.req;

import java.util.List;

import br.unicamp.ic.lsd.mercurius.datatype.Product;

public interface ProductSearchPromotionMgt {

	List<Product> getProductsWithPromotion(List<Product> products);

}
