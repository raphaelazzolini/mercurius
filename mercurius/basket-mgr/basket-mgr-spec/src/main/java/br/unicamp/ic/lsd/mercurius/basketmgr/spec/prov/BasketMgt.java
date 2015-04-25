package br.unicamp.ic.lsd.mercurius.basketmgr.spec.prov;

import java.util.List;
import java.util.Set;

import br.unicamp.ic.lsd.mercurius.datatype.Basket;
import br.unicamp.ic.lsd.mercurius.datatype.BasketItem;
import br.unicamp.ic.lsd.mercurius.datatype.Customer;
import br.unicamp.ic.lsd.mercurius.datatype.Product;
import br.unicamp.ic.lsd.mercurius.datatype.ProductAttribute;
import br.unicamp.ic.lsd.mercurius.datatype.ProductQuantity;

public interface BasketMgt {

	/**
	 * Adds a {@link Product} to the {@link Basket}.
	 * 
	 * @param product
	 *            the {@link Product} to be inserted
	 * @param attributes
	 *            the attributes of the {@link Product} to be inserted
	 * @param quantity
	 *            the quantity to be inserted
	 */
	void addToBasket(Product product, List<ProductAttribute> attributes, Integer quantity);

	/**
	 * Adds a {@link Product} to the {@link Basket}.
	 * 
	 * @param sku
	 *            the SKU of the product to be inserted. The SKU is unique for
	 *            each combination of {@link Product} and a {@link List} of
	 *            {@link ProductAttribute}.
	 * @param quantity
	 *            the quantity to be inserted
	 */
	void addToBasket(String sku, Integer quantity);

	/**
	 * Removes the {@link BasketItem} from the {@link Basket}.
	 * 
	 * @param item
	 *            the {@link BasketItem} to be removed
	 */
	void removeFromBasket(BasketItem item);

	/**
	 * Updates the {@link BasketItem} quantity
	 * 
	 * @param item
	 *            the {@link BasketItem} to be updated
	 * @param newQuantity
	 *            the new quantity of the item
	 */
	void updateItemQuantity(BasketItem item, Integer newQuantity);

	/**
	 * Removes all {@link BasketItem} from the {@link Basket}.
	 */
	void cleanBasket();

	/**
	 * Returns the {@link Basket} of the {@link Customer}.
	 * 
	 * @param customer
	 *            the {@link Customer} that owns the {@link Basket} to be
	 *            returned
	 * @return
	 */
	Basket getBasket(Customer customer);

	/**
	 * Returns the {@link Basket} associated with the informed session id.
	 * 
	 * @param sessionId
	 *            the session id that owns the {@link Basket} to be returned
	 * @return
	 */
	Basket getBasket(String sessionId);

	/**
	 * Returns the current {@link Basket}.
	 * 
	 * @return
	 */
	Basket getCurrentBasket();

	/**
	 * Loads the {@link Set} of {@link BasketItem} from the entity. The
	 * {@link Set} in the class that implements {@link Basket} must be fetched
	 * lazily.
	 * 
	 * @param basket
	 */
	void loadBasketItems(Basket basket);

	/**
	 * Creates a new {@link Basket} instance;
	 * 
	 * @return
	 */
	Basket newBasket();

	/**
	 * Returns the given basket with the product in it.
	 * 
	 * @param basket
	 * @param productQuantity
	 * @return
	 */
	Basket addProduct(Basket basket, ProductQuantity productQuantity);

}
