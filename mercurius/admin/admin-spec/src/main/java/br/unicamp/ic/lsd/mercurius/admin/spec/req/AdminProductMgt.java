package br.unicamp.ic.lsd.mercurius.admin.spec.req;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import br.unicamp.ic.lsd.mercurius.datatype.Category;
import br.unicamp.ic.lsd.mercurius.datatype.Manufacturer;
import br.unicamp.ic.lsd.mercurius.datatype.Product;
import br.unicamp.ic.lsd.mercurius.datatype.ProductImage;
import br.unicamp.ic.lsd.mercurius.datatype.ProductQuantity;

public interface AdminProductMgt {

	Product createNewProductInstance();

	List<Category> getAllCategories();

	Product loadCategories(Product product);

	void removeProduct(Product product);

	Product saveProduct(Product product);

	void deleteProductImage(ProductImage image);

	List<Manufacturer> getManufacturers();

	ProductQuantity createNewProductQuantity();

	ProductQuantity saveProductQuantity(ProductQuantity productQuantity);

	void deleteProductQuantity(ProductQuantity productQuantity);

	ProductImage saveProductImage(String imageName, byte[] imageBytes) throws IOException;

	ProductImage saveProductImage(ProductImage productImage);

	ProductQuantity loadProductImages(ProductQuantity productQuantity);

	List<Product> getProductsList(int offset, int maxResults);

	Integer getTotalProducts();

	Manufacturer getManufacturerById(Integer id);

	List<Category> getCategoriesByIds(Collection<Integer> ids);

}
