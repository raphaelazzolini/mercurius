package br.unicamp.ic.lsd.mercurius.recommendedproductsmgr.aspects;

import java.util.Collection;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Cookie;

import org.apache.commons.collections4.CollectionUtils;

import br.unicamp.ic.lsd.mercurius.datatype.Product;
import br.unicamp.ic.lsd.mercurius.recommendedproductsmgr.impl.RecommendedProductsMgrComponentFactory;
import br.unicamp.ic.lsd.mercurius.recommendedproductsmgr.spec.prov.RecommendedProductsManager;

public abstract aspect RecommendedProducts {

	private final RecommendedProductsManager manager = (RecommendedProductsManager) RecommendedProductsMgrComponentFactory.createInstance();

	public pointcut recommendedProducts(HttpServletRequest request, Integer quantity) : execution(public Collection<Product> br.unicamp.ic.lsd.mercurius.productmgr.spec.prov.ProductMgt.getRandomProducts(HttpServletRequest, Integer)) && args(request, quantity);

	Collection<Product> around(HttpServletRequest request, Integer quantity) : recommendedProducts(HttpServletRequest, Integer) && args(request, quantity) {
		Collection<Product> products = null;

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

			products = manager.getProductDAO().getRecommendedProducts(x_coord, y_coord, distance, quantity);
		}

		if (CollectionUtils.isNotEmpty(products)) {
			return products;
		}

		return proceed(request, quantity);
	}
}
