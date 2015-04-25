package br.unicamp.ic.lsd.mercurius.basketmgr.impl;

import java.util.HashSet;
import java.util.Set;

import br.unicamp.ic.lsd.mercurius.basketmgr.spec.prov.BasketManager;
import br.unicamp.ic.lsd.mercurius.datatype.Basket;
import br.unicamp.ic.lsd.mercurius.datatype.BasketItem;
import br.unicamp.ic.lsd.mercurius.datatype.Customer;
import br.unicamp.ic.lsd.mercurius.datatype.ProductQuantity;

class BasketMgtImpl {

	private BasketManager manager;

	private Basket currentBasket;

	BasketMgtImpl(BasketManager manager) {
		super();
		this.manager = manager;
	}

	public void addToBasket(ProductQuantity productQuantity, Integer quantity) {
		Basket basket = getCurrentBasket();
		addProduct(basket, productQuantity, quantity);
	}

	public void removeFromBasket(BasketItem item) {
		Basket basket = getCurrentBasket();
		basket.getBasketItems().remove(item);
	}

	public void updateItemQuantity(BasketItem item, Integer newQuantity) {
		Basket basket = getCurrentBasket();
		if (basket.getBasketItems() == null) {
			basket.setBasketItems(new HashSet<BasketItem>());
		}
		Set<BasketItem> basketItems = basket.getBasketItems();
		if (newQuantity == 0) {
			removeFromBasket(item);
		} else if (basketItems.contains(item)) {
			for (BasketItem basketItem : basketItems) {
				if (basketItem.equals(item)) {
					basketItem.setQuantity(newQuantity);
					break;
				}
			}
		} else {
			item.setQuantity(newQuantity);
			basketItems.add(item);
		}
	}

	public void cleanBasket() {
		Basket basket = getCurrentBasket();
		basket.getBasketItems().clear();
	}

	public Basket getBasket(Customer customer) {
		currentBasket = manager.getBasketDAO().getBasketFromCustomer(customer);
		return getCurrentBasket();
	}

	public Basket getBasket(String sessionId) {
		currentBasket = manager.getBasketDAO().getBasketFromSession(sessionId);
		return getCurrentBasket();
	}

	public Basket getCurrentBasket() {
		if (currentBasket == null) {
			currentBasket = manager.getBasketDAO().newInstance();
			currentBasket.setBasketItems(new HashSet<BasketItem>());
		}
		return currentBasket;
	}

	public void loadBasketItems(Basket basket) {
		manager.getBasketDAO().loadBasketItems(basket);
	}

	public Basket addProduct(Basket basket, ProductQuantity productQuantity, Integer quantity) {
		BasketItem item = manager.getBasketItemFactory().createInstance(basket, productQuantity.getSku(),
				productQuantity.getProduct(), productQuantity.getProductsAttributes());

		item.setItemPrice(productQuantity.getItemPrice());
		item.setQuantity(quantity);
		item.setItemPrice(productQuantity.getItemPrice());

		if (basket.getBasketItems() == null) {
			basket.setBasketItems(new HashSet<BasketItem>());
		}
		if (basket.getBasketItems().contains(item)) {
			basket.getBasketItems().remove(item);
		}
		basket.getBasketItems().add(item);
		return basket;
	}

}
