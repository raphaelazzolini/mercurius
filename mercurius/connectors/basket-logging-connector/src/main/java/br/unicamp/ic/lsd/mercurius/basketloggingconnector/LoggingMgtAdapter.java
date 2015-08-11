package br.unicamp.ic.lsd.mercurius.basketloggingconnector;

import br.unicamp.ic.lsd.mercurius.basketmgr.spec.req.BasketLoggingMgt;
import br.unicamp.ic.lsd.mercurius.loggingmgr.spec.prov.LoggingMgt;
import br.unicamp.ic.sed.cosmos.IManager;

class LoggingMgtAdapter implements BasketLoggingMgt {

	private final IManager manager;

	private LoggingMgt loggingMgt;

	LoggingMgtAdapter(IManager manager) {
		super();
		this.manager = manager;
		loggingMgt = (LoggingMgt) this.manager.getRequiredInterface("LoggingMgt");
	}

	@Override
	public void info(String message) {
		loggingMgt.info(message);
	}

	@Override
	public void debug(String message) {
		loggingMgt.debug(message);
	}

	@Override
	public void warn(String message) {
		loggingMgt.warn(message);
	}

	@Override
	public void error(String message, Throwable t) {
		loggingMgt.error(message, t);
	}

}
