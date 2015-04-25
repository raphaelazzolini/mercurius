package br.unicamp.ic.lsd.mercurius.loggingmgr.aspects;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.aspectj.lang.Signature;
import org.aspectj.lang.JoinPoint.StaticPart;

public abstract aspect AALogging {

	private static final Logger LOGGER = LogManager.getLogger(AALogging.class);
	private static final String INITIALIZING = "Initializing ";
	private static final String FINISHING = "Finishing ";
	private static final String COLON = ":";
	private static final String HYPHEN = " - ";

	abstract public pointcut errorScope();

	abstract public pointcut warnScopeBefore();

	abstract public pointcut warnScopeAfter();

	abstract public pointcut infoScopeBefore();

	abstract public pointcut infoScopeAfter();

	abstract public pointcut debugScopeBefore();

	abstract public pointcut debugScopeAfter();

	before(Exception e) : handler(Exception+) && args(e) && errorScope() {
		Signature signature = thisJoinPointStaticPart.getSignature();
		String logMessage = getLogMessage(signature, thisEnclosingJoinPointStaticPart, getMessage());
		LOGGER.error(logMessage, e);
	}

	before() : warnScopeBefore() {
		Signature signature = thisJoinPointStaticPart.getSignature();

		StringBuilder message = new StringBuilder(INITIALIZING);
		if (getMessage() != null) {
			message.append(getMessage());
		}

		String logMessage = getLogMessage(signature, thisEnclosingJoinPointStaticPart, message.toString());
		LOGGER.warn(logMessage);
	}

	before() : infoScopeBefore() {
		Signature signature = thisJoinPointStaticPart.getSignature();

		StringBuilder message = new StringBuilder(INITIALIZING);
		if (getMessage() != null) {
			message.append(getMessage());
		}

		String logMessage = getLogMessage(signature, thisEnclosingJoinPointStaticPart, message.toString());
		LOGGER.info(logMessage);
	}

	before() : debugScopeBefore() {
		Signature signature = thisJoinPointStaticPart.getSignature();

		StringBuilder message = new StringBuilder(INITIALIZING);
		if (getMessage() != null) {
			message.append(getMessage());
		}

		String logMessage = getLogMessage(signature, thisEnclosingJoinPointStaticPart, message.toString());
		LOGGER.debug(logMessage);
	}

	after() : warnScopeAfter() {
		Signature signature = thisJoinPointStaticPart.getSignature();

		StringBuilder message = new StringBuilder(FINISHING);
		if (getMessage() != null) {
			message.append(getMessage());
		}

		String logMessage = getLogMessage(signature, thisEnclosingJoinPointStaticPart, message.toString());
		LOGGER.warn(logMessage);
	}

	after() : infoScopeAfter() {
		Signature signature = thisJoinPointStaticPart.getSignature();

		StringBuilder message = new StringBuilder(FINISHING);
		if (getMessage() != null) {
			message.append(getMessage());
		}

		String logMessage = getLogMessage(signature, thisEnclosingJoinPointStaticPart, message.toString());
		LOGGER.info(logMessage);
	}

	after() : debugScopeAfter() {
		Signature signature = thisJoinPointStaticPart.getSignature();

		StringBuilder message = new StringBuilder(FINISHING);
		if (getMessage() != null) {
			message.append(getMessage());
		}

		String logMessage = getLogMessage(signature, thisEnclosingJoinPointStaticPart, message.toString());
		LOGGER.info(logMessage);
	}

	private String getLogMessage(Signature signature, StaticPart staticPart, String message) {
		StringBuilder logMessageBuilder = new StringBuilder();
		logMessageBuilder.append(signature.getDeclaringTypeName());
		logMessageBuilder.append(COLON);
		logMessageBuilder.append(staticPart.getSourceLocation().getLine());
		logMessageBuilder.append(HYPHEN);
		logMessageBuilder.append(message);
		return logMessageBuilder.toString();
	}

	abstract protected String getMessage();
}
