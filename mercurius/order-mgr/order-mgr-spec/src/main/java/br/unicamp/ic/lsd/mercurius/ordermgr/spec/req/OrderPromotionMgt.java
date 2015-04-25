package br.unicamp.ic.lsd.mercurius.ordermgr.spec.req;

import java.util.List;

import br.unicamp.ic.lsd.mercurius.datatype.Customer;
import br.unicamp.ic.lsd.mercurius.datatype.Order;
import br.unicamp.ic.lsd.mercurius.datatype.Promotion;

public interface OrderPromotionMgt {

	List<Promotion> getPromotionsForOrder(Order order, Customer customer);

}
