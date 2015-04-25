package br.unicamp.ic.lsd.mercurius.datatype;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public interface ProductQuantity extends Serializable {

	/**
	 * Returns the SKU code.
	 * 
	 * @return
	 */
	String getSku();

	/**
	 * Sets the SKU code.
	 */
	void setSku(String sku);

	/**
	 * Returns the product of the SKU.
	 * 
	 * @return
	 */
	Product getProduct();

	/**
	 * Sets the product of the SKU.
	 * 
	 * @param product
	 */
	void setProduct(Product product);

	/**
	 * Returns the quantity of products with this SKU that are in stock.
	 * 
	 * @return
	 */
	Integer getQuantity();

	/**
	 * Sets the quantity of products with this SKU that are in stock.
	 */
	void setQuantity(Integer quantity);

	/**
	 * Returns the attributes of this SKU.
	 * 
	 * @return
	 */
	List<ProductAttribute> getProductsAttributes();

	/**
	 * Sets the attributes of the SKU.
	 * 
	 * @param productsAttributes
	 */
	void setProductsAttributes(List<ProductAttribute> productsAttributes);

	/**
	 * Returns the price of one item of the product. The price is the
	 * {@link Product#getPrice()} value plus the
	 * {@link ProductAttribute#getValue()} of each {@link ProductAttribute} in
	 * the attributes list.
	 * 
	 * @return
	 */
	BigDecimal getItemPrice();

	/**
	 * Returns all the images of the product for this SKU.
	 * 
	 * @return
	 */
	List<ProductImage> getProductImages();

	/**
	 * Returns all the small size images of the product for this SKU.
	 * 
	 * @return
	 */
	List<ProductImage> getProductSmallImages();

	/**
	 * Returns all the medium size images of the product for this SKU.
	 * 
	 * @return
	 */
	List<ProductImage> getProductMediumImages();

}
