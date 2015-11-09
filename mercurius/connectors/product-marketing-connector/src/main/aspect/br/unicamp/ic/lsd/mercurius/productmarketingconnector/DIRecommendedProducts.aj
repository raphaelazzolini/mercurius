package br.unicamp.ic.lsd.mercurius.productmarketingconnector;

import java.util.Collection;

import br.unicamp.ic.lsd.mercurius.datatype.Product;
import br.unicamp.ic.lsd.mercurius.recommendedproductsmgr.aspects.AARecommendedProducts;

public aspect DIRecommendedProducts extends AARecommendedProducts {

	//Entrecorta o método getRecommendedProducts() do MarketingAdapter com o aspecto AARecommendedProducts.
	public pointcut recommendedProducts(Double x_coord,Double y_coord,Double distance, Integer quantity) : execution(public Collection<Product> MarketingAdapter.getRecommendedProducts(Double, Double, Double, Integer)) && args(x_coord, y_coord, distance, quantity);

}
