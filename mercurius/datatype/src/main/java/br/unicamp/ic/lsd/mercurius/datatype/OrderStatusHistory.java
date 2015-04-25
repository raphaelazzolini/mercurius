package br.unicamp.ic.lsd.mercurius.datatype;

import java.io.Serializable;
import java.util.Date;

public interface OrderStatusHistory extends Serializable {

	/**
	 * Returns the status history id.
	 * 
	 * @return
	 */
	Integer getId();

	/**
	 * Returns the order of the status history.
	 * 
	 * @return
	 */
	Order getOrder();

	/**
	 * Sets the order of the status history.
	 */
	void setOrder(Order order);

	/**
	 * Returns the order status.
	 * 
	 * @return
	 */
	OrderStatus getOrderStatus();

	/**
	 * Sets the order status.
	 * 
	 * @param orderStatus
	 */
	void setOrderStatus(OrderStatus orderStatus);

	/**
	 * Returns the date that the order status was changed.
	 * 
	 * @return
	 */
	Date getDateAdded();

	/**
	 * Sets the date that the order status was changed.
	 */
	void setDateAdded(Date dateAdded);

	/**
	 * Returns the comments of the status change.
	 * 
	 * @return
	 */
	String getComments();

	/**
	 * Sets the comments of the status change.
	 * 
	 * @param comments
	 */
	void setComments(String comments);

	/**
	 * Returns <code>true</code> if the customer was notified about the status
	 * change.
	 * 
	 * @return
	 */
	boolean isCustomerNotified();

	/**
	 * Sets if the customer was notified about the status change.
	 * 
	 * @param notified
	 */
	void setCustomerNotified(boolean notified);

}
