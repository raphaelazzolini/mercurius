package br.unicamp.ic.lsd.mercurius.datatype;

import java.io.Serializable;
import java.math.BigDecimal;

public interface OrderTotal extends Serializable {

	/**
	 * Returns the id.
	 * 
	 * @return
	 */
	Integer getId();

	/**
	 * Returns the {@link Order} that contains this {@link OrderTotal}.
	 * 
	 * @return
	 */
	Order getOrder();

	/**
	 * Sets the {@link Order} that contains this {@link OrderTotal}.
	 * 
	 * @param order
	 */
	void setOrder(Order order);

	/**
	 * Returns the module of this {@link OrderTotal}.
	 * 
	 * @return
	 */
	OrderTotalModule getModule();

	/**
	 * Sets the module of this {@link OrderTotal}.
	 * 
	 * @param name
	 */
	void setModule(OrderTotalModule name);

	/**
	 * Returns the title.
	 * 
	 * @return
	 */
	String getTitle();

	/**
	 * Sets the title.
	 * 
	 * @param title
	 */
	void setTitle(String title);

	/**
	 * Returns the text.
	 * 
	 * @return
	 */
	String getText();

	/**
	 * Sets the text.
	 * 
	 * @param text
	 */
	void setText(String text);

	/**
	 * Returns the value.
	 * 
	 * @return
	 */
	BigDecimal getValue();

	/**
	 * Sets the value.
	 * 
	 * @param price
	 */
	void setValue(BigDecimal value);

}
