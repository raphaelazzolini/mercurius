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
import br.unicamp.ic.lsd.mercurius.ordermgr.spec.req.OrderLoggingMgt;
import br.unicamp.ic.lsd.mercurius.ordermgr.spec.req.OrderProductMgt;
import br.unicamp.ic.lsd.mercurius.ordermgr.spec.req.OrderPromotionMgt;
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
	private final OrderLoggingMgt loggingMgt;

	private static boolean checkOrdersPaymentRunning = false;

	OrderFacade(OrderManager manager) {
		super();
		this.manager = manager;
		orderMgtImpl = new OrderMgtImpl(this.manager);
		basketMgt = (OrderBasketMgt) this.manager.getRequiredInterface(ORDER_BASKET_MGT);
		shippingMgt = (ShippingMgt) this.manager.getRequiredInterface(SHIPPING_MGT);
		loggingMgt = (OrderLoggingMgt) this.manager.getRequiredInterface("OrderLoggingMgt");
	}

	@Override
	public Order oneClickOrder(Basket basket) {
		try {
			loggingMgt.debug("Executing method oneClickOrder");
			OrderStatus firstOrderStatus = manager.getOrderStatusDAO().getFirstOrderStatus();
			Set<BasketItem> basketItems = basketMgt.getBasketItems(basket);
			return orderMgtImpl.oneClickOrder(basket, firstOrderStatus, basketItems);
		} catch (Exception e) {
			loggingMgt.error("Error executing method oneClickOrder", e);
			throw e;
		}
	}

	@Override
	public File getInvoice(Order order) {
		try {
			loggingMgt.debug("Executing method getInvoice");
			return orderMgtImpl.getInvoice(order);
		} catch (Exception e) {
			loggingMgt.error("Error executing method getInvoice", e);
			throw e;
		}
	}

	@Override
	public boolean cancelOrder(Order order) {
		try {
			loggingMgt.debug("Executing method cancelOrder");
			OrderStatus canceledStatus = manager.getOrderStatusDAO().getCanceledOrderStatus();
			return orderMgtImpl.cancelOrder(order, canceledStatus);
		} catch (Exception e) {
			loggingMgt.error("Error executing method cancelOrder", e);
			throw e;
		}
	}

	@Override
	public Order createOrder(Basket basket) {
		try {
			loggingMgt.debug("Executing method createOrder");
			OrderPromotionMgt promotionMgt = (OrderPromotionMgt) manager.getRequiredInterface("OrderPromotionMgt");
			Set<BasketItem> basketItems = basketMgt.getBasketItems(basket);
			Order order = orderMgtImpl.createOrder(basket, basketItems);
			OrderTotal totalDiscount = manager.getOrderTotalFactory().createInstance();
			return promotionMgt.getOrderWithDiscount(order, totalDiscount);
		} catch (Exception e) {
			loggingMgt.error("Error executing method createOrder", e);
			throw e;
		}
	}

	@Override
	public Order saveOrder(Order order, PaymentMethod paymentMethod, String paymentInformation)
			throws ProductNotFoundException, OutOfStockException {
		try {
			loggingMgt.debug("Executing method saveOrder");
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
		} catch (Exception e) {
			loggingMgt.error("Error executing method saveOrder", e);
		}

		return null;
	}

	@Override
	public void changeOrderStatus(Order order, OrderStatus status) {
		try {
			loggingMgt.debug("Executing method changeOrderStatus");
			orderMgtImpl.changeOrderStatus(order, status);
			loggingMgt.debug("Finishing method changeOrderStatus");
		} catch (Exception e) {
			loggingMgt.error("Error executing method changeOrderStatus", e);
			throw e;
		}
	}

	@Override
	public Order getOrder(Integer orderId) {
		try {
			loggingMgt.debug("Executing method getOrder");
			return orderMgtImpl.getOrder(orderId);
		} catch (Exception e) {
			loggingMgt.error("Error executing method getOrder", e);
			throw e;
		}
	}

	@Override
	public List<ShippingService> calculateShippingService(Order order, Address address) {
		try {
			loggingMgt.debug("Executing method calculateShippingService");
			return orderMgtImpl.calculateShippingService(order, address);
		} catch (Exception e) {
			loggingMgt.error("Error executing method calculateShippingService", e);
			throw e;
		}
	}

	@Override
	public Order chooseShippingService(Order order, ShippingService shippingService) {
		try {
			loggingMgt.debug("Executing method chooseShippingService");
			return orderMgtImpl.chooseShippingService(order, shippingService);
		} catch (Exception e) {
			loggingMgt.error("Error executing method chooseShippingService", e);
			throw e;
		}
	}

	@Override
	public void checkOrdersPaymentStatus() {
		try {
			loggingMgt.debug("Executing method checkOrdersPaymentStatus");
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
		} catch (Exception e) {
			loggingMgt.error("Error executing method checkOrdersPaymentStatus", e);
			throw e;
		}

	}

	@Override
	public void sendOrder(Order order, String trackingNumber) {
		try {
			loggingMgt.debug("Executing method sendOrder");
			OrderStatus sentStatus = manager.getOrderStatusDAO().getOrderSentStatus();
			order.setTrackingNumber(trackingNumber);
			changeOrderStatus(order, sentStatus);
			loggingMgt.debug("Finishing method sendOrder");
		} catch (Exception e) {
			loggingMgt.error("Error executing method sendOrder", e);
			throw e;
		}
	}

	@Override
	public void checkOrdersArrived() {
		try {
			loggingMgt.debug("Executing method checkOrdersArrived");
			OrderStatus orderStatus = manager.getOrderStatusDAO().getOrderSentStatus();
			List<Order> orders = orderMgtImpl.getOrdersWithOrderStatus(orderStatus);
			orderStatus = manager.getOrderStatusDAO().getOrderArrivedStatus();
			for (Order order : orders) {
				if (shippingMgt.checkOrderArrived(order.getTrackingNumber())) {
					changeOrderStatus(order, orderStatus);
				}
			}
			loggingMgt.debug("Finishing method checkOrdersArrived");
		} catch (Exception e) {
			loggingMgt.error("Error executing method checkOrdersArrived", e);
			throw e;
		}
	}

	@Override
	public List<PaymentMethod> getPaymentMethods(Order order) {
		try {
			loggingMgt.debug("Executing method getPaymentMethods");
			PaymentMgt paymentMgt = (PaymentMgt) this.manager.getRequiredInterface(PAYMENT_MGT);
			return paymentMgt.getPaymentMethods(order);
		} catch (Exception e) {
			loggingMgt.error("Error executing method getPaymentMethods", e);
			throw e;
		}
	}

	@Override
	public OrderTotal createOrderTotal() {
		// TODO Auto-generated method stub
		return null;
	}

}
