package br.unicamp.ic.lsd.mercurius.persistence.spec.req;

import java.util.List;

import br.unicamp.ic.lsd.mercurius.datatype.Customer;
import br.unicamp.ic.lsd.mercurius.datatype.Product;
import br.unicamp.ic.lsd.mercurius.datatype.ProductQuantity;
import br.unicamp.ic.lsd.mercurius.datatype.Promotion;

public interface PersistenceCacheMgt {

	List<Promotion> getFromPromotionCache(Customer customer);

	void putOnPromotionCache(Customer customer, List<Promotion> value);

	ProductQuantity getFromQuantityCache(ProductQuantity quantity);

	void putOnQuantityCache(ProductQuantity quantity, ProductQuantity value);

	Product getFromProductWithCategoriesCache(Product product);

	void putOnProductWithCategoriesCache(Product product, Product value);

	Product getFromProductWithImagesCache(Product product);

	void putOnProductWithImagesCache(Product product, Product value);

	List<Product> getFromProductSearchCache(String text);

	void putOnProductSearchCache(String text, List<Product> value);

}
