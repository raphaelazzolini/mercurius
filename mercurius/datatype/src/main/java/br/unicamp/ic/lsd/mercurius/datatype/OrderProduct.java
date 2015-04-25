package br.unicamp.ic.lsd.mercurius.datatype;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public interface OrderProduct extends Serializable {

	/**
	 * Returns the SKU of the {@link Product} of this {@link OrderProduct}.
	 * 
	 * @return
	 */
	String getSku();

	/**
	 * Returns the {@link Product} of this {@link OrderProduct}.
	 * 
	 * @return
	 */
	Product getProduct();

	/**
	 * Returns the attributes of the {@link Product} of this
	 * {@link OrderProduct}.
	 * 
	 * @return
	 */
	List<ProductAttribute> getProductAttributes();

	/**
	 * Returns the price of one item of this {@link OrderProduct}.
	 * 
	 * @return
	 */
	BigDecimal getPrice();

	/**
	 * Sets the price of one item of this {@link OrderProduct}.
	 * 
	 * @param price
	 */
	void setPrice(BigDecimal price);

	/**
	 * Sets the special price of one item of this {@link OrderProduct}.
	 * 
	 * @param price
	 */
	void setSpecialPrice(BigDecimal price);

	/**
	 * Returns the special price of one item of this {@link OrderProduct}.
	 * 
	 * @return
	 */
	BigDecimal getSpecialPrice();

	/**
	 * Returns the total price of this {@link OrderProduct}. This price is the
	 * price of one item multiplied by the quantity of items.
	 * 
	 * @return
	 */
	BigDecimal getTotalPrice();

	/**
	 * Returns the quantity of items of this {@link OrderProduct}.
	 * 
	 * @return
	 */
	Integer getQuantity();

	/**
	 * Sets the quantity of items of this {@link OrderProduct}.
	 * 
	 * @param quantity
	 */
	void setQuantity(Integer quantity);

	/**
	 * Returns the {@link Order} that this {@link OrderProduct} is part of.
	 * 
	 * @return
	 */
	Order getOrder();

	/**
	 * Sets order.
	 * 
	 * @param quantity
	 */
	void setOrder(Order order);

}
