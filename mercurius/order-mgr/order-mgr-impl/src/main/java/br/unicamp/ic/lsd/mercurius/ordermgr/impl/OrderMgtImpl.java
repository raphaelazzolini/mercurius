package br.unicamp.ic.lsd.mercurius.ordermgr.impl;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.unicamp.ic.lsd.mercurius.datatype.Address;
import br.unicamp.ic.lsd.mercurius.datatype.Basket;
import br.unicamp.ic.lsd.mercurius.datatype.BasketItem;
import br.unicamp.ic.lsd.mercurius.datatype.Customer;
import br.unicamp.ic.lsd.mercurius.datatype.Order;
import br.unicamp.ic.lsd.mercurius.datatype.OrderProduct;
import br.unicamp.ic.lsd.mercurius.datatype.OrderStatus;
import br.unicamp.ic.lsd.mercurius.datatype.OrderStatusHistory;
import br.unicamp.ic.lsd.mercurius.datatype.OrderTotal;
import br.unicamp.ic.lsd.mercurius.datatype.OrderTotalModule;
import br.unicamp.ic.lsd.mercurius.datatype.PaymentNotification;
import br.unicamp.ic.lsd.mercurius.datatype.Product;
import br.unicamp.ic.lsd.mercurius.datatype.ProductAttribute;
import br.unicamp.ic.lsd.mercurius.datatype.ShippingService;
import br.unicamp.ic.lsd.mercurius.ordermgr.spec.prov.OrderManager;
import br.unicamp.ic.lsd.mercurius.ordermgr.spec.req.ShippingMgt;

class OrderMgtImpl {

	private static final String PRODUCTS_DISCOUNT = "Products Discount";

	private final OrderManager manager;

	OrderMgtImpl(OrderManager manager) {
		super();
		this.manager = manager;
	}

	public Order oneClickOrder(Basket basket, OrderStatus firstOrderStatus, Set<BasketItem> basketItems) {
		Order order = createOrder(basket, basketItems);
		order = saveOrder(order, firstOrderStatus);
		return order;
	}

	public File getInvoice(Order order) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean cancelOrder(Order order, OrderStatus canceledStatus) {
		OrderStatus orderStatus = order.getOrderStatus();

		if (orderStatus.isCancelable()) {
			if (canceledStatus != null) {
				changeOrderStatus(order, canceledStatus);
			}
			return true;
		}

		return false;
	}

	public Order createOrder(Basket basket, Set<BasketItem> basketItems) {
		Order order = manager.getOrderDAO().newInstance();
		Set<OrderProduct> orderProducts = getOrderProductsFromBasket(basket, basketItems);
		List<OrderTotal> orderTotals = getOrderTotals(order, orderProducts, basket.getCustomer());

		order.setCustomer(basket.getCustomer());
		order.setOrderProducts(orderProducts);
		order.setOrderTotals(orderTotals);

		return order;
	}

	public Order saveOrder(Order order, OrderStatus firstOrderStatus) {
		Date today = new Date();
		boolean saveProducts = false;
		Set<OrderProduct> products = order.getOrderProducts();
		List<OrderTotal> totals = order.getOrderTotals();

		if (order.getId() == null) {
			saveProducts = true;
			order.setOrderProducts(null);
			order.setOrderTotals(null);
		}

		order.setDatePurchased(today);
		order.setDateModified(today);
		order.setOrderStatus(firstOrderStatus);

		order = manager.getOrderDAO().merge(order);

		if (saveProducts) {
			for (OrderProduct product : products) {
				product.setOrder(order);
				manager.getOrderProductDAO().merge(product);
			}
			for (OrderTotal total : totals) {
				total.setOrder(order);
				manager.getOrderTotalDAO().merge(total);
			}
		}

		OrderStatusHistory statusHistory = manager.getOrderStatusHistoryDAO().newInstance();
		statusHistory.setCustomerNotified(false);
		statusHistory.setDateAdded(today);
		statusHistory.setOrderStatus(firstOrderStatus);
		statusHistory.setOrder(order);
		manager.getOrderStatusHistoryDAO().merge(statusHistory);

		return order;
	}

