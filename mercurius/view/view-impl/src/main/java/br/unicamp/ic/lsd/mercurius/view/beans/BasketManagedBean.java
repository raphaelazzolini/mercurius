package br.unicamp.ic.lsd.mercurius.view.beans;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.collections4.CollectionUtils;

import br.unicamp.ic.lsd.mercurius.datatype.Basket;
import br.unicamp.ic.lsd.mercurius.datatype.BasketItem;
import br.unicamp.ic.lsd.mercurius.datatype.ProductQuantity;
import br.unicamp.ic.lsd.mercurius.view.spec.req.ViewBasketMgt;
import br.unicamp.ic.lsd.mercurius.viewbasketconnector.ViewBasketConnectorFactory;
import br.unicamp.ic.sed.cosmos.IManager;

@Named("basketManagedBean")
@SessionScoped
public class BasketManagedBean implements Serializable {

	private static final long serialVersionUID = 1881732786578862587L;

	private Basket basket;
	private ProductQuantity produtoInserir;

	private IManager viewBasketConnector;
	private ViewBasketMgt viewBasketMgt;

	@Inject
	private Instance<CustomerManagedBean> customerManagedBean;

	@PostConstruct
	public void init() {
		viewBasketConnector = ViewBasketConnectorFactory.createInstance();
		viewBasketMgt = (ViewBasketMgt) viewBasketConnector.getProvidedInterface("ViewBasketMgt");
		if (basket == null) {
			basket = viewBasketMgt.newBasket();
		}
	}

	public String carrinho() {
		return "carrinho";
	}

	public String comprar() {
		if (basket == null) {
			basket = viewBasketMgt.newBasket();
		}
		basket = viewBasketMgt.addProduct(basket, produtoInserir);
		return "carrinho";
	}

	public String checkout() {
		if (customerManagedBean.get().getCustomer() == null) {
			return "login";
		}
		return "checkout";
	}

	public List<BasketItem> getBasketItems() {
		if (CollectionUtils.isNotEmpty(basket.getBasketItems())) {
			return new LinkedList<BasketItem>(basket.getBasketItems());
		}
		return Collections.emptyList();
	}

	public Basket getBasket() {
		return basket;
	}

	public void setBasket(Basket basket) {
		this.basket = basket;
	}

	public ProductQuantity getProdutoInserir() {
		return produtoInserir;
	}

	public void setProdutoInserir(ProductQuantity produtoInserir) {
		this.produtoInserir = produtoInserir;
	}

}
