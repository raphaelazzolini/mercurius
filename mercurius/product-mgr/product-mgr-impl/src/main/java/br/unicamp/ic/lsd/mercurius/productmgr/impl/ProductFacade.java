package br.unicamp.ic.lsd.mercurius.productmgr.impl;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import br.unicamp.ic.lsd.mercurius.datatype.Configuration;
import br.unicamp.ic.lsd.mercurius.datatype.Manufacturer;
import br.unicamp.ic.lsd.mercurius.datatype.Product;
import br.unicamp.ic.lsd.mercurius.datatype.ProductAttribute;
import br.unicamp.ic.lsd.mercurius.datatype.ProductImage;
import br.unicamp.ic.lsd.mercurius.datatype.ProductQuantity;
import br.unicamp.ic.lsd.mercurius.productmgr.spec.prov.ProductManager;
import br.unicamp.ic.lsd.mercurius.productmgr.spec.prov.ProductMgt;
import br.unicamp.ic.lsd.mercurius.productmgr.spec.req.ProductPromotionMgt;

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
		ProductPromotionMgt promotionMgt = (ProductPromotionMgt) manager.getRequiredInterface("ProductPromotionMgt");
		Product product = productMgt.getProductById(productId);
		return promotionMgt.getProductWithPromotion(product);
	}

	@Override
	public Product loadCategories(Product product) {
		ProductPromotionMgt promotionMgt = (ProductPromotionMgt) manager.getRequiredInterface("ProductPromotionMgt");
		product = productMgt.loadCategories(product);
		return promotionMgt.getProductWithPromotion(product);
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

	@Override
	public Product newProductInstance() {
		return manager.getProductDAO().newInstance();
	}

	@Override
	public void remove(Product product) {
		manager.getProductDAO().removeById(product.getId());
	}

	@Override
	public void deleteProductImage(ProductImage image) {
		Configuration imagesFolderConfiguration = manager.getConfigurationDAO().findById("imagesDirectory");
		File imagesFolder = new File(imagesFolderConfiguration.getValue());
		manager.getProductImageDAO().removeById(image.getId());
		productMgt.deleteImages(image, imagesFolder);
	}

	@Override
	public List<Manufacturer> getManufacturerers() {
		return manager.getManufactoryDAO().getAll();
	}

	@Override
	public ProductQuantity newProductQuantityInstance() {
		return manager.getProductQuantityDAO().newInstance();
	}

	@Override
	public void deleteProductQuantity(ProductQuantity productQuantity) {
		manager.getProductQuantityDAO().removeById(productQuantity.getSku());
	}

	@Override
	public ProductQuantity saveProductQuantity(ProductQuantity productQuantity) {
		return manager.getProductQuantityDAO().merge(productQuantity);
	}

	@Override
	public ProductImage saveImage(String imageName, byte[] imageBytes) throws IOException {
		Configuration imagesFolderConfiguration = manager.getConfigurationDAO().findById("imagesDirectory");
		File imagesFolder = new File(imagesFolderConfiguration.getValue());
		productMgt.saveProductImage(imagesFolder, imageName, imageBytes);

		ProductImage image = manager.getProductImageDAO().newInstance();
		image.setImagePath(imageName);
		return image;
	}

	@Override
	public ProductImage saveProductImage(ProductImage productImage) {
		return manager.getProductImageDAO().merge(productImage);
	}

	@Override
	public List<Product> getProductList(int offset, int maxResults) {
		return manager.getProductDAO().getAll(offset, maxResults);
	}

	@Override
	public Integer getTotalProducts() {
		return manager.getProductDAO().getProductCount().intValue();
	}

	@Override
	public Manufacturer getManufacturerById(Integer id) {
		return manager.getManufactoryDAO().findById(id);
	}

	@Override
	public Collection<Product> getRandomProducts(Integer quantity) {
		if (quantity == null || quantity < 1) {
			return Collections.emptyList();
		}
		Collection<Product> products = manager.getProductDAO().getRandomProducts(quantity);
		return products;
	}

}
