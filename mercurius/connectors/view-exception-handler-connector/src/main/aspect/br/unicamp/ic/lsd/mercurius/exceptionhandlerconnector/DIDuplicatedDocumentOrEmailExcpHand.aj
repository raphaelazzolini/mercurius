package br.unicamp.ic.lsd.mercurius.exceptionhandlerconnector;

import br.unicamp.ic.lsd.mercurius.excpetionhandler.aspects.AADuplicatedDocumentOrEmailExcpHand;
import br.unicamp.ic.lsd.mercurius.view.aspects.XPIView;

public aspect DIDuplicatedDocumentOrEmailExcpHand extends AADuplicatedDocumentOrEmailExcpHand {

	public pointcut emailException() : XPIView.emailException();

	public pointcut documentException() : XPIView.documentException();

}
