package br.unicamp.ic.lsd.mercurius.datatype;

import java.io.Serializable;
import java.math.BigDecimal;

public interface ShippingService extends Serializable {

	/**
	 * Returns the shipping name.
	 * 
	 * @return
	 */
	String getName();

	/**
	 * Sets the shipping name.
	 * 
	 * @param name
	 */
	void setName(String name);

	/**
	 * Returns the shipping service code.
	 * 
	 * @return
	 */
	String getServiceCode();

	/**
	 * Sets the shipping service code.
	 * 
	 * @param code
	 */
	void setServiceCode(String code);

	/**
	 * Returns the shipping description;
	 * 
	 * @return
	 */
	String getDescription();

	/**
	 * Sets the shipping description.
	 * 
	 * @param description
	 */
	void setDescription(String description);

	/**
	 * Returns the shipping cost.
	 * 
	 * @return
	 */
	BigDecimal getValue();

	/**
	 * Sets the shipping cost.
	 * 
	 * @param value
	 */
	void setValue(BigDecimal value);

}
