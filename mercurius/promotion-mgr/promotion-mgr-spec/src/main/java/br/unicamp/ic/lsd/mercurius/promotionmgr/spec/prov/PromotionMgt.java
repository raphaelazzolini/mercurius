package br.unicamp.ic.lsd.mercurius.promotionmgr.spec.prov;

import java.util.List;

import br.unicamp.ic.lsd.mercurius.datatype.OrderTotal;
import br.unicamp.ic.lsd.mercurius.datatype.Product;

public interface PromotionMgt {

	Product productDiscount(Product product);

	OrderTotal orderTotalDiscount(List<Product> products, OrderTotal totalDiscount);

}
