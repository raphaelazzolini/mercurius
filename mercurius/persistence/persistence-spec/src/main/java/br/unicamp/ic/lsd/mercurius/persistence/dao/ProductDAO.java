package br.unicamp.ic.lsd.mercurius.persistence.dao;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;

import br.unicamp.ic.lsd.mercurius.datatype.Product;
import br.unicamp.ic.lsd.mercurius.datatype.ProductAttribute;
import br.unicamp.ic.lsd.mercurius.datatype.ProductQuantity;

public interface ProductDAO extends DAO<Product, Integer> {

	Product loadCategories(Product product);

	ProductQuantity getProductQuantity(Product product, List<ProductAttribute> attributes);

	EntityManager getEntityManager();

	List<Product> searchByText(String text);

	Product loadProductImages(Product product);

	Long getProductCount();

	void indexProductSearch();

	Collection<Product> getRandomProducts(Integer quantity);
	
	Collection<Product> getRecommendedProducts(Double x_coord,Double y_coord,Double distance, Integer quantity);

}
