package br.unicamp.ic.lsd.mercurius.datatype;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface Product extends Serializable {

	/**
	 * Returns the id of the product.
	 * 
	 * @return
	 */
	Integer getId();

	/**
	 * Returns the name of the product.
	 * 
	 * @return
	 */
	String getName();

	/**
	 * Sets the name of the product.
	 * 
	 * @param name
	 *            a String with the name of the product
	 */
	void setName(String name);

	/**
	 * Sets the date that the product was registered.
	 * 
	 * @return
	 */
	void setDateAdded(Date dateAdded);

	/**
	 * Return the date that the product was registered.
	 * 
	 * @return
	 */
	Date getDateAdded();

	/**
	 * Returns the that that the product will be available
	 * 
	 * @return
	 */
	Date getDateAvailable();

	/**
	 * Sets the date that the product will be available.
	 * 
	 * @param dateAvailable
	 */
	void setDateAvailable(Date dateAvailable);

	/**
	 * Returns the date that the product will be no more available.
	 * 
	 * @return
	 */
	Date getExpiryDate();

	/**
	 * Sets the date that the product will be no more available.
	 * 
	 * @param expiryDate
	 */
	void setExpiryDate(Date expiryDate);

	/**
	 * Returns the price of the product.
	 * 
	 * @return
	 */
	BigDecimal getPrice();

	/**
	 * Sets the price of the product.
	 * 
	 * @param price
	 */
	void setPrice(BigDecimal price);

	/**
	 * Returns the weight of the product.
	 * 
	 * @return
	 */
	BigDecimal getWeight();

	/**
	 * Sets the weight of the product.
	 * 
	 * @param weight
	 */
	void setWeight(BigDecimal weight);

	/**
	 * Returns the width of the product.
	 * 
	 * @return
	 */
	BigDecimal getWidth();

	/**
	 * Sets the width of the product.
	 * 
	 * @param width
	 */
	void setWidth(BigDecimal width);

	/**
	 * Returns the height of the product.
	 * 
	 * @return
	 */
	BigDecimal getHeight();

	/**
	 * Sets the height of the product.
	 * 
	 * @param height
	 */
	void setHeight(BigDecimal height);

	/**
	 * Return a String with the details of the product
	 * 
	 * @return
	 */
	String getDetails();

	/**
	 * If the products contains technical details, it will be returned,
	 * otherwise returns <b>null</b>.
	 * 
	 * @return
	 */
	String getTechnicalDetails();

	/**
	 * If the product is a free gift <b>true</b> is returned, otherwise
	 * <b>false</b> is returned;
	 * 
	 * @return
	 */
	boolean isFreeGift();

	/**
	 * Sets the details of the product.
	 * 
	 * @param details
	 *            the String with the details to be set
	 */
	void setDetails(String details);

	/**
	 * Sets the technical details of the product.
	 * 
	 * @param technicalDetails
	 *            the String with the technical details to be set
	 */
	void setTechnicalDetails(String technicalDetails);

	/**
	 * Set if the product is a free gift.
	 * 
	 * @param freeGift
	 *            <b>true</b> if it is a free gift or <b>false</b> if it is not
	 *            a free gift
	 */
	void setFreeGift(boolean freeGift);

	/**
	 * Returns the categories that the product is part of.
	 * 
	 * @return
	 */
	List<Category> getCategories();

	/**
	 * Sets the categories that the product is part of.
	 * 
	 * @return
	 */
	void setCategories(List<Category> categories);

	/**
	 * Returns the SKUs of the product.
	 * 
	 * @return
	 */
	List<ProductQuantity> getQuantities();

	/**
	 * Sets the SKUs of the product.
	 * 
	 * @param quantities
	 */
	void setQuantities(List<ProductQuantity> quantities);

	/**
	 * Returns the manufacturer of the product.
	 * 
	 * @return
	 */
	Manufacturer getManufacturer();

	/**
	 * Sets the manufacturer of the product.
	 * 
	 * @return
	 */
	void setManufacturer(Manufacturer manufacturer);

	/**
	 * Returns the main image source
	 * 
	 * @return
	 */
	String getMainImage();

	/**
	 * Sets the main image source
	 * 
	 * @param image
	 */
	void setMainImage(String image);

	/**
	 * Returns the product special price
	 * 
	 * @return
	 */
	BigDecimal getSpecialPrice();

	/**
	 * Sets the product special price
	 * 
	 * @param specialPrice
	 */
	void setSpecialPrice(BigDecimal specialPrice);

	String getPriceFormatted();

	String getSpecialPriceFormatted();

}
