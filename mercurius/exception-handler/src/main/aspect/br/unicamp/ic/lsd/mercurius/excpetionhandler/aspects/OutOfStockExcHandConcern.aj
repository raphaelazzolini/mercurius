package br.unicamp.ic.lsd.mercurius.excpetionhandler.aspects;

public aspect OutOfStockExcHandConcern extends AAOutOfStockExcHand {

	public pointcut outOfStock() : execution(public String br.unicamp.ic.lsd.mercurius.view.beans.OrderManagedBean.finalizarPedido());

}
