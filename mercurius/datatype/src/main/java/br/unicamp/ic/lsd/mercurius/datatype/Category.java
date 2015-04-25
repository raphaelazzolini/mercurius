package br.unicamp.ic.lsd.mercurius.datatype;

import java.io.Serializable;
import java.util.List;

public interface Category extends Serializable {

	/**
	 * Returns the id of the category
	 * 
	 * @return
	 */
	Integer getId();

	/**
	 * Returns the name of the category.
	 * 
	 * @return
	 */
	String getName();

	/**
	 * Sets the name of the Category.
	 * 
	 * @param name
	 */
	void setName(String name);

	/**
	 * Returns the parent category of the category if exists, otherwise returns
	 * <b>null</b>.
	 * 
	 * @return
	 */
	Category getParent();

	/**
	 * Sets the parent of the category.
	 * 
	 * @param parent
	 */
	void setParent(Category parent);

	/**
	 * Return a {@link java.util.List} with the children categories of the
	 * category.
	 * 
	 * @return
	 */
	List<Category> getChildren();

	/**
	 * Returns the description of the category.
	 * 
	 * @return
	 */
	String getDescription();

	/**
	 * Sets the description of the category.
	 * 
	 * @param description
	 */
	void setDescription(String description);

	/**
	 * Returns the products that have this category.
	 * 
	 * @return
	 */
	List<Product> getProducts();

}
