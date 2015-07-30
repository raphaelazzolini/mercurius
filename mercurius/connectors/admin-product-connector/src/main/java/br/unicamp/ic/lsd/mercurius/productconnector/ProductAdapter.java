package br.unicamp.ic.lsd.mercurius.productconnector;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import br.unicamp.ic.lsd.mercurius.admin.spec.req.AdminProductMgt;
import br.unicamp.ic.lsd.mercurius.datatype.Category;
import br.unicamp.ic.lsd.mercurius.datatype.Manufacturer;
import br.unicamp.ic.lsd.mercurius.datatype.Product;
import br.unicamp.ic.lsd.mercurius.datatype.ProductImage;
import br.unicamp.ic.lsd.mercurius.datatype.ProductQuantity;
import br.unicamp.ic.lsd.mercurius.productmgr.spec.prov.ProductCategoryMgt;
import br.unicamp.ic.lsd.mercurius.productmgr.spec.prov.ProductMgt;
import br.unicamp.ic.sed.cosmos.IManager;

class ProductAdapter implements AdminProductMgt {

	private final IManager manager;

	private ProductMgt productMgt;
	private ProductCategoryMgt categoryMgt;

	ProductAdapter(IManager manager) {
		super();
		this.manager = manager;
		productMgt = (ProductMgt) this.manager.getRequiredInterface("ProductMgt");
		categoryMgt = (ProductCategoryMgt) this.manager.getRequiredInterface("CategoryMgt");
	}

	@Override
	public Product createNewProductInstance() {
		return productMgt.newProductInstance();
	}

	@Override
	public List<Category> getAllCategories() {
		return categoryMgt.getAllCategories();
	}

	@Override
	public Product loadCategories(Product product) {
		return productMgt.loadCategories(product);
	}

	@Override
	public void removeProduct(Product product) {
		productMgt.remove(product);
	}

	@Override
	public Product saveProduct(Product product) {
		return productMgt.saveProduct(product);
	}

	@Override
	public void deleteProductImage(ProductImage image) {
		productMgt.deleteProductImage(image);
	}

	@Override
	public List<Manufacturer> getManufacturers() {
		return productMgt.getManufacturerers();
	}

	@Override
	public ProductQuantity createNewProductQuantity() {
		return productMgt.newProductQuantityInstance();
	}

	@Override
	public void deleteProductQuantity(ProductQuantity productQuantity) {
		productMgt.deleteProductQuantity(productQuantity);
	}

	@Override
	public ProductQuantity saveProductQuantity(ProductQuantity productQuantity) {
		return productMgt.saveProductQuantity(productQuantity);
	}

	@Override
	public ProductImage saveProductImage(String imageName, byte[] imageBytes) throws IOException {
		return productMgt.saveImage(imageName, imageBytes);
	}

	@Override
	public ProductImage saveProductImage(ProductImage productImage) {
		return productMgt.saveProductImage(productImage);
	}

	@Override
	public ProductQuantity loadProductImages(ProductQuantity productQuantity) {
		return productMgt.getProductQuantity(productQuantity.getSku());
	}

	@Override
	public List<Product> getProductsList(int offset, int maxResults) {
		return productMgt.getProductList(offset, maxResults);
	}

	@Override
	public Integer getTotalProducts() {
		return productMgt.getTotalProducts();
	}

	@Override
	public Manufacturer getManufacturerById(Integer id) {
		return productMgt.getManufacturerById(id);
	}

	@Override
	public List<Category> getCategoriesByIds(Collection<Integer> ids) {
		return categoryMgt.getCategoriesByIds(ids);
	}
}
