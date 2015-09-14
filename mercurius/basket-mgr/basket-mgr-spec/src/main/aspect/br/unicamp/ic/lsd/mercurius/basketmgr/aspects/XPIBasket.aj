package br.unicamp.ic.lsd.mercurius.basketmgr.aspects;

public aspect XPIBasket {

	public pointcut loggingErrorScope() : within(br.unicamp.ic.lsd.mercurius.basketmgr.spec..*);

	public pointcut loggingInfoScope() : execution(public * br.unicamp.ic.lsd.mercurius.basketmgr.spec.none.*(..));

	public pointcut loggingDebugScope() : execution(public * br.unicamp.ic.lsd.mercurius.basketmgr.spec.prov.BasketMgt.*(..));

	public pointcut loggingWarnScope() : execution(public * br.unicamp.ic.lsd.mercurius.basketmgr.spec.none.*(..));

}
