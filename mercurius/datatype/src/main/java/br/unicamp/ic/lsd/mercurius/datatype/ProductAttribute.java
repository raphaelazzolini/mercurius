package br.unicamp.ic.lsd.mercurius.datatype;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public interface ProductAttribute extends Serializable {

	/**
	 * Returns the if of the option value;
	 * 
	 * @return
	 */
	Integer getId();

	/**
	 * Returns the name of the option value;
	 * 
	 * @return
	 */
	String getName();

	/**
	 * Sets the name of the option value;
	 * 
	 * @param name
	 */
	void setName(String name);

	/**
	 * Returns the option that the value is part of.
	 * 
	 * @return
	 */
	ProductOption getOption();

	/**
	 * Sets the option of the value.
	 * 
	 * @param option
	 */
	void setOption(ProductOption option);

	/**
	 * Returns the value that this option value adds or subtracts from the
	 * product.
	 * 
	 * @return
	 */
	BigDecimal getValue();

	/**
	 * Sets the value that this option value adds or subtracts from the product.
	 * 
	 * @param value
	 */
	void setValue(BigDecimal value);

	/**
	 * Returns the SKUs that has this attribute
	 * 
	 * @return
	 */
	List<ProductQuantity> getProductsQuantities();

}
