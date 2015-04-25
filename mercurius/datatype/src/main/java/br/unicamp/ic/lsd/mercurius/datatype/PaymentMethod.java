package br.unicamp.ic.lsd.mercurius.datatype;

import java.io.Serializable;

public interface PaymentMethod extends Serializable {

	/**
	 * Returns the payment method name.
	 * 
	 * @return
	 */
	String getName();

	/**
	 * Sets the payment method name.
	 * 
	 * @param name
	 */
	void setName(String name);

}
