package br.unicamp.ic.lsd.mercurius.basketmgr.impl;

import java.util.List;

import br.unicamp.ic.lsd.mercurius.basketmgr.spec.prov.BasketManager;
import br.unicamp.ic.lsd.mercurius.basketmgr.spec.prov.BasketMgt;
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

	public BasketFacade(BasketManager manager) {
		super();
		this.manager = manager;
		this.basketMgtImpl = new BasketMgtImpl(this.manager);
		productMgt = (BasketProductMgt) this.manager.getRequiredInterface(BASKET_PRODUCT_MGT);
	}

	@Override
	public void addToBasket(Product product, List<ProductAttribute> attributes, Integer quantity) {
		ProductQuantity productQuantity = productMgt.getProductQuantity(product, attributes);
		basketMgtImpl.addToBasket(productQuantity, quantity);
	}

	@Override
	public void addToBasket(String sku, Integer quantity) {
		ProductQuantity productQuantity = productMgt.getProductQuantity(sku);
		basketMgtImpl.addToBasket(productQuantity, quantity);
	}

	@Override
	public void removeFromBasket(BasketItem item) {
		basketMgtImpl.removeFromBasket(item);
	}

	@Override
	public void updateItemQuantity(BasketItem item, Integer newQuantity) {
		basketMgtImpl.updateItemQuantity(item, newQuantity);
	}

	@Override
	public void cleanBasket() {
		basketMgtImpl.cleanBasket();
	}

	@Override
	public Basket getBasket(Customer customer) {
		return basketMgtImpl.getBasket(customer);
	}

	@Override
	public Basket getBasket(String sessionId) {
		return basketMgtImpl.getBasket(sessionId);
	}

	@Override
	public Basket getCurrentBasket() {
		return basketMgtImpl.getCurrentBasket();
	}

	@Override
	public void loadBasketItems(Basket basket) {
		basketMgtImpl.loadBasketItems(basket);
	}

	BasketProductMgt getProductMgt() {
		return productMgt;
	}

	void setProductMgt(BasketProductMgt productMgt) {
		this.productMgt = productMgt;
	}

	@Override
	public Basket newBasket() {
		return manager.getBasketDAO().newInstance();
	}

	@Override
	public Basket addProduct(Basket basket, ProductQuantity productQuantity) {
		productQuantity = productMgt.getProductQuantity(productQuantity.getSku());
		return basketMgtImpl.addProduct(basket, productQuantity, 1);
	}

}
