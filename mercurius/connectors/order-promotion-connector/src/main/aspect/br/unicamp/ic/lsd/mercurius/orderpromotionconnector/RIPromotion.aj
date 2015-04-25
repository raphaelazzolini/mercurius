package br.unicamp.ic.lsd.mercurius.orderpromotionconnector;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import br.unicamp.ic.lsd.mercurius.ordermgr.aspects.XPIOrder;
import br.unicamp.ic.lsd.mercurius.ordermgr.spec.prov.OrderMgt;
import br.unicamp.ic.lsd.mercurius.datatype.Basket;
import br.unicamp.ic.lsd.mercurius.datatype.Order;
import br.unicamp.ic.lsd.mercurius.datatype.OrderProduct;
import br.unicamp.ic.lsd.mercurius.datatype.OrderTotal;
import br.unicamp.ic.lsd.mercurius.datatype.OrderTotalModule;
import br.unicamp.ic.lsd.mercurius.datatype.Product;

public aspect RIPromotion {

	private PromotionAdapter promotionAdapter = new PromotionAdapter();

	Order around(Basket basket) : XPIOrder.orderTotalPointcut(Basket) && args(basket) {
		Order order = proceed(basket);
		List<OrderTotal> orderTotals = order.getOrderTotals();
		Set<OrderProduct> orderProducts = order.getOrderProducts();
		List<Product> products = new LinkedList<Product>();
		OrderTotal total = null;

		for (OrderTotal orderTotal : orderTotals) {
			if (orderTotal.getModule().equals(OrderTotalModule.ORDER_TOTAL)) {
				total = orderTotal;
				break;
			}
		}

		for (OrderProduct orderProduct : orderProducts) {
			products.add(orderProduct.getProduct());
		}

		OrderMgt orderMgt = (OrderMgt) thisJoinPoint.getThis();
		OrderTotal totalDiscount = promotionAdapter.getOrderDiscount(products, orderMgt.createOrderTotal());

		if (total != null && totalDiscount != null) {
			BigDecimal newTotal = total.getValue().add(totalDiscount.getValue());
			if (newTotal.compareTo(BigDecimal.ZERO) >= 0) {
				total.setValue(newTotal);
				orderTotals.add(totalDiscount);
			}
		}

		order.setOrderTotals(orderTotals);

		return order;
	}

}
