package br.unicamp.ic.lsd.mercurius.productmgr.spec.prov;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import br.unicamp.ic.lsd.mercurius.datatype.Category;
import br.unicamp.ic.lsd.mercurius.datatype.Manufacturer;
import br.unicamp.ic.lsd.mercurius.datatype.Product;
import br.unicamp.ic.lsd.mercurius.datatype.ProductAttribute;
import br.unicamp.ic.lsd.mercurius.datatype.ProductImage;
import br.unicamp.ic.lsd.mercurius.datatype.ProductQuantity;

public interface ProductMgt {

	/**
	 * Returns the {@link Product} that has the informed id or <code>null</code>
	 * if there isn't any {@link Product} with this id.
	 * 
	 * @param productId
	 *            the id of the product
	 * @return
	 */
	Product getProduct(Integer productId);

	/**
	 * Load the {@link List} of {@link Category} that are part of the informed
	 * {@link Product}. The class that implements the {@link Product} interface
	 * must have a {@link List} of {@link Category} of the fetch type LAZY.
	 * 
	 * @param product
	 *            the {@link Product} that will have the {@link List} of
	 *            {@link Category} loaded
	 * @return the {@link Product} with the load categories
	 */
	Product loadCategories(Product product);

	/**
	 * Saves the informed {@link Product}.
	 * 
	 * @param product
	 *            the {@link Product} to be saved
	 * @return the saved {@link Product}
	 */
	Product saveProduct(Product product);

	/**
	 * Returns the {@link ProductQuantity} from the informed {@link Product}
	 * that contains the informed attributes
	 * 
	 * @param product
	 * @param attributes
	 * @return
	 */
	ProductQuantity getProductQuantity(Product product, List<ProductAttribute> attributes);

	/**
	 * Returns the {@link ProductQuantity} that has the informed SKU
	 * 
	 * @param sku
	 * @return
	 */
	ProductQuantity getProductQuantity(String sku);

	/**
	 * Returns a new {@link Product} instance
	 * 
	 * @return
	 */
	Product newProductInstance();

	/**
	 * Removes a product from database
	 * 
	 * @param product
	 */
	void remove(Product product);

	/**
	 * Removes a product image
	 * 
	 * @param product
	 */
	void deleteProductImage(ProductImage image);

	/**
	 * Returns a list with all manufacturers from database
	 * 
	 * @return
	 */
	List<Manufacturer> getManufacturerers();

	/**
	 * Returns a new {@link ProductQuantity} instance
	 * 
	 * @return
	 */
	ProductQuantity newProductQuantityInstance();

	/**
	 * Saves {@link ProductQuantity} to database
	 * 
	 * @param productQuantity
	 * @return
	 */
	ProductQuantity saveProductQuantity(ProductQuantity productQuantity);

	/**
	 * Removes a {@link ProductQuantity} from database
	 * 
	 * @return
	 */
	void deleteProductQuantity(ProductQuantity productQuantity);

	/**
	 * Saves the image file with the correct sizes
	 * 
	 * @param imageName
	 * @param imageBytes
	 * @return
	 * @throws IOException
	 */
	ProductImage saveImage(String imageName, byte[] imageBytes) throws IOException;

	/**
	 * Saves the product image in the database
	 * 
	 * @param productImage
	 * @return
	 */
	ProductImage saveProductImage(ProductImage productImage);

	/**
	 * Returns a list of {@link Product} limited by the offset and max number of
	 * results
	 * 
	 * @param offset
	 * @param maxResults
	 * @return
	 */
	List<Product> getProductList(int offset, int maxResults);

	/**
	 * Returns the total of products in the database
	 * 
	 * @return
	 */
	Integer getTotalProducts();

	/**
	 * Returns a {@link Manufacturer} by its id
	 * 
	 * @param id
	 * @return
	 */
	Manufacturer getManufacturerById(Integer id);

	/**
	 * Return a collection with random products
	 * 
	 * @param quantity
	 * @return
	 */
	Collection<Product> getRandomProducts(HttpServletRequest request, Integer quantity);
	
	Collection<Product> getRandomProducts(Integer quantity);

}
