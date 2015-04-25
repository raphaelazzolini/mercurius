package br.unicamp.ic.lsd.mercurius.ordermgr.impl;

import java.io.File;
import java.util.List;
import java.util.Set;

import br.unicamp.ic.lsd.mercurius.datatype.Address;
import br.unicamp.ic.lsd.mercurius.datatype.Basket;
import br.unicamp.ic.lsd.mercurius.datatype.BasketItem;
import br.unicamp.ic.lsd.mercurius.datatype.Order;
import br.unicamp.ic.lsd.mercurius.datatype.OrderProduct;
import br.unicamp.ic.lsd.mercurius.datatype.OrderStatus;
import br.unicamp.ic.lsd.mercurius.datatype.OrderTotal;
import br.unicamp.ic.lsd.mercurius.datatype.Payment;
import br.unicamp.ic.lsd.mercurius.datatype.PaymentMethod;
import br.unicamp.ic.lsd.mercurius.datatype.PaymentNotification;
import br.unicamp.ic.lsd.mercurius.datatype.ShippingService;
import br.unicamp.ic.lsd.mercurius.excpetionhandler.exceptions.OutOfStockException;
import br.unicamp.ic.lsd.mercurius.excpetionhandler.exceptions.ProductNotFoundException;
import br.unicamp.ic.lsd.mercurius.ordermgr.spec.prov.OrderManager;
import br.unicamp.ic.lsd.mercurius.ordermgr.spec.prov.OrderMgt;
import br.unicamp.ic.lsd.mercurius.ordermgr.spec.req.OrderBasketMgt;
import br.unicamp.ic.lsd.mercurius.ordermgr.spec.req.OrderProductMgt;
import br.unicamp.ic.lsd.mercurius.ordermgr.spec.req.PaymentMgt;
import br.unicamp.ic.lsd.mercurius.ordermgr.spec.req.ShippingMgt;

class OrderFacade implements OrderMgt {

	private static final String SHIPPING_MGT = "ShippingMgt";
	private static final String PAYMENT_MGT = "PaymentMgt";
	private static final String ORDER_BASKET_MGT = "OrderBasketMgt";
	private static final String ORDER_PRODUCT_MGT = "OrderProductMgt";
	private final OrderManager manager;
	private final OrderMgtImpl orderMgtImpl;
	private final OrderBasketMgt basketMgt;
	private final ShippingMgt shippingMgt;

	private static boolean checkOrdersPaymentRunning = false;

	OrderFacade(OrderManager manager) {
		super();
		this.manager = manager;
		orderMgtImpl = new OrderMgtImpl(this.manager);
		basketMgt = (OrderBasketMgt) this.manager.getRequiredInterface(ORDER_BASKET_MGT);
		shippingMgt = (ShippingMgt) this.manager.getRequiredInterface(SHIPPING_MGT);
	}

	@Override
	public Order oneClickOrder(Basket basket) {
		OrderStatus firstOrderStatus = manager.getOrderStatusDAO().getFirstOrderStatus();
		Set<BasketItem> basketItems = basketMgt.getBasketItems(basket);
		return orderMgtImpl.oneClickOrder(basket, firstOrderStatus, basketItems);
	}

	@Override
	public File getInvoice(Order order) {
		return orderMgtImpl.getInvoice(order);
	}

	@Override
	public boolean cancelOrder(Order order) {
		OrderStatus canceledStatus = manager.getOrderStatusDAO().getCanceledOrderStatus();
		return orderMgtImpl.cancelOrder(order, canceledStatus);
	}

	@Override
	public Order createOrder(Basket basket) {
		Set<BasketItem> basketItems = basketMgt.getBasketItems(basket);
		return orderMgtImpl.createOrder(basket, basketItems);
	}