	public void changeOrderStatus(Order order, OrderStatus status) {
		Date today = new Date();

		order.setOrderStatus(status);
		order = manager.getOrderDAO().merge(order);

		OrderStatusHistory statusHistory = manager.getOrderStatusHistoryDAO().newInstance();
		statusHistory.setCustomerNotified(false);
		statusHistory.setDateAdded(today);
		statusHistory.setOrderStatus(status);
		statusHistory.setOrder(order);
		manager.getOrderStatusHistoryDAO().merge(statusHistory);
	}

	public Order getOrder(Integer orderId) {
		return manager.getOrderDAO().findById(orderId);
	}

	private Set<OrderProduct> getOrderProductsFromBasket(Basket basket, Set<BasketItem> basketItems) {
		Set<OrderProduct> orderProducts = new HashSet<OrderProduct>();
		for (BasketItem item : basketItems) {
			String sku = item.getSku();
			Product product = item.getProduct();
			List<ProductAttribute> attributes = item.getProductAttributes();
			OrderProduct orderProduct = manager.getOrderProductFactory().createInstance(sku, product, attributes);
			orderProduct.setPrice(item.getItemPrice());
			orderProduct.setSpecialPrice(item.getItemSpecialPrice());
			orderProduct.setQuantity(item.getQuantity());
			orderProducts.add(orderProduct);
		}
		return orderProducts;
	}

	private List<OrderTotal> getOrderTotals(Order order, Set<OrderProduct> orderProducts, Customer customer) {
		List<OrderTotal> orderTotals = new ArrayList<OrderTotal>();
		BigDecimal productsTotal = BigDecimal.ZERO;
		BigDecimal totalValue = BigDecimal.ZERO;
		for (OrderProduct orderProduct : orderProducts) {
			BigDecimal price = orderProduct.getPrice();
			productsTotal = productsTotal.add(price);
			totalValue = totalValue.add(price);
		}

		OrderTotal orderProductsTotal = manager.getOrderTotalFactory().createInstance();
		orderProductsTotal.setModule(OrderTotalModule.PRODUCTS_TOTAL);
		orderProductsTotal.setValue(productsTotal);
		orderTotals.add(orderProductsTotal);

		for (OrderTotal orderTotal : orderTotals) {
			if (orderTotal.getModule().equals(OrderTotalModule.SHIPPING_TOTAL)) {
				totalValue = totalValue.add(orderTotal.getValue());
			}
		}
		OrderTotal total = manager.getOrderTotalFactory().createInstance();
		total.setModule(OrderTotalModule.ORDER_TOTAL);
		total.setValue(totalValue);
		orderTotals.add(total);

		return orderTotals;
	}

	public List<ShippingService> calculateShippingService(Order order, Address address) {
		Set<OrderProduct> products = order.getOrderProducts();
		BigDecimal width = BigDecimal.ZERO;
		BigDecimal height = BigDecimal.ZERO;
		BigDecimal weight = BigDecimal.ZERO;

		for (OrderProduct orderProduct : products) {
			Product product = orderProduct.getProduct();
			width = width.add(product.getWidth());
			height = height.add(product.getHeight());
			weight = weight.add(product.getWeight());
		}

		ShippingMgt shippingMgt = (ShippingMgt) manager.getRequiredInterface(ShippingMgt.class.getName());

		return shippingMgt.getOrderShippingServices(width, height, weight, address);
	}

	public Order chooseShippingService(Order order, ShippingService shippingService) {
		order.setShippingService(shippingService.getName());

		OrderTotal shippingTotal = manager.getOrderTotalFactory().createInstance();
		shippingTotal.setModule(OrderTotalModule.SHIPPING_TOTAL);
		shippingTotal.setValue(shippingService.getValue());
		shippingTotal.setTitle(shippingService.getName());
		shippingTotal.setText(shippingService.getDescription());
		order.getOrderTotals().add(shippingTotal);

		return order;
	}

	public List<Order> getOrdersWithOrderStatus(OrderStatus firstOrderStatus) {
		return manager.getOrderDAO().getOrdersWithOrderStatus(firstOrderStatus);
	}

	public List<PaymentNotification> checkOrderPayment(Order order, List<PaymentNotification> paymentNotifications) {
		manager.getOrderDAO().loadOrderPaymentNotifications(order);
		paymentNotifications.removeAll(order.getPaymentNotifications());
		return paymentNotifications;
	}
}
