package br.unicamp.ic.lsd.mercurius.persistence.dao;

import java.util.Collection;
import java.util.List;

import br.unicamp.ic.lsd.mercurius.datatype.Category;
import br.unicamp.ic.lsd.mercurius.datatype.Product;

public interface CategoryDAO extends DAO<Category, Integer> {

	void loadSubCategories(Category category);

	void loadCategoryProducts(Category category);

	List<Category> getMainCategories();

	List<Category> getCategoriesWithoutProduct(Product product);

	List<Category> findByIds(Collection<Integer> ids);

}
