package br.unicamp.ic.lsd.mercurius.datatype;

import java.io.Serializable;

public interface OrderStatus extends Serializable {

	/**
	 * Returns the status id.
	 * 
	 * @return
	 */
	Integer getId();

	/**
	 * Returns the status name.
	 * 
	 * @return
	 */
	String getName();

	/**
	 * Sets the status name.
	 * 
	 * @param name
	 */
	void setName(String name);

	/**
	 * Returns <code>true</code> if the order with this status can be canceled.
	 * 
	 * @return
	 */
	boolean isCancelable();

}
