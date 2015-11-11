package br.unicamp.ic.lsd.mercurius.productmarketingconnector;

import br.unicamp.ic.lsd.mercurius.productmgr.aspects.XPIProduct;
import br.unicamp.ic.lsd.mercurius.datatype.Product;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Cookie;
import java.util.Collection;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

public aspect RIRecommendedProducts {

	Collection<Product> around(HttpServletRequest request, Integer quantity) : XPIProduct.recommendedProductsPointcut(HttpServletRequest, Integer)  && args(request, quantity) {
		MarketingAdapter marketingAdapter = new MarketingAdapter();
		Collection<Product> listaProdutosRecomendados = null;

		Cookie cookie = null;
		for(Cookie cookieRequest : request.getCookies()) {
			if (cookieRequest.getName().equals("prodRecomendado")) {
				cookie = cookieRequest;
				break;
			}
		}

		if (cookie == null) {
			return proceed(request, quantity);
		}

		String cookieValue = cookie.getValue();
		String[] values = cookieValue.split(",");
		Integer numCookies = values.length;

		if (numCookies > 1) {
			String[] newestCookie = values[numCookies-1].split("/");
			String[] lastCookie = values[numCookies-2].split("/");
			Double x_coord = Double.parseDouble(newestCookie[1]);
			Double y_coord = Double.parseDouble(newestCookie[2]);
			Double distance = (sqrt(pow(Double.parseDouble(newestCookie[1])-Double.parseDouble(lastCookie[1]),2)+pow(Double.parseDouble(newestCookie[2])-Double.parseDouble(lastCookie[2]),2)));

			listaProdutosRecomendados = marketingAdapter.getRecommendedProducts(x_coord,y_coord,distance,quantity);
		}

		// Se a lista retornada for nula ou vazia, retornar o valor dar continuidade ao método entrecortado
		if(CollectionUtils.isEmpty(listaProdutosRecomendados)){
			return proceed(request, quantity);
		}
		return listaProdutosRecomendados;
	}

}
