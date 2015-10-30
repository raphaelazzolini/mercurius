package br.unicamp.ic.lsd.mercurius.recommendedproductsmgr.aspects;

import java.util.Collection;

import br.unicamp.ic.lsd.mercurius.datatype.Product;
import br.unicamp.ic.lsd.mercurius.recommendedproductsmgr.impl.RecommendedProductsMgrComponentFactory;
import br.unicamp.ic.lsd.mercurius.recommendedproductsmgr.spec.prov.RecommendedProductsManager;

public abstract aspect AARecommendedProducts {

	private final RecommendedProductsManager manager = (RecommendedProductsManager) RecommendedProductsMgrComponentFactory.createInstance();

	abstract public pointcut recommendedProducts(String idsList, Integer quantity);

	Collection<Product> around(String idsList, Integer quantity) : recommendedProducts(String, Integer) && args(idsList, quantity) {
		// TODO: realizar a lógica para obter os produtos recomendados
		return null;
	}
}
