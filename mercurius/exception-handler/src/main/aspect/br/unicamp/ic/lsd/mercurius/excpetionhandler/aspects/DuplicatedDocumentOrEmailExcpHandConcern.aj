package br.unicamp.ic.lsd.mercurius.excpetionhandler.aspects;

public aspect DuplicatedDocumentOrEmailExcpHandConcern extends AADuplicatedDocumentOrEmailExcpHand {

	public pointcut emailException() : execution(public String br.unicamp.ic.lsd.mercurius.view.beans.CadastroManagedBean.cadastrar());

	public pointcut documentException() : execution(public String br.unicamp.ic.lsd.mercurius.view.beans.CadastroManagedBean.cadastrar());

}
