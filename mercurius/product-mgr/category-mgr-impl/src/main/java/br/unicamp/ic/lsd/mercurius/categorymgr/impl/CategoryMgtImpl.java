package br.unicamp.ic.lsd.mercurius.categorymgr.impl;

import java.util.List;

import br.unicamp.ic.lsd.mercurius.categorymgr.spec.prov.CategoryManager;
import br.unicamp.ic.lsd.mercurius.datatype.Category;

class CategoryMgtImpl {

	private final CategoryManager manager;

	CategoryMgtImpl(CategoryManager manager) {
		super();
		this.manager = manager;
	}

	public List<Category> getCategoryTree() {
		List<Category> mainCategories = manager.getCategoryDAO()
				.getMainCategories();

		for (Category category : mainCategories) {
			manager.getCategoryDAO().loadSubCategories(category);
		}

		return mainCategories;
	}

	public void loadSubCategories(Category category) {
		manager.getCategoryDAO().loadSubCategories(category);
	}

	public Category getCategoryById(Integer categoryId) {
		return manager.getCategoryDAO().findById(categoryId);
	}

	public void loadCategoryProducts(Category category) {
		manager.getCategoryDAO().loadCategoryProducts(category);
	}

	public Category saveCategory(Category category) {
		return manager.getCategoryDAO().merge(category);
	}

}
