package br.unicamp.ic.lsd.mercurius.basketmgr.spec.req;

public interface BasketLoggingMgt {

	void info(String message);

	void debug(String message);

	void warn(String message);

	void error(String message, Throwable t);

}
