package br.unicamp.ic.lsd.mercurius.excpetionhandler.aspects;

import br.unicamp.ic.lsd.mercurius.excpetionhandler.exceptions.OutOfStockException;

public abstract aspect AAOutOfStockExcHand {

	abstract public pointcut outOfStock();

	String around() : outOfStock() {
		try {
			return proceed();
		} catch (OutOfStockException e) {
			return null;
		}
	}

}
