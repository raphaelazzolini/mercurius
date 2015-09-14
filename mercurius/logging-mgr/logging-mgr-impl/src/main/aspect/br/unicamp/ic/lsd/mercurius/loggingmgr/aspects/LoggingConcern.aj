package br.unicamp.ic.lsd.mercurius.loggingmgr.aspects;

import br.unicamp.ic.lsd.mercurius.loggingmgr.impl.LoggingMgrComponentFactory;
import br.unicamp.ic.lsd.mercurius.loggingmgr.spec.prov.LoggingMgt;

public privileged aspect LoggingConcern {

	private LoggingMgt loggingMgt = (LoggingMgt) LoggingMgrComponentFactory.createInstance().getProvidedInterface("LoggingMgt");

	public pointcut errorScope() : within(br.unicamp.ic.lsd.mercurius.basketmgr.spec..*)
		|| within(br.unicamp.ic.lsd.mercurius.ordermgr.spec..*)
		|| within(br.unicamp.ic.lsd.mercurius.productsearchmgr.spec..*);

	public pointcut debugScopeBefore() : execution(public * br.unicamp.ic.lsd.mercurius.basketmgr.spec.prov.BasketMgt.*(..))
		|| execution(public * br.unicamp.ic.lsd.mercurius.orderMgr.spec.prov.OrderMgt.*(..))
		|| execution(public * br.unicamp.ic.lsd.mercurius.productSearchMgr.spec.prov.ProductSearchMgt.*(..));

	public pointcut debugScopeAfter() : execution(public * br.unicamp.ic.lsd.mercurius.basketmgr.spec.prov.BasketMgt.*(..))
		|| execution(public * br.unicamp.ic.lsd.mercurius.ordermgr.spec.prov.OrderMgt.*(..))
		|| execution(public * br.unicamp.ic.lsd.mercurius.productsearchmgr.spec.prov.ProductSearchMgt.*(..));

	before(Exception e) : handler(Exception+) && args(e) && errorScope() {
		loggingMgt.error("Error executing public method " + thisJoinPointStaticPart.getSignature().getName(), e);
	}

	before() : debugScopeBefore() {
		loggingMgt.debug("Initializing public method " + thisJoinPointStaticPart.getSignature().getName() + "execution");
	}

	before() : debugScopeAfter() {
		loggingMgt.debug("Finishing public method " + thisJoinPointStaticPart.getSignature().getName() + "execution");
	}

}
