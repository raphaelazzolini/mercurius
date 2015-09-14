package br.unicamp.ic.lsd.mercurius.view.aspects;

public aspect XPIView {

	public pointcut emailException() : execution(public String br.unicamp.ic.lsd.mercurius.view.beans.CadastroManagedBean.cadastrar());

	public pointcut documentException() : execution(public String br.unicamp.ic.lsd.mercurius.view.beans.CadastroManagedBean.cadastrar());

	public pointcut productNotFoundPage() : execution(public void br.unicamp.ic.lsd.mercurius.view.beans.ProductManagedBean.productDetails());

	public pointcut productNotFoundOrder() : execution(public String br.unicamp.ic.lsd.mercurius.view.beans.OrderManagedBean.finalizarPedido());

	public pointcut outOfStock() : execution(public String br.unicamp.ic.lsd.mercurius.view.beans.OrderManagedBean.finalizarPedido());

}
