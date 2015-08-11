package br.unicamp.ic.lsd.mercurius.ordermgr.impl;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.unicamp.ic.lsd.mercurius.datatype.factory.OrderProductFactory;
import br.unicamp.ic.lsd.mercurius.datatype.factory.OrderTotalFactory;
import br.unicamp.ic.lsd.mercurius.orderbasketconnector.BasketConnectorComponentFactory;
import br.unicamp.ic.lsd.mercurius.orderloggingconnector.OrderLoggingConnectorComponentFactory;
import br.unicamp.ic.lsd.mercurius.ordermgr.spec.prov.OrderManager;
import br.unicamp.ic.lsd.mercurius.ordermgr.spec.prov.OrderMgt;
import br.unicamp.ic.lsd.mercurius.ordermgr.spec.req.OrderBasketMgt;
import br.unicamp.ic.lsd.mercurius.ordermgr.spec.req.OrderProductMgt;
import br.unicamp.ic.lsd.mercurius.ordermgr.spec.req.OrderPromotionMgt;
import br.unicamp.ic.lsd.mercurius.ordermgr.spec.req.PaymentMgt;
import br.unicamp.ic.lsd.mercurius.ordermgr.spec.req.ShippingMgt;
import br.unicamp.ic.lsd.mercurius.orderpaymentconnector.PaymentConnectorComponentFactory;
import br.unicamp.ic.lsd.mercurius.orderpromotionconnector.OrderPromotionComponentFactory;
import br.unicamp.ic.lsd.mercurius.persistence.dao.OrderDAO;
import br.unicamp.ic.lsd.mercurius.persistence.dao.OrderProductDAO;
import br.unicamp.ic.lsd.mercurius.persistence.dao.OrderStatusDAO;
import br.unicamp.ic.lsd.mercurius.persistence.dao.OrderStatusHistoryDAO;
import br.unicamp.ic.lsd.mercurius.persistence.dao.OrderTotalDAO;
import br.unicamp.ic.lsd.mercurius.persistence.dao.PaymentNotificationDAO;
import br.unicamp.ic.lsd.mercurius.productconnector.ProductConnectorComponentFactory;
import br.unicamp.ic.sed.cosmos.AManager;
import br.unicamp.ic.sed.cosmos.IManager;

class Manager extends AManager implements OrderManager {

	private static final String ORDER_PROMOTION_MGT = "OrderPromotionMgt";
	private static final String SHIPPING_MGT = "ShippingMgt";
	private static final String PAYMENT_MGT = "PaymentMgt";
	private static final String ORDER_PRODUCT_MGT = "OrderProductMgt";
	private static final String I_MANAGER = "IManager";
	private static final String ORDER_MGT = "OrderMgt";
	private static final String ORDER_BASKET_MGT = "OrderBasketMgt";

	private OrderStatusDAO orderStatusDAO;
	private PaymentNotificationDAO paymentNotificationDAO;
	private OrderDAO orderDAO;
	private OrderProductDAO orderProductDAO;
	private OrderTotalDAO orderTotalDAO;
	private OrderStatusHistoryDAO orderStatusHistoryDAO;
	private OrderProductFactory orderProductFactory;
	private OrderTotalFactory orderTotalFactory;

	Manager() {
		super();
		try {
			Context context = new InitialContext();
			orderStatusDAO = (OrderStatusDAO) context.lookup("java:app/persistence/orderStatusDAO");
			paymentNotificationDAO = (PaymentNotificationDAO) context
					.lookup("java:app/persistence/paymentNotificationDAO");
			orderDAO = (OrderDAO) context.lookup("java:app/persistence/orderDAO");
			orderProductDAO = (OrderProductDAO) context.lookup("java:app/persistence/orderProductDAO");
			orderTotalDAO = (OrderTotalDAO) context.lookup("java:app/persistence/orderTotalDAO");
			orderStatusHistoryDAO = (OrderStatusHistoryDAO) context
					.lookup("java:app/persistence/orderStatusHistoryDAO");
			orderProductFactory = (OrderProductFactory) context.lookup("java:app/persistence/orderProductFactory");
			orderTotalFactory = (OrderTotalFactory) context.lookup("java:app/persistence/orderTotalFactory");

			setProvidedInterfaceType(ORDER_MGT, OrderMgt.class);
			setProvidedInterfaceType(I_MANAGER, IManager.class);

			IManager promotionManager = OrderPromotionComponentFactory.createInstance();
			setRequiredInterfaceType("OrderPromotionMgt", OrderPromotionMgt.class);
			setRequiredInterface("OrderPromotionMgt", promotionManager.getProvidedInterface("OrderPromotionMgt"));

			setRequiredInterfaceType(ORDER_BASKET_MGT, OrderBasketMgt.class);
			setRequiredInterfaceType(ORDER_PRODUCT_MGT, OrderProductMgt.class);
			setRequiredInterfaceType(PAYMENT_MGT, PaymentMgt.class);
			setRequiredInterfaceType(SHIPPING_MGT, ShippingMgt.class);
			setRequiredInterfaceType(ORDER_PROMOTION_MGT, OrderPromotionMgt.class);

			IManager basketConnectorManager = BasketConnectorComponentFactory.createInstance();
			setRequiredInterface(ORDER_BASKET_MGT, basketConnectorManager.getProvidedInterface(ORDER_BASKET_MGT));

			IManager paymentConnectorManager = PaymentConnectorComponentFactory.createInstance();
			setRequiredInterface(PAYMENT_MGT, paymentConnectorManager.getProvidedInterface(PAYMENT_MGT));

			IManager loggingManager = OrderLoggingConnectorComponentFactory.createInstance();
			setRequiredInterface("OrderLoggingMgt", loggingManager.getProvidedInterface("OrderLoggingMgt"));

			IManager orderProductManager = ProductConnectorComponentFactory.createInstance();
			setRequiredInterface(ORDER_PRODUCT_MGT, orderProductManager.getProvidedInterface(ORDER_PRODUCT_MGT));

			setProvidedInterface(I_MANAGER, this);
			setProvidedInterface(ORDER_MGT, new OrderFacade(this));
		} catch (NamingException e) {
		}
	}

	@Override
	public OrderStatusDAO getOrderStatusDAO() {
		return orderStatusDAO;
	}

	@Override
	public PaymentNotificationDAO getPaymentNotificationDAO() {
		return paymentNotificationDAO;
	}

	@Override
	public OrderDAO getOrderDAO() {
		return orderDAO;
	}

	@Override
	public OrderStatusHistoryDAO getOrderStatusHistoryDAO() {
		return orderStatusHistoryDAO;
	}

	@Override
	public OrderProductFactory getOrderProductFactory() {
		return orderProductFactory;
	}

	@Override
	public OrderTotalFactory getOrderTotalFactory() {
		return orderTotalFactory;
	}

	@Override
	public OrderProductDAO getOrderProductDAO() {
		return orderProductDAO;
	}

	@Override
	public OrderTotalDAO getOrderTotalDAO() {
		return orderTotalDAO;
	}

}
