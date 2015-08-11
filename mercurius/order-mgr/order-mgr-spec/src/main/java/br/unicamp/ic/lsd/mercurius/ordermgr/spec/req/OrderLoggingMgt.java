package br.unicamp.ic.lsd.mercurius.ordermgr.spec.req;

public interface OrderLoggingMgt {

	void info(String message);

	void debug(String message);

	void warn(String message);

	void error(String message, Throwable t);

}
