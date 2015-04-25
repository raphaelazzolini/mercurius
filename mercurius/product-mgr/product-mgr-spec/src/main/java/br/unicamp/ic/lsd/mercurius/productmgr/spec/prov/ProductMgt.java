package br.unicamp.ic.lsd.mercurius.productmgr.spec.prov;

import java.util.List;

import br.unicamp.ic.lsd.mercurius.datatype.Category;
import br.unicamp.ic.lsd.mercurius.datatype.Product;
import br.unicamp.ic.lsd.mercurius.datatype.ProductAttribute;
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

}
