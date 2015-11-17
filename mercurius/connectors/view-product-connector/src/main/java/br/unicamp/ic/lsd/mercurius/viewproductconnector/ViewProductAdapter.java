package br.unicamp.ic.lsd.mercurius.viewproductconnector;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

	@Override
	public Collection<Product> getFirstPageProducts(HttpServletRequest request) {
		return productMgt.getRandomProducts(request, 4);
	}

	private List<Category> getCategoriesTree(Category category) {
		List<Category> categoriesList = new LinkedList<>();
		if (category.getParent() != null) {
			categoriesList = getCategoriesTree(category.getParent());
		}
		categoriesList.add(category);
		return categoriesList;
	}

}
