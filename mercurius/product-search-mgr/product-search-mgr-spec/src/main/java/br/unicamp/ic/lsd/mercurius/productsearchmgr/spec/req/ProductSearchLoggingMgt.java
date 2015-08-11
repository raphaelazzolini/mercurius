package br.unicamp.ic.lsd.mercurius.productsearchmgr.spec.req;

public interface ProductSearchLoggingMgt {

	void info(String message);

	void debug(String message);

	void warn(String message);

	void error(String message, Throwable t);

}
