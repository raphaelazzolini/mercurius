package br.unicamp.ic.lsd.mercurius.recommendedproducts.spec.prov;

import java.util.Collection;

import br.unicamp.ic.lsd.mercurius.datatype.Product;

public interface RecommendedProductsMgt {

	Collection<Product> getRecommendedProducts(Double x_coord, Double y_coord, Double distance, Integer quantity);

}
