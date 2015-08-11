package br.unicamp.ic.lsd.mercurius.orderloggingconnector;

import br.unicamp.ic.lsd.mercurius.loggingmgr.spec.prov.LoggingMgt;
import br.unicamp.ic.lsd.mercurius.ordermgr.spec.req.OrderLoggingMgt;
import br.unicamp.ic.sed.cosmos.IManager;

class LoggingMgtAdapter implements OrderLoggingMgt {

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
