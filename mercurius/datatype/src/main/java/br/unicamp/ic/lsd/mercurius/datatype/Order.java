package br.unicamp.ic.lsd.mercurius.datatype;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

public interface Order extends Serializable {

	/**
	 * Returns the order id.
	 * 
	 * @return
	 */
	Integer getId();

	/**
	 * Returns the customer that made the order.
	 * 
	 * @return
	 */
	Customer getCustomer();

	/**
	 * Sets the customer that made the order.
	 */
	void setCustomer(Customer customer);

	/**
	 * Returns the name from the person that will receive the order's bill.
	 * 
	 * @return
	 */
	String getBillingName();

	/**
	 * Sets the name from the person that will receive the order's bill.
	 * 
	 * @return
	 */
	void setBillingName(String name);

	/**
	 * Returns the city where the order's bill will be sent.
	 * 
	 * @return
	 */
	String getBillingCity();

	/**
	 * Sets the city where the order's bill will be sent.
	 * 
	 * @return
	 */
	void setBillingCity(String city);

	/**
	 * Returns the State where the order's bill will be sent.
	 * 
	 * @return
	 */
	String getBillingState();

	/**
	 * Sets the State where the order's bill will be sent.
	 * 
	 * @return
	 */
	void setBillingState(String state);

	/**
	 * Returns the suburb where the order's bill will be sent.
	 * 
	 * @return
	 */
	String getBillingSuburb();

	/**
	 * Sets the suburb where the order's bill will be sent.
	 * 
	 * @return
	 */
	void setBillingSuburb(String suburb);

	/**
	 * Returns the post code where the order's bill will be sent.
	 * 
	 * @return
	 */
	String getBillingPostCode();

	/**
	 * Sets the post code where the order's bill will be sent.
	 * 
	 * @return
	 */
	void setBillingPostCode(String suburb);

	/**
	 * Returns the first line of the address where the order's bill will be
	 * sent.
	 * 
	 * @return
	 */
	String getBillingAddress1();

	/**
	 * Sets the first line of the address where the order's bill will be sent.
	 * 
	 * @return
	 */
	void setBillingAddress1(String address);

	/**
	 * Returns the second line of the address where the order's bill will be
	 * sent.
	 * 
	 * @return
	 */
	String getBillingAddress2();

	/**
	 * Sets the second line of the address where the order's bill will be sent.
	 * 
	 * @return
	 */
	void setBillingAddress2(String address);

	/**
	 * Returns the telephone from the place where the order's bill will be sent.
	 * 
	 * @return
	 */
	String getBillingTelephone();

	/**
	 * Sets the telephone from the place where the order's bill will be sent.
	 * 
	 * @return
	 */
	void setBillingTelephone(String telephone);

	/**
	 * Returns the name from the customer that made order.
	 * 
	 * @return
	 */
	String getCustomerName();

	/**
	 * Sets the name from the customer that made order.
	 * 
	 * @return
	 */
	void setCustomerName(String name);

	/**
	 * Returns the city from the customer that made the order.
	 * 
	 * @return
	 */
	String getCustomerCity();

	/**
	 * Sets the city from the customer that made the order.
	 * 
	 * @return
	 */
	void setCustomerCity(String city);

	/**
	 * Returns the State from the customer that made the order.
	 * 
	 * @return
	 */
	String getCustomerState();

	/**
	 * Sets the State from the customer that made the order.
	 * 
	 * @return
	 */
	void setCustomerState(String state);

	/**
	 * Returns the suburb from the customer that made the order.
	 * 
	 * @return
	 */
	String getCustomerSuburb();

	/**
	 * Sets the suburb from the customer that made the order.
	 * 
	 * @return
	 */
	void setCustomerSuburb(String suburb);

	/**
	 * Returns the post code from the customer that made the order.
	 * 
	 * @return
	 */
	String getCustomerPostCode();

	/**
	 * Sets the post code from the customer that made the order.
	 * 
	 * @return
	 */
	void setCustomerPostCode(String suburb);

	/**
	 * Returns the first line of the address from the customer that made the
	 * order.
	 * 
	 * @return
	 */
	String getCustomerAddress1();

	/**
	 * Sets the first line of the address from the customer that made the order.
	 * 
	 * @return
	 */
	void setCustomerAddress1(String address);

	/**
	 * Returns the second line of the address from the customer that made the
	 * order.
	 * 
	 * @return
	 */
	String getCustomerAddress2();

	/**
	 * Sets the second line of the address from the customer that made the
	 * order.
	 * 
	 * @return
	 */
	void setCustomerAddress2(String address);

	/**
	 * Returns the telephone from the place from the customer that made the
	 * order.
	 * 
	 * @return
	 */
	String getCustomerTelephone();

	/**
	 * Sets the telephone from the place from the customer that made the order.
	 * 
	 * @return
	 */
	void setCustomerTelephone(String telephone);

	/**
	 * Returns the name from the person that will receive the order.
	 * 
	 * @return
	 */
	String getDeliveryName();

