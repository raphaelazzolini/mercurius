package br.unicamp.ic.lsd.mercurius.viewproductconnector;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Cookie;

import br.unicamp.ic.lsd.mercurius.datatype.Category;
import br.unicamp.ic.lsd.mercurius.datatype.Product;
import br.unicamp.ic.lsd.mercurius.excpetionhandler.exceptions.ProductNotFoundException;
import br.unicamp.ic.lsd.mercurius.productmgr.spec.prov.ProductMgt;
import br.unicamp.ic.lsd.mercurius.view.spec.req.ViewProductMgt;
import br.unicamp.ic.sed.cosmos.IManager;

class ViewProductAdapter implements ViewProductMgt {

	private final IManager manager;
	private ProductMgt productMgt;

	ViewProductAdapter(IManager manager) {
		super();
		this.manager = manager;
		productMgt = (ProductMgt) this.manager.getRequiredInterface("ProductMgt");
	}

	@Override
	public Product getProduct(Integer productId) throws ProductNotFoundException {
		Product product = productMgt.getProduct(productId);

		if (product == null) {
			throw new ProductNotFoundException();
		}

		product = productMgt.loadCategories(product);
		return product;
	}

	@Override
	public List<Category> getCategoryParents(Category category) {
		return getCategoriesTree(category);
	}

	private List<Category> getCategoriesTree(Category category) {
		List<Category> categoriesList = new LinkedList<>();
		if (category.getParent() != null) {
			categoriesList = getCategoriesTree(category.getParent());
		}
		categoriesList.add(category);
		return categoriesList;
	}

	@Override
	public Collection<Product> getFirstPageProducts() {
		Cookie cookie = null;
		for(Cookie cookieRequest : request.getCookies()) {
			if (cookieRequest.getName().equals("prodRecomendado")) {
				cookie = cookieRequest;
				break;
			}
		}

		if (cookie == null) {
			return productMgt.getRandomProducts(4);
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

			return productMgt.getRecommendedProducts(x_coord, y_coord, distance, quantity);
		}
		

		return productMgt.getRandomProducts(4);
		
		
	}

}
