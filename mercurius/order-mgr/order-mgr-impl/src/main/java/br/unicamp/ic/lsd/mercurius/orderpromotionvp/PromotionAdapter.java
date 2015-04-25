package br.unicamp.ic.lsd.mercurius.orderpromotionvp;

import java.util.List;

import br.unicamp.ic.lsd.mercurius.datatype.OrderTotal;
import br.unicamp.ic.lsd.mercurius.datatype.OrderTotalModule;
import br.unicamp.ic.lsd.mercurius.datatype.Product;
import br.unicamp.ic.lsd.mercurius.ordermgr.impl.OrderMgrComponentFactory;
import br.unicamp.ic.lsd.mercurius.ordermgr.spec.prov.OrderManager;

public class PromotionAdapter {

	public PromotionAdapter() {
		super();
	}

	public OrderTotal getOrderDiscount(List<Product> product) {
		OrderManager manager = (OrderManager) OrderMgrComponentFactory.createInstance();
		OrderTotal totalDiscount = manager.getOrderTotalFactory().createInstance();
		totalDiscount.setModule(OrderTotalModule.DISCOUNT);
		return totalDiscount;
	}

}
