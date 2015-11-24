package br.unicamp.ic.lsd.mercurius.recommendedproductsconnector;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

import java.util.Collection;
import java.util.Collections;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import br.unicamp.ic.lsd.mercurius.datatype.Product;
import br.unicamp.ic.lsd.mercurius.productmgr.spec.req.ProductRecommendedProductsMgt;
import br.unicamp.ic.lsd.mercurius.recommendedproducts.spec.prov.RecommendedProductsMgt;
import br.unicamp.ic.sed.cosmos.IManager;

class RecommendedProductsAdapter implements ProductRecommendedProductsMgt {

	private final IManager manager;

	RecommendedProductsAdapter(IManager manager) {
		super();
		this.manager = manager;
	}

	@Override
	public Collection<Product> getRecommendedProducts(HttpServletRequest request, Integer quantity) {
		RecommendedProductsMgt recommendedProductsMgt = (RecommendedProductsMgt) manager
				.getRequiredInterface("RecommendedProductsMgt");
		Collection<Product> listaProdutosRecomendados = null;

		Cookie cookie = null;
		for (Cookie cookieRequest : request.getCookies()) {
			if (cookieRequest.getName().equals("prodRecomendado")) {
				cookie = cookieRequest;
				break;
			}
		}

		if (cookie == null) {
			return Collections.emptyList();
		}

		String cookieValue = cookie.getValue();
		String[] values = cookieValue.split(",");
		Integer numCookies = values.length;

		if (numCookies > 1) {
			String[] newestCookie = values[numCookies - 1].split("/");
			String[] lastCookie = values[numCookies - 2].split("/");
			Double x_coord = Double.parseDouble(newestCookie[1]);
			Double y_coord = Double.parseDouble(newestCookie[2]);
			Double distance = (sqrt(pow(Double.parseDouble(newestCookie[1]) - Double.parseDouble(lastCookie[1]), 2)
					+ pow(Double.parseDouble(newestCookie[2]) - Double.parseDouble(lastCookie[2]), 2)));

			listaProdutosRecomendados = recommendedProductsMgt.getRecommendedProducts(x_coord, y_coord, distance,
					quantity);
		}

		// Se a lista retornada for nula ou vazia, retornar o valor dar
		// continuidade ao método entrecortado
		return listaProdutosRecomendados;
	}

}
