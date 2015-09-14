package br.unicamp.ic.lsd.mercurius.loggingmgr.spec.prov;

public interface LoggingMgt {

	void info(String message);

	void debug(String message);

	void warn(String message);

	void error(String message, Throwable t);

}