	/**
	 * Sets the name from the person that will receive the order.
	 * 
	 * @return
	 */
	void setDeliveryName(String name);

	/**
	 * Returns the city where the order will be delivered.
	 * 
	 * @return
	 */
	String getDeliveryCity();

	/**
	 * Sets the city where the order will be delivered.
	 * 
	 * @return
	 */
	void setDeliveryCity(String city);

	/**
	 * Returns the State where the order will be delivered.
	 * 
	 * @return
	 */
	String getDeliveryState();

	/**
	 * Sets the State where the order bill will be delivered.
	 * 
	 * @return
	 */
	void setDeliveryState(String state);

	/**
	 * Returns the suburb where the order will be delivered.
	 * 
	 * @return
	 */
	String getDeliverySuburb();

	/**
	 * Sets the suburb where the order bill will be delivered.
	 * 
	 * @return
	 */
	void setDeliverySuburb(String suburb);

	/**
	 * Returns the post code where the order will be delivered.
	 * 
	 * @return
	 */
	String getDeliveryPostCode();

	/**
	 * Sets the post code where the order will be delivered.
	 * 
	 * @return
	 */
	void setDeliveryPostCode(String suburb);

	/**
	 * Returns the first line of the address where the order will be delivered.
	 * 
	 * @return
	 */
	String getDeliveryAddress1();

	/**
	 * Sets the first line of the address where the order will be delivered.
	 * 
	 * @return
	 */
	void setDeliveryAddress1(String address);

	/**
	 * Returns the second line of the address where the order will be delivered.
	 * 
	 * @return
	 */
	String getDeliveryAddress2();

	/**
	 * Sets the second line of the address where the order will be delivered.
	 * 
	 * @return
	 */
	void setDeliveryAddress2(String address);

	/**
	 * Returns the telephone from the place where the order will be delivered.
	 * 
	 * @return
	 */
	String getDeliveryTelephone();

	/**
	 * Sets the telephone from the place where the order will be delivered.
	 * 
	 * @return
	 */
	void setDeliveryTelephone(String telephone);

	/**
	 * Returns the date that the order was purchased.
	 * 
	 * @return
	 */
	Date getDatePurchased();

	/**
	 * Sets the date that the order was purchased.
	 * 
	 * @return
	 */
	void setDatePurchased(Date date);

	/**
	 * Returns the date that the order was finished.
	 * 
	 * @return
	 */
	Date getDateFinished();

	/**
	 * Sets the date that the order was finished.
	 * 
	 * @return
	 */
	void setDateFinished(Date date);

	/**
	 * Returns the last date that the order was modified.
	 * 
	 * @return
	 */
	Date getDateModified();

	/**
	 * Sets the last date that the order was modified.
	 * 
	 * @return
	 */
	void setDateModified(Date date);

	/**
	 * Returns the order status.
	 * 
	 * @return
	 */
	OrderStatus getOrderStatus();

	/**
	 * Sets the order status.
	 * 
	 * @param status
	 */
	void setOrderStatus(OrderStatus status);

	/**
	 * Returns the order's shipping service.
	 * 
	 * @return
	 */
	String getShippingService();

	/**
	 * Sets the order's shipping service.
	 * 
	 * @return
	 */
	void setShippingService(String shipping);

	/**
	 * Returns the order's payment method.
	 * 
	 * @return
	 */
	String getPaymentMethod();

	/**
	 * Sets the order's payment method.
	 * 
	 * @param paymentMethod
	 */
	void setPaymentMethod(String paymentMethod);

	/**
	 * Returns the order's totals.
	 * 
	 * @return
	 */
	List<OrderTotal> getOrderTotals();

	/**
	 * Sets the order's totals.
	 * 
	 * @param totals
	 */
	void setOrderTotals(List<OrderTotal> totals);

	/**
	 * Returns the order's status histories.
	 * 
	 * @return
	 */
	List<OrderStatusHistory> getOrderStatusHistories();

	/**
	 * Sets the order's status histories.
	 * 
	 * @return
	 */
	void setOrderStatusHistories(List<OrderStatusHistory> statusHistories);

	/**
	 * Returns the total cost of the order.
	 * 
	 * @return
	 */
	BigDecimal getOrderTotal();

	/**
	 * Returns the order's products.
	 * 
	 * @return
	 */
	Set<OrderProduct> getOrderProducts();

	/**
	 * Sets the order's products.
	 * 
	 * @param products
	 */
	void setOrderProducts(Set<OrderProduct> products);

	/**
	 * Returns the order's payment notifications.
	 * 
	 * @return
	 */
	List<PaymentNotification> getPaymentNotifications();

	/**
	 * Returns the order tracking number to track the order delivery status.
	 * 
	 * @return
	 */
	String getTrackingNumber();

	/**
	 * Sets the order tracking number to track the order delivery status.
	 * 
	 * @param trackingNumber
	 */
	void setTrackingNumber(String trackingNumber);

}
