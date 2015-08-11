package br.unicamp.ic.lsd.mercurius.ordermgr.spec.req;

import br.unicamp.ic.lsd.mercurius.datatype.Order;
import br.unicamp.ic.lsd.mercurius.datatype.OrderTotal;

public interface OrderPromotionMgt {

	Order getOrderWithDiscount(Order order, OrderTotal totalDiscount);

}
