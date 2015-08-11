package br.unicamp.ic.lsd.mercurius.basketmgr.impl;

import java.util.List;

import br.unicamp.ic.lsd.mercurius.basketmgr.spec.prov.BasketManager;
import br.unicamp.ic.lsd.mercurius.basketmgr.spec.prov.BasketMgt;
import br.unicamp.ic.lsd.mercurius.basketmgr.spec.req.BasketLoggingMgt;
import br.unicamp.ic.lsd.mercurius.basketmgr.spec.req.BasketProductMgt;
import br.unicamp.ic.lsd.mercurius.datatype.Basket;
import br.unicamp.ic.lsd.mercurius.datatype.BasketItem;
import br.unicamp.ic.lsd.mercurius.datatype.Customer;
import br.unicamp.ic.lsd.mercurius.datatype.Product;
import br.unicamp.ic.lsd.mercurius.datatype.ProductAttribute;
import br.unicamp.ic.lsd.mercurius.datatype.ProductQuantity;

public class BasketFacade implements BasketMgt {

	private static final String BASKET_PRODUCT_MGT = "BasketProductMgt";

	private final BasketManager manager;
	private final BasketMgtImpl basketMgtImpl;
	private BasketProductMgt productMgt;
	private BasketLoggingMgt loggingMgt;

	public BasketFacade(BasketManager manager) {
		super();
		this.manager = manager;
		this.basketMgtImpl = new BasketMgtImpl(this.manager);
		productMgt = (BasketProductMgt) this.manager.getRequiredInterface(BASKET_PRODUCT_MGT);
		loggingMgt = (BasketLoggingMgt) this.manager.getRequiredInterface("BasketLoggingMgt");
	}

	@Override
	public void addToBasket(Product product, List<ProductAttribute> attributes, Integer quantity) {
		try {
			loggingMgt.debug("Executing method addToBasket");
			ProductQuantity productQuantity = productMgt.getProductQuantity(product, attributes);
			basketMgtImpl.addToBasket(productQuantity, quantity);
			loggingMgt.debug("Finishing method addToBasket");
		} catch (Exception e) {
			loggingMgt.error("Error executing method addToBasket", e);
			throw e;
		}
	}

	@Override
	public void addToBasket(String sku, Integer quantity) {
		try {
			loggingMgt.debug("Executing method addToBasket");
			ProductQuantity productQuantity = productMgt.getProductQuantity(sku);
			basketMgtImpl.addToBasket(productQuantity, quantity);
			loggingMgt.debug("Finishing method addToBasket");
		} catch (Exception e) {
			loggingMgt.error("Error executing method addToBasket", e);
			throw e;
		}
	}

	@Override
	public void removeFromBasket(BasketItem item) {
		try {
			loggingMgt.debug("Executing method removeFromBasket");
			basketMgtImpl.removeFromBasket(item);
			loggingMgt.debug("Finishing method addToBasket");
		} catch (Exception e) {
			loggingMgt.error("Error executing method removeFromBasket", e);
			throw e;
		}
	}

	@Override
	public void updateItemQuantity(BasketItem item, Integer newQuantity) {
		try {
			loggingMgt.debug("Executing method updateItemQuantity");
			basketMgtImpl.updateItemQuantity(item, newQuantity);
			loggingMgt.debug("Finishing method updateItemQuantity");
		} catch (Exception e) {
			loggingMgt.error("Error executing method updateItemQuantity", e);
			throw e;
		}
	}

	@Override
	public void cleanBasket() {
		try {
			loggingMgt.debug("Executing method cleanBasket");
			basketMgtImpl.cleanBasket();
			loggingMgt.debug("Finishing method cleanBasket");
		} catch (Exception e) {
			loggingMgt.error("Error executing method cleanBasket", e);
			throw e;
		}
	}

	@Override
	public Basket getBasket(Customer customer) {
		try {
			loggingMgt.debug("Executing method getBasket");
			return basketMgtImpl.getBasket(customer);
		} catch (Exception e) {
			loggingMgt.error("Error executing method getBasket", e);
			throw e;
		}
	}

	@Override
	public Basket getBasket(String sessionId) {
		try {
			loggingMgt.debug("Executing method getBasket");
			return basketMgtImpl.getBasket(sessionId);
		} catch (Exception e) {
			loggingMgt.error("Error executing method getBasket", e);
			throw e;
		}
	}

	@Override
	public Basket getCurrentBasket() {
		try {
			loggingMgt.debug("Executing method getCurrentBasket");
			return basketMgtImpl.getCurrentBasket();
		} catch (Exception e) {
			loggingMgt.error("Error executing method getCurrentBasket", e);
			throw e;
		}
	}

	@Override
	public void loadBasketItems(Basket basket) {
		try {
			loggingMgt.debug("Executing method loadBasketItems");
			basketMgtImpl.loadBasketItems(basket);
			loggingMgt.debug("Finishing method loadBasketItems");
		} catch (Exception e) {
			loggingMgt.error("Error executing method loadBasketItems", e);
			throw e;
		}
	}

	BasketProductMgt getProductMgt() {
		return productMgt;
	}

	void setProductMgt(BasketProductMgt productMgt) {
		this.productMgt = productMgt;
	}

	@Override
	public Basket newBasket() {
		try {
			loggingMgt.debug("Executing method newBasket");
			return manager.getBasketDAO().newInstance();
		} catch (Exception e) {
			loggingMgt.error("Error executing method newBasket", e);
			throw e;
		}
	}

	@Override
	public Basket addProduct(Basket basket, ProductQuantity productQuantity) {
		try {
			loggingMgt.debug("Executing method addProduct");
			productQuantity = productMgt.getProductQuantity(productQuantity.getSku());
			return basketMgtImpl.addProduct(basket, productQuantity, 1);
		} catch (Exception e) {
			loggingMgt.error("Error executing method addProduct", e);
			throw e;
		}
	}

}
