package br.unicamp.ic.lsd.mercurius.datatype;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

public interface Basket extends Serializable {

	/**
	 * Returns the {@link Basket} id.
	 * 
	 * @return
	 */
	Integer getId();

	/**
	 * Returns the {@link Set} of {@link BasketItem} that compose the
	 * {@link Basket}
	 * 
	 * @return
	 */
	Set<BasketItem> getBasketItems();

	/**
	 * Sets the {@link Set} of {@link BasketItem} that compose the
	 * {@link Basket}
	 * 
	 * @param basketItems
	 */
	void setBasketItems(Set<BasketItem> basketItems);

	/**
	 * Returns the id of the session where the {@link Basket} was loaded for the
	 * last time.
	 * 
	 * @return
	 */
	String getSessionId();

	/**
	 * Sets the id of the session where the {@link Basket} was loaded.
	 * 
	 * @param sessionId
	 */
	void setSessionId(String sessionId);

	/**
	 * Returns the {@link Customer} that creates the {@link Basket} or
	 * <code>null</code> if the {@link Basket} was not created by a logged
	 * customer.
	 * 
	 * @return
	 */
	Customer getCustomer();

	/**
	 * Sets the {@link Customer} that created the {@link Basket}.
	 * 
	 * @param customer
	 */
	void setCustomer(Customer customer);

	/**
	 * Returns the date that the {@link Basket} was created.
	 * 
	 * @return
	 */
	Date getDateCreated();

	/**
	 * Sets the date that the {@link Basket} was created.
	 * 
	 * @param dateCreated
	 */
	void setDateCreated(Date dateCreated);

	/**
	 * Get the total price of the items of the {@link Basket}
	 * 
	 * @return
	 */
	BigDecimal getBasketPrice();

}
