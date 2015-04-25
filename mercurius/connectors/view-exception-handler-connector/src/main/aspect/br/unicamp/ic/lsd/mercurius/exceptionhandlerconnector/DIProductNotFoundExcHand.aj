package br.unicamp.ic.lsd.mercurius.exceptionhandlerconnector;

import br.unicamp.ic.lsd.mercurius.excpetionhandler.aspects.AAProductNotFoundExcHand;
import br.unicamp.ic.lsd.mercurius.view.aspects.XPIView;

public aspect DIProductNotFoundExcHand extends AAProductNotFoundExcHand {

	public pointcut productNotFoundPage() : XPIView.productNotFoundPage();

	public pointcut productNotFoundOrder() : XPIView.productNotFoundOrder();

}
