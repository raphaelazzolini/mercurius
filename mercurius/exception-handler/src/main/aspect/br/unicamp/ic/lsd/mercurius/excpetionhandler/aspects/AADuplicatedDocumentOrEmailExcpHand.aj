package br.unicamp.ic.lsd.mercurius.excpetionhandler.aspects;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import br.unicamp.ic.lsd.mercurius.excpetionhandler.exceptions.DuplicatedDocumentException;
import br.unicamp.ic.lsd.mercurius.excpetionhandler.exceptions.DuplicatedEmailExcpetion;

public abstract aspect AADuplicatedDocumentOrEmailExcpHand {

	abstract public pointcut emailException();

	abstract public pointcut documentException();

	String around() : emailException() {
		try {
			return proceed();
		} catch (DuplicatedEmailExcpetion e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "E-mail já cadastrado", "Esse e-mail já está cadastrado em nosso sistema"));
			return null;
		}
	}

	String around() : documentException() {
		try {
			return proceed();
		} catch (DuplicatedDocumentException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "CPF já cadastrado", "O CPF informado já está cadastrado em nosso sistema"));
			return null;
		}
	}

}
