package br.unicamp.ic.lsd.mercurius.datatype;

import java.io.Serializable;

public interface Configuration extends Serializable {

	/**
	 * Returns the configuration key.
	 * 
	 * @return
	 */
	String getKey();

	/**
	 * Sets the configuration key.
	 * 
	 * @param key
	 */
	void setKey(String key);

	/**
	 * Returns the configuration value.
	 * 
	 * @return
	 */
	String getValue();

	/**
	 * Sets the configuration value.
	 * 
	 * @param value
	 */
	void setValue(String value);

}
