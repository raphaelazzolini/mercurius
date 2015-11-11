package br.unicamp.ic.lsd.mercurius.recommendedproductsmgr.aspects;

import java.util.Collection;

import org.apache.commons.collections4.CollectionUtils;

import br.unicamp.ic.lsd.mercurius.datatype.Product;
import br.unicamp.ic.lsd.mercurius.recommendedproductsmgr.impl.RecommendedProductsMgrComponentFactory;
import br.unicamp.ic.lsd.mercurius.recommendedproductsmgr.spec.prov.RecommendedProductsManager;

public abstract aspect AARecommendedProducts {

	private final RecommendedProductsManager manager = (RecommendedProductsManager) RecommendedProductsMgrComponentFactory.createInstance();

	abstract public pointcut recommendedProducts(Double x_coord,Double y_coord,Double distance, Integer quantity);

	Collection<Product> around(Double x_coord, Double y_coord, Double distance, Integer quantity) : recommendedProducts(Double, Double, Double, Integer) && args(x_coord, y_coord, distance, quantity) {
		Collection<Product> products = manager.getProductDAO().getRecommendedProducts(x_coord, y_coord, distance, quantity);

		if (CollectionUtils.isNotEmpty(products)) {
			return products;
		}

		return proceed(x_coord, y_coord, distance, quantity);
	}
}
