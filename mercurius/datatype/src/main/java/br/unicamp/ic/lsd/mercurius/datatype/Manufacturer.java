package br.unicamp.ic.lsd.mercurius.datatype;

import java.io.Serializable;
import java.util.List;

public interface Manufacturer extends Serializable {

	/**
	 * Returns the id of the manufacturer.
	 * 
	 * @return
	 */
	Integer getId();

	/**
	 * Returns the name of the manufacturer.
	 * 
	 * @return
	 */
	String getName();

	/**
	 * Sets the name of the manufacturer.
	 * 
	 * @param name
	 */
	void setName(String name);

	/**
	 * Returns the URL of the manufacturer.
	 * 
	 * @return
	 */
	String getUrl();

	/**
	 * Sets the URL of the manufacturer.
	 * 
	 * @param url
	 */
	void setUrl(String url);

	/**
	 * Returns the products of the manufacturer.
	 * 
	 * @return
	 */
	List<Product> getProducts();

}
