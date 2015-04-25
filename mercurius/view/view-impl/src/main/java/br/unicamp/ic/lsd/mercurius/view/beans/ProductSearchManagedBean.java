package br.unicamp.ic.lsd.mercurius.view.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import br.unicamp.ic.lsd.mercurius.datatype.Product;
import br.unicamp.ic.lsd.mercurius.view.spec.req.ViewProductSearchMgt;
import br.unicamp.ic.lsd.mercurius.viewproductsearchconnector.ViewProductSearchConnectorFactory;
import br.unicamp.ic.sed.cosmos.IManager;

@Named("productSearchManagedBean")
@SessionScoped
public class ProductSearchManagedBean implements Serializable {

	private static final long serialVersionUID = -2438886566771928168L;

	private List<Product> products;
	private String searchText;
	private Product selectedProduct;

	private IManager viewProductSearchConnector;
	private ViewProductSearchMgt productSearchMgt;

	@Inject
	private HttpServletRequest request;

	@PostConstruct
	public void init() {
		viewProductSearchConnector = ViewProductSearchConnectorFactory.createInstance();
		productSearchMgt = (ViewProductSearchMgt) viewProductSearchConnector
				.getProvidedInterface("ViewProductSearchMgt");
	}

	public void search() {
		searchText = request.getParameter("q");
		products = productSearchMgt.searchByText(searchText);
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Product getSelectedProduct() {
		return selectedProduct;
	}

	public void setSelectedProduct(Product selectedProduct) {
		this.selectedProduct = selectedProduct;
	}

}
