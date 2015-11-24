package br.unicamp.ic.lsd.mercurius.recommendedproducts.impl;

import java.util.Collection;

import br.unicamp.ic.lsd.mercurius.datatype.Product;
import br.unicamp.ic.lsd.mercurius.recommendedproducts.spec.prov.RecommendedProductsManager;
import br.unicamp.ic.lsd.mercurius.recommendedproducts.spec.prov.RecommendedProductsMgt;

class RecommendedProductsFacade implements RecommendedProductsMgt {

	private final RecommendedProductsManager manager;

	RecommendedProductsFacade(RecommendedProductsManager manager) {
		super();
		this.manager = manager;
	}

	@Override
	public Collection<Product> getRecommendedProducts(Double x_coord, Double y_coord, Double distance, Integer quantity) {
		Collection<Product> products = manager.getProductDAO().getRecommendedProducts(x_coord, y_coord, distance,
				quantity);
		return products;
	}

}
