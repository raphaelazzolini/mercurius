package br.unicamp.ic.lsd.mercurius.datatype;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public interface BasketItem extends Serializable {

	/**
	 * Returns the SKU of the {@link Product} of this {@link BasketItem}.
	 * 
	 * @return
	 */
	String getSku();

	/**
	 * Returns the {@link Product} of this {@link BasketItem}.
	 * 
	 * @return
	 */
	Product getProduct();

	/**
	 * Returns the attributes of the {@link Product} of this {@link BasketItem}.
	 * 
	 * @return
	 */
	List<ProductAttribute> getProductAttributes();

	/**
	 * Returns the price of one item of this {@link BasketItem}.
	 * 
	 * @return
	 */
	BigDecimal getItemPrice();

	/**
	 * Sets the special price of one item of this {@link BasketItem}.
	 * 
	 * @param price
	 */
	void setItemSpecialPrice(BigDecimal price);

	/**
	 * Returns the special price of one item of this {@link BasketItem}.
	 * 
	 * @return
	 */
	BigDecimal getItemSpecialPrice();

	/**
	 * Sets the price of one item of this {@link BasketItem}.
	 * 
	 * @param price
	 */
	void setItemPrice(BigDecimal price);

	/**
	 * Returns the total price of this {@link BasketItem}. This price is the
	 * price of one item multiplied by the quantity of items.
	 * 
	 * @return
	 */
	BigDecimal getTotalPrice();

	/**
	 * Returns the quantity of items of this {@link BasketItem}.
	 * 
	 * @return
	 */
	Integer getQuantity();

	/**
	 * Sets the quantity of items of this {@link BasketItem}.
	 * 
	 * @param quantity
	 */
	void setQuantity(Integer quantity);

	/**
	 * Returns the {@link Basket} that this {@link BasketItem} is part of.
	 * 
	 * @return
	 */
	Basket getBasket();

}
