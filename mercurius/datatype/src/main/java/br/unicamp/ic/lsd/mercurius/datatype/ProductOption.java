package br.unicamp.ic.lsd.mercurius.datatype;

import java.io.Serializable;
import java.util.List;

public interface ProductOption extends Serializable {

	/**
	 * Returns the id of the option.
	 * 
	 * @return
	 */
	Integer getId();

	/**
	 * Returns the name of the option.
	 * 
	 * @return
	 */
	String getName();

	/**
	 * Sets the name of the option.
	 * 
	 * @param name
	 *            a String with the name of the product option
	 */
	void setName(String name);

	/**
	 * Returns the available attributes of this option.
	 * 
	 * @return
	 */
	List<ProductAttribute> getAttributes();

}