	@Override
	public Order saveOrder(Order order, PaymentMethod paymentMethod, String paymentInformation)
			throws ProductNotFoundException, OutOfStockException {
		PaymentMgt paymentMgt = (PaymentMgt) this.manager.getRequiredInterface(PAYMENT_MGT);
		OrderStatus firstOrderStatus = manager.getOrderStatusDAO().getFirstOrderStatus();
		Payment payment = paymentMgt.createPayment(order, paymentInformation, paymentMethod);
		OrderProductMgt orderProductMgt = (OrderProductMgt) manager.getRequiredInterface(ORDER_PRODUCT_MGT);

		for (OrderProduct product : order.getOrderProducts()) {
			if (!orderProductMgt.productExists(product.getProduct().getId())) {
				throw new ProductNotFoundException();
			}
			if (!orderProductMgt.hasQuantityAvailable(product.getSku(), product.getQuantity())) {
				throw new OutOfStockException();
			}
		}

		if (payment != null) {
			order.setPaymentMethod(paymentMethod.getName());
			order = orderMgtImpl.saveOrder(order, firstOrderStatus);

			if (order.getId() != null) {
				payment.setOrder(order);
				paymentMgt.makeAPayment(payment);
				PaymentNotification paymentNotification = paymentMgt.makeAPayment(payment);
				paymentNotification.setOrder(order);
				manager.getPaymentNotificationDAO().merge(paymentNotification);
				basketMgt.cleanBasket();
				return order;
			}
		}

		return null;
	}

	@Override
	public void changeOrderStatus(Order order, OrderStatus status) {
		orderMgtImpl.changeOrderStatus(order, status);
	}

	@Override
	public Order getOrder(Integer orderId) {
		return orderMgtImpl.getOrder(orderId);
	}

	@Override
	public List<ShippingService> calculateShippingService(Order order, Address address) {
		return orderMgtImpl.calculateShippingService(order, address);
	}

	@Override
	public Order chooseShippingService(Order order, ShippingService shippingService) {
		return orderMgtImpl.chooseShippingService(order, shippingService);
	}

	@Override
	public void checkOrdersPaymentStatus() {
		PaymentMgt paymentMgt = (PaymentMgt) this.manager.getRequiredInterface(PAYMENT_MGT);

		if (!checkOrdersPaymentRunning) {
			checkOrdersPaymentRunning = true;
			OrderStatus firstOrderStatus = manager.getOrderStatusDAO().getFirstOrderStatus();
			List<Order> ordersWaitingForPayment = orderMgtImpl.getOrdersWithOrderStatus(firstOrderStatus);
			for (Order order : ordersWaitingForPayment) {
				String transactionId = manager.getPaymentNotificationDAO().getOrderTransactionId(order);
				List<PaymentNotification> paymentNotifications = paymentMgt.checkPaymentNotificatons(transactionId);
				paymentNotifications = orderMgtImpl.checkOrderPayment(order, paymentNotifications);
				for (PaymentNotification paymentNotification : paymentNotifications) {
					paymentNotification.setOrder(order);
					OrderStatus orderStatus;
					switch (paymentNotification.getPaymentStatus()) {
					case CANCELED:
						orderStatus = manager.getOrderStatusDAO().getCanceledOrderStatus();
						orderMgtImpl.changeOrderStatus(order, orderStatus);
						break;
					case CONFIRMED:
						orderStatus = manager.getOrderStatusDAO().getPaymentConfirmedOrderStatus();
						orderMgtImpl.changeOrderStatus(order, orderStatus);
						break;
					case FAILED:
						orderStatus = manager.getOrderStatusDAO().getPaymentFailedOrderStatus();
						orderMgtImpl.changeOrderStatus(order, orderStatus);
						break;
					default:
						break;
					}
					manager.getPaymentNotificationDAO().merge(paymentNotification);
				}
			}
		}

	}

	@Override
	public void sendOrder(Order order, String trackingNumber) {
		OrderStatus sentStatus = manager.getOrderStatusDAO().getOrderSentStatus();
		order.setTrackingNumber(trackingNumber);
		changeOrderStatus(order, sentStatus);
	}

	@Override
	public void checkOrdersArrived() {
		OrderStatus orderStatus = manager.getOrderStatusDAO().getOrderSentStatus();
		List<Order> orders = orderMgtImpl.getOrdersWithOrderStatus(orderStatus);
		orderStatus = manager.getOrderStatusDAO().getOrderArrivedStatus();
		for (Order order : orders) {
			if (shippingMgt.checkOrderArrived(order.getTrackingNumber())) {
				changeOrderStatus(order, orderStatus);
			}
		}
	}

	@Override
	public List<PaymentMethod> getPaymentMethods(Order order) {
		PaymentMgt paymentMgt = (PaymentMgt) this.manager.getRequiredInterface(PAYMENT_MGT);
		return paymentMgt.getPaymentMethods(order);
	}

	@Override
	public OrderTotal createOrderTotal() {
		return manager.getOrderTotalFactory().createInstance();
	}

}
