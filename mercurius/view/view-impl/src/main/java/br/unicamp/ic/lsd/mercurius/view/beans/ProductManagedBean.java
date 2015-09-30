package br.unicamp.ic.lsd.mercurius.view.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.MenuItem;
import org.primefaces.model.menu.MenuModel;

import br.unicamp.ic.lsd.mercurius.datatype.Category;
import br.unicamp.ic.lsd.mercurius.datatype.Product;
import br.unicamp.ic.lsd.mercurius.datatype.ProductQuantity;
import br.unicamp.ic.lsd.mercurius.excpetionhandler.exceptions.ProductNotFoundException;
import br.unicamp.ic.lsd.mercurius.view.spec.req.ViewProductMgt;
import br.unicamp.ic.lsd.mercurius.viewproductconnector.ViewProductConnectorFactory;
import br.unicamp.ic.sed.cosmos.IManager;

@Named("productManagedBean")
@SessionScoped
public class ProductManagedBean implements Serializable {

	private static final long serialVersionUID = 3922356213133065938L;

	private Product product;
	private List<Category> categories;
	private ProductQuantity selectedProductQuantity;

	private IManager viewProductConnector;
	private ViewProductMgt productMgt;

	@Inject
	private HttpServletRequest request;

	@PostConstruct
	public void init() {
		viewProductConnector = ViewProductConnectorFactory.createInstance();
		productMgt = (ViewProductMgt) viewProductConnector.getProvidedInterface("ViewProductMgt");
	}

	public void productDetails() throws ProductNotFoundException {
		String idString = request.getParameter("prodId");
		if (NumberUtils.isNumber(idString)) {
			Integer idProduto = Integer.parseInt(idString);
			product = productMgt.getProduct(idProduto);
			if (CollectionUtils.isNotEmpty(product.getCategories())) {
				categories = productMgt.getCategoryParents(product.getCategories().get(0));
			}
			if (CollectionUtils.isNotEmpty(product.getQuantities())) {
				selectedProductQuantity = product.getQuantities().get(0);
			}
		}
	}

	public MenuModel getCategoriesBreadCrumb() {
		MenuModel model = new DefaultMenuModel();

		if (product != null && CollectionUtils.isNotEmpty(product.getCategories())) {
			List<MenuItem> items = new ArrayList<>();
			Category category = product.getCategories().get(0);
			MenuItem item = new DefaultMenuItem(category.getName());
			items.add(item);

			while (category.getParent() != null) {
				category = category.getParent();
				item = new DefaultMenuItem(category.getName());
				items.add(item);
			}

			for (int i = items.size() - 1; i >= 0; i--) {
				model.addElement(items.get(i));
			}
		}

		return model;
	}

	public Collection<Product> getFirstPageProducts() {
		return productMgt.getFirstPageProducts();
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public ProductQuantity getSelectedProductQuantity() {
		return selectedProductQuantity;
	}

	public void setSelectedProductQuantity(ProductQuantity selectedProductQuantity) {
		this.selectedProductQuantity = selectedProductQuantity;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

}
