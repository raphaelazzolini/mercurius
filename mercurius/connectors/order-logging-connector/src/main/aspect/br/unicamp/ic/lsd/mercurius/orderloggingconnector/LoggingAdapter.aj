package br.unicamp.ic.lsd.mercurius.orderloggingconnector;

import br.unicamp.ic.lsd.mercurius.loggingmgr.aspects.AALogging;
import br.unicamp.ic.lsd.mercurius.ordermgr.aspects.XPIOrder;

public privileged aspect LoggingAdapter extends AALogging {

	private String message;

	public pointcut errorScope() : XPIOrder.loggingErrorScope();

	public pointcut warnScopeBefore() : XPIOrder.loggingWarnScope();

	public pointcut warnScopeAfter() : XPIOrder.loggingWarnScope();

	public pointcut infoScopeBefore() : XPIOrder.loggingInfoScope();

	public pointcut infoScopeAfter() : XPIOrder.loggingInfoScope();

	public pointcut debugScopeBefore() : XPIOrder.loggingDebugScope();

	public pointcut debugScopeAfter() : XPIOrder.loggingDebugScope();

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
