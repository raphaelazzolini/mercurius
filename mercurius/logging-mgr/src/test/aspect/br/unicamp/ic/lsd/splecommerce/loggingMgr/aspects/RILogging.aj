package br.unicamp.ic.lsd.mercurius.loggingmgr.aspects;

public aspect RILogging extends AALogging {

	private String message;

	public pointcut errorScope() : within(br.unicamp.ic.lsd.mercurius.loggingMgr.test..*);

	public pointcut warnScopeBefore() : execution(void br.unicamp.ic.lsd.mercurius.loggingMgr.test.LoggingTest.logginTest3());

	public pointcut infoScopeBefore() : execution(void br.unicamp.ic.lsd.mercurius.loggingMgr.test.LoggingTest.logginTest4());

	public pointcut warnScopeAfter() : warnScopeBefore();

	public pointcut infoScopeAfter() : infoScopeBefore();

	public pointcut debugScopeBefore() : infoScopeBefore();

	public pointcut debugScopeAfter() : infoScopeBefore();

	before() : execution(public String br.unicamp.ic.lsd.mercurius.loggingMgr.test.LoggingTest.logginTest1()) {
		message = "Error executing public method " + thisJoinPointStaticPart.getSignature().getName();
	}

	before() : execution(private String br.unicamp.ic.lsd.mercurius.loggingMgr.test.LoggingTest.logginTest2()) {
		message = "Error executing private method " + thisJoinPointStaticPart.getSignature().getName();
	}

	before() : execution(public void br.unicamp.ic.lsd.mercurius.loggingMgr.test.LoggingTest.logginTest3()) {
		message = "public method " + thisJoinPointStaticPart.getSignature().getName();
	}

	before() : execution(private void br.unicamp.ic.lsd.mercurius.loggingMgr.test.LoggingTest.logginTest4()) {
		message = null;
	}

	protected String getMessage() {
		return message;
	}

}
