package br.unicamp.ic.lsd.mercurius.productmgr.impl;

import java.util.List;

import br.unicamp.ic.lsd.mercurius.datatype.Product;
import br.unicamp.ic.lsd.mercurius.datatype.ProductAttribute;
import br.unicamp.ic.lsd.mercurius.datatype.ProductQuantity;
import br.unicamp.ic.lsd.mercurius.productmgr.spec.prov.ProductManager;
import br.unicamp.ic.lsd.mercurius.productmgr.spec.prov.ProductMgt;

class ProductFacade implements ProductMgt {

	private final ProductManager manager;
	private final ProductMgtImpl productMgt;

	protected ProductFacade(ProductManager manager) {
		super();
		this.manager = manager;
		this.productMgt = new ProductMgtImpl(this.manager);
	}

	@Override
	public Product getProduct(Integer productId) {
		return productMgt.getProductById(productId);
	}

	@Override
	public Product loadCategories(Product product) {
		return productMgt.loadCategories(product);
	}

	@Override
	public Product saveProduct(Product product) {
		return productMgt.saveProduct(product);
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
