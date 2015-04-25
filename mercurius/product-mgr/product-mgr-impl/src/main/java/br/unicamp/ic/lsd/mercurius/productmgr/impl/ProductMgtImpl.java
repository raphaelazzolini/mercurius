package br.unicamp.ic.lsd.mercurius.productmgr.impl;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import br.unicamp.ic.lsd.mercurius.datatype.Product;
import br.unicamp.ic.lsd.mercurius.datatype.ProductAttribute;
import br.unicamp.ic.lsd.mercurius.datatype.ProductQuantity;
import br.unicamp.ic.lsd.mercurius.productmgr.spec.prov.ProductManager;

class ProductMgtImpl {

	private final ProductManager manager;

	ProductMgtImpl(ProductManager manager) {
		super();
		this.manager = manager;
	}

	public Product getProductById(Integer productId) {
		Product product = manager.getProductDAO().findById(productId);
		return manager.getProductDAO().loadProductImages(product);
	}

	public Product loadCategories(Product product) {
		return manager.getProductDAO().loadCategories(product);
	}

	public Product saveProduct(Product product) {
		return manager.getProductDAO().merge(product);
	}

	public ProductQuantity getProductQuantity(Product product, List<ProductAttribute> attributes) {
		ProductQuantity productQuantity = manager.getProductDAO().getProductQuantity(product, attributes);
		productQuantity = manager.getProductQuantityDAO().loadAttributes(productQuantity);
		if (CollectionUtils.isNotEmpty(productQuantity.getProductImages())) {
			productQuantity.getProduct().setMainImage(productQuantity.getProductImages().get(0).getImagePath());
		} else {
			productQuantity.getProduct().setMainImage(StringUtils.EMPTY);
		}
		return productQuantity;
	}

	public ProductQuantity getProductQuantity(String sku) {
		ProductQuantity productQuantity = manager.getProductQuantityDAO().findById(sku);
		productQuantity = manager.getProductQuantityDAO().loadAttributes(productQuantity);
		if (CollectionUtils.isNotEmpty(productQuantity.getProductImages())) {
			productQuantity.getProduct().setMainImage(productQuantity.getProductImages().get(0).getImagePath());
		} else {
			productQuantity.getProduct().setMainImage(StringUtils.EMPTY);
		}
		return productQuantity;
	}

}
