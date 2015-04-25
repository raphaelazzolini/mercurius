package br.unicamp.ic.lsd.mercurius.productsearchloggingconnector;

import br.unicamp.ic.lsd.mercurius.loggingmgr.aspects.AALogging;
import br.unicamp.ic.lsd.mercurius.productsearchmgr.aspects.XPIProductSearch;

public aspect LoggingAdapter extends AALogging {

	private String message;

	public pointcut errorScope() : XPIProductSearch.loggingErrorScope();

	public pointcut warnScopeBefore() : XPIProductSearch.loggingWarnScope();

	public pointcut warnScopeAfter() : XPIProductSearch.loggingWarnScope();

	public pointcut infoScopeBefore() : XPIProductSearch.loggingInfoScope();

	public pointcut infoScopeAfter() : XPIProductSearch.loggingInfoScope();

	public pointcut debugScopeBefore() : XPIProductSearch.loggingDebugScope();

	public pointcut debugScopeAfter() : XPIProductSearch.loggingDebugScope();

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
