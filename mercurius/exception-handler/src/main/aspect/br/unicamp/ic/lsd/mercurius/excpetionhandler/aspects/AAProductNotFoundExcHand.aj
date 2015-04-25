package br.unicamp.ic.lsd.mercurius.excpetionhandler.aspects;

import java.io.IOException;

import javax.faces.context.FacesContext;

import br.unicamp.ic.lsd.mercurius.excpetionhandler.exceptions.ProductNotFoundException;

public abstract aspect AAProductNotFoundExcHand {

	abstract public pointcut productNotFoundPage();

	abstract public pointcut productNotFoundOrder();

	void around() : productNotFoundPage() {
		try {
			proceed();
		} catch (ProductNotFoundException e) {
			try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("product_not_found.xhtml");
			} catch (IOException e2) {
			}
		}
	}

	String around() : productNotFoundOrder() {
		try {
			return proceed();
		} catch (ProductNotFoundException e) {
			return null;
		}
	}

}
