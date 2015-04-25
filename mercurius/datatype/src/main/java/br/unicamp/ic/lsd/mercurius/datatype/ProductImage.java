package br.unicamp.ic.lsd.mercurius.datatype;

import java.io.Serializable;

public interface ProductImage extends Serializable {

	/**
	 * Returns the id of the image.
	 * 
	 * @return
	 */
	Integer getId();

	/**
	 * Returns the type of the image.
	 * 
	 * @return
	 */
	String getImageType();

	/**
	 * Sets the type of the image.
	 * 
	 * @param type
	 */
	void setImageType(String imageType);

	/**
	 * Return the path of the image.
	 * 
	 * @return
	 */
	String getImagePath();

	/**
	 * Sets the path of the image.
	 * 
	 * @param path
	 */
	void setImagePath(String path);

	/**
	 * Returns the SKU that has the image.
	 * 
	 * @return
	 */
	ProductQuantity getProductQuantity();

	/**
	 * Sets the SKU that has the image.
	 * 
	 * @param attribute
	 */
	void setProductQuantity(ProductQuantity productQuantity);

}
