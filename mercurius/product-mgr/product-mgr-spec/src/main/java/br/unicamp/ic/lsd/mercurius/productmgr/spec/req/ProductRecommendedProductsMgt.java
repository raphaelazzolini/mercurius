package br.unicamp.ic.lsd.mercurius.productmgr.spec.req;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import br.unicamp.ic.lsd.mercurius.datatype.Product;

public interface ProductRecommendedProductsMgt {

	Collection<Product> getRecommendedProducts(HttpServletRequest request, Integer quantity);

}
