package br.unicamp.ic.lsd.mercurius.productmarketingconnector;

import java.util.Collection;

import br.unicamp.ic.lsd.mercurius.datatype.Product;
import br.unicamp.ic.lsd.mercurius.recommendedproductsmgr.aspects.AARecommendedProducts;

public aspect DIRecommendedProducts extends AARecommendedProducts {

	//Entrecorta o método getRecommendedProducts() do MarketingAdapter com o aspecto AARecommendedProducts.
	public pointcut recommendedProducts(String idsList, Integer quantity) : execution(public Collection<Product> MarketingAdapter.getRecommendedProducts(String, Integer)) && args(idsList, quantity);

}
