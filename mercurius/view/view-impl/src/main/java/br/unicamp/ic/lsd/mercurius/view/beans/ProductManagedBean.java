package br.unicamp.ic.lsd.mercurius.view.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.lucene.search.Query;
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
	
	@PersistenceContext
	private EntityManager em;

	@Inject
	private HttpServletRequest request;
	

	@PostConstruct
	public void init() {
		viewProductConnector = ViewProductConnectorFactory.createInstance();
		productMgt = (ViewProductMgt) viewProductConnector.getProvidedInterface("ViewProductMgt");
	}

	public void productDetails() throws ProductNotFoundException {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
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
			
			javax.persistence.Query query1 = em.createNativeQuery("select category_id from category_to_price where product_id = :idString");
			query1.setParameter("idString", idString);
			List<Integer> idCat = query1.getResultList();
			
			javax.persistence.Query query2 = em.createNativeQuery("select range_price_id from category_to_price where product_id = :idString");
			query2.setParameter("idString", idString);
			List<Integer> idFaixa = query2.getResultList();
			
			
			Cookie cookie = null;
			for(Cookie cookieRequest : request.getCookies()) {
				if (cookieRequest.getName().equals("prodRecomendado")) {
					cookie = cookieRequest;
					break;
				}
			}
			
			if (cookie == null) {
			cookie = new Cookie("prodRecomendado", idString + "/" + idCat.get(0) + "/" + idFaixa.get(0));
			} else {
				String novoValor = cookie.getValue() + ";" + idString + "/" + idCat.get(0) + "/" + idFaixa.get(0);
				cookie.setValue(novoValor);
			}
			
			cookie.setMaxAge(5000000);
			response.addCookie(cookie);
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
		return productMgt.getFirstPageProducts(request);
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
