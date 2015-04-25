package br.unicamp.ic.lsd.mercurius.ordermgr.aspects;

import br.unicamp.ic.lsd.mercurius.datatype.Basket;
import br.unicamp.ic.lsd.mercurius.datatype.Order;

public aspect XPIOrder {

	public pointcut loggingErrorScope() : within(br.unicamp.ic.lsd.mercurius.ordermgr.spec..*);

	public pointcut loggingInfoScope() : execution(public * br.unicamp.ic.lsd.mercurius.ordermgr.spec.none.*(..));

	public pointcut loggingDebugScope() : execution(public * br.unicamp.ic.lsd.mercurius.ordermgr.spec.prov.OrderMgt.*(..));

	public pointcut loggingWarnScope() : execution(public * br.unicamp.ic.lsd.mercurius.ordermgr.spec.none.*(..));

	public pointcut orderTotalPointcut(Basket basket) : execution(public Order br.unicamp.ic.lsd.mercurius.ordermgr.spec.prov.OrderMgt.createOrder(Basket)) && args(basket);

}
