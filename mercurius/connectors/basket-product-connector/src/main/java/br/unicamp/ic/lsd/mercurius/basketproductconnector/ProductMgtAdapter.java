package br.unicamp.ic.lsd.mercurius.basketproductconnector;

import java.util.List;

import br.unicamp.ic.lsd.mercurius.basketmgr.spec.req.BasketProductMgt;
import br.unicamp.ic.lsd.mercurius.datatype.Product;
import br.unicamp.ic.lsd.mercurius.datatype.ProductAttribute;
import br.unicamp.ic.lsd.mercurius.datatype.ProductQuantity;
import br.unicamp.ic.lsd.mercurius.productmgr.spec.prov.ProductMgt;

class ProductMgtAdapter implements BasketProductMgt {

	private static final String PRODUCT_MGT = "ProductMgt";

	private final ProductBasketConnectorManager manager;
	private final ProductMgt productMgt;

	ProductMgtAdapter(ProductBasketConnectorManager manager) {
		super();
		this.manager = manager;
		productMgt = (ProductMgt) this.manager.getRequiredInterface(PRODUCT_MGT);
	}

	@Override
	public ProductQuantity getProductQuantity(Product product, List<ProductAttribute> attributes) {
		return productMgt.getProductQuantity(product, attributes);
	}

	@Override
	public ProductQuantity getProductQuantity(String sku) {
		return productMgt.getProductQuantity(sku);
	}

}
