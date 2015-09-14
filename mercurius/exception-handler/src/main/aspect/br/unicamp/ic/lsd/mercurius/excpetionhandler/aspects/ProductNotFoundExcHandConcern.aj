package br.unicamp.ic.lsd.mercurius.excpetionhandler.aspects;

public aspect ProductNotFoundExcHandConcern extends AAProductNotFoundExcHand {

	public pointcut productNotFoundPage() : execution(public void br.unicamp.ic.lsd.mercurius.view.beans.ProductManagedBean.productDetails());

	public pointcut productNotFoundOrder() : execution(public String br.unicamp.ic.lsd.mercurius.view.beans.OrderManagedBean.finalizarPedido());

}
