package br.unicamp.ic.lsd.mercurius.persistence.dao;

import java.util.List;

import br.unicamp.ic.lsd.mercurius.datatype.Category;

public interface CategoryDAO extends DAO<Category, Integer> {

	void loadSubCategories(Category category);

	void loadCategoryProducts(Category category);

	List<Category> getMainCategories();

}
