package br.unicamp.ic.lsd.mercurius.basketloggingconnector;

import br.unicamp.ic.lsd.mercurius.loggingmgr.aspects.AALogging;
import br.unicamp.ic.lsd.mercurius.basketmgr.aspects.XPIBasket;

public aspect LoggingAdapter extends AALogging {

	private String message;

	public pointcut errorScope() : XPIBasket.loggingErrorScope();

	public pointcut warnScopeBefore() : XPIBasket.loggingWarnScope();

	public pointcut warnScopeAfter() : XPIBasket.loggingWarnScope();

	public pointcut infoScopeBefore() : XPIBasket.loggingInfoScope();

	public pointcut infoScopeAfter() : XPIBasket.loggingInfoScope();

	public pointcut debugScopeBefore() : XPIBasket.loggingDebugScope();

	public pointcut debugScopeAfter() : XPIBasket.loggingDebugScope();

	before() : errorScope() {
		message = "executing public method " + thisJoinPointStaticPart.getSignature().getName();
	}

	before() : warnScopeBefore() {
		message = "executing public method " + thisJoinPointStaticPart.getSignature().getName();
	}

	before() : warnScopeAfter() {
		message = "executing public method " + thisJoinPointStaticPart.getSignature().getName();
	}

	before() : infoScopeBefore() {
		message = "executing public method " + thisJoinPointStaticPart.getSignature().getName();
	}

	before() : infoScopeAfter() {
		message = "executing public method " + thisJoinPointStaticPart.getSignature().getName();
	}

	before() : debugScopeBefore() {
		message = "executing public method " + thisJoinPointStaticPart.getSignature().getName();
	}

	before() : debugScopeAfter() {
		message = "executing public method " + thisJoinPointStaticPart.getSignature().getName();
	}

	protected String getMessage() {
		return message;
	}

}
