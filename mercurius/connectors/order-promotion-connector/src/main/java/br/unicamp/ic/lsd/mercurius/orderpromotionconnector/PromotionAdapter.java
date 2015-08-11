package br.unicamp.ic.lsd.mercurius.orderpromotionconnector;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import br.unicamp.ic.lsd.mercurius.datatype.Order;
import br.unicamp.ic.lsd.mercurius.datatype.OrderProduct;
import br.unicamp.ic.lsd.mercurius.datatype.OrderTotal;
import br.unicamp.ic.lsd.mercurius.datatype.OrderTotalModule;
import br.unicamp.ic.lsd.mercurius.datatype.Product;
import br.unicamp.ic.lsd.mercurius.ordermgr.spec.req.OrderPromotionMgt;
import br.unicamp.ic.lsd.mercurius.promotionmgr.spec.prov.PromotionMgt;
import br.unicamp.ic.sed.cosmos.IManager;

class PromotionAdapter implements OrderPromotionMgt {

	private final IManager manager;

	PromotionAdapter(IManager manager) {
		super();
		this.manager = manager;
	}

	@Override
	public Order getOrderWithDiscount(Order order, OrderTotal totalDiscount) {
		PromotionMgt promotionMgt = (PromotionMgt) manager.getRequiredInterface("PromotionMgt");
		List<OrderTotal> orderTotals = order.getOrderTotals();
		Set<OrderProduct> orderProducts = order.getOrderProducts();
		List<Product> products = new LinkedList<>();
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

		totalDiscount = promotionMgt.orderTotalDiscount(products, totalDiscount);

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
