package br.unicamp.ic.lsd.mercurius.ordermgr.spec.prov;

import java.io.File;
import java.util.List;

import br.unicamp.ic.lsd.mercurius.datatype.Address;
import br.unicamp.ic.lsd.mercurius.datatype.Basket;
import br.unicamp.ic.lsd.mercurius.datatype.Order;
import br.unicamp.ic.lsd.mercurius.datatype.OrderStatus;
import br.unicamp.ic.lsd.mercurius.datatype.OrderTotal;
import br.unicamp.ic.lsd.mercurius.datatype.PaymentMethod;
import br.unicamp.ic.lsd.mercurius.datatype.ShippingService;
import br.unicamp.ic.lsd.mercurius.excpetionhandler.exceptions.OutOfStockException;
import br.unicamp.ic.lsd.mercurius.excpetionhandler.exceptions.ProductNotFoundException;

public interface OrderMgt {

	/**
	 * Creates and saves a {@link Order} with the informed {@link Basket}. In
	 * this method the payment is made by the customer data saved by the payment
	 * gateway.
	 * 
	 * @param basket
	 * @return
	 */
	Order oneClickOrder(Basket basket);

	/**
	 * Returns the file, in PDF format, with the invoice of the informed
	 * {@link Order}.
	 * 
	 * @param order
	 * @return
	 */
	File getInvoice(Order order);

	/**
	 * Cancels the informed {@link Order}.
	 * 
	 * @param order
	 * @return <code>true</code> if the {@link Order} was successfully
	 *         cancelled.
	 */
	boolean cancelOrder(Order order);

	/**
	 * Creates an {@link Order} with the informed {@link Basket}.
	 * 
	 * @param basket
	 * @return
	 */
	Order createOrder(Basket basket);

	/**
	 * Saves the {@link Order}
	 * 
	 * @param order
	 *            the saved {@link Order}
	 * @param paymentMethod
	 *            the payment method of the order
	 * @param paymentInformation
	 *            the payment information method of the order
	 * @return
	 */
	Order saveOrder(Order order, PaymentMethod paymentMethod, String paymentInformation)
			throws ProductNotFoundException, OutOfStockException;

	/**
	 * Checks the payment status of the orders waiting for payment confirmation
	 * and change the order status if necessary.
	 */
	void checkOrdersPaymentStatus();

	/**
	 * Changes the {@link Order} status.
	 * 
	 * @param order
	 *            the order that will have the status changed
	 * @param status
	 *            the new order status
	 */
	void changeOrderStatus(Order order, OrderStatus status);

	/**
	 * Returns the {@link Order} with the informed id. If there is no order with
	 * this id, <code>null</code> is returned.
	 * 
	 * @param orderId
	 * @return the order with the informed id or <code>null</code> if there is
	 *         no order with the informed id
	 */
	Order getOrder(Integer orderId);

	/**
	 * Returns a {@link List} with the available shipping services for the order
	 * in the informed address.
	 * 
	 * @param order
	 * @param address
	 * @return
	 */
	List<ShippingService> calculateShippingService(Order order, Address address);

	/**
	 * Returns the order with the chosen shipping service calculated.
	 * 
	 * @param order
	 * @param shippingService
	 * @return
	 */
	Order chooseShippingService(Order order, ShippingService shippingService);

	/**
	 * Changes the status of the order to the order sent status.
	 * 
	 * @param order
	 * @param trackingNumber
	 */
	void sendOrder(Order order, String trackingNumber);

	/**
	 * Check if the orders with sent status have arrived in the delivered
	 * address and change it's status if necessary.
	 */
	void checkOrdersArrived();

	/**
	 * Returns the available payment methods for the informed order.
	 * 
	 * @param order
	 * @return
	 */
	List<PaymentMethod> getPaymentMethods(Order order);

	/**
	 * Creates a new {@link OrderTotal} instance.
	 * 
	 * @return
	 */
	OrderTotal createOrderTotal();

}
