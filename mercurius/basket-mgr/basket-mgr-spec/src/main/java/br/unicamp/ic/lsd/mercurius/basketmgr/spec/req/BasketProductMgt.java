package br.unicamp.ic.lsd.mercurius.basketmgr.spec.req;

import java.util.List;

import br.unicamp.ic.lsd.mercurius.datatype.Product;
import br.unicamp.ic.lsd.mercurius.datatype.ProductAttribute;
import br.unicamp.ic.lsd.mercurius.datatype.ProductQuantity;

public interface BasketProductMgt {

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
