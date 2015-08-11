package br.unicamp.ic.lsd.mercurius.loggingmgr.impl;

import org.apache.log4j.Logger;

import br.unicamp.ic.lsd.mercurius.loggingmgr.spec.prov.LoggingMgt;
import br.unicamp.ic.sed.cosmos.IManager;

class LoggingFacade implements LoggingMgt {

	private final static Logger LOGGER = Logger.getLogger(LoggingFacade.class);

	private final IManager manager;

	LoggingFacade(IManager manager) {
		super();
		this.manager = manager;
	}

	@Override
	public void info(String message) {
		LOGGER.info(message);
	}

	@Override
	public void debug(String message) {
		LOGGER.debug(message);
	}

	@Override
	public void warn(String message) {
		LOGGER.warn(message);
	}

	@Override
	public void error(String message, Throwable t) {
		LOGGER.error(message, t);
	}

}
