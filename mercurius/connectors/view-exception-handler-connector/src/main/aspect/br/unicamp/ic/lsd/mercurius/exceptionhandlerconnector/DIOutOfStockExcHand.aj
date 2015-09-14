package br.unicamp.ic.lsd.mercurius.exceptionhandlerconnector;

import br.unicamp.ic.lsd.mercurius.excpetionhandler.aspects.AAOutOfStockExcHand;
import br.unicamp.ic.lsd.mercurius.view.aspects.XPIView;

public aspect DIOutOfStockExcHand extends AAOutOfStockExcHand {

	public pointcut outOfStock() : XPIView.outOfStock();

}
