package br.unicamp.ic.lsd.mercurius.productmarketingconnector;

import br.unicamp.ic.lsd.mercurius.productmgr.aspects.XPIProduct;
import br.unicamp.ic.lsd.mercurius.datatype.Product;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

public aspect RIRecommendedProducts {

	Collection<Product> around(HttpServletRequest request, Integer quantity) : XPIProduct.recommendedProductsPointcut(HttpServletRequest, Integer)  && args(request, quantity) {
		MarketingAdapter marketingAdapter = new MarketingAdapter();

		// TODO: chamar o pegar o valor do cookie de produtos recomendados, passando-os para o método
		// getRecommendedProducts() do marketingAdapter, junto com o quantity. Retornar a lista desse método.

		//	if (cookie possui produtosRecomendados)
		// return listaProdutosRecomendados

		// Se a lista retornada for nula ou vazia, retornar o valor dar continuidade ao método entrecortado
		return proceed(request, quantity);
	}

}
