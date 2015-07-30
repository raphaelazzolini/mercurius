package br.unicamp.ic.lsd.mercurius.categorymgr.impl;

import java.util.Collection;
import java.util.List;

import br.unicamp.ic.lsd.mercurius.categorymgr.spec.prov.CategoryManager;
import br.unicamp.ic.lsd.mercurius.categorymgr.spec.prov.CategoryMgt;
import br.unicamp.ic.lsd.mercurius.datatype.Category;

class CategoryFacade implements CategoryMgt {

	private final CategoryManager manager;
	private final CategoryMgtImpl categoryMgt;

	protected CategoryFacade(CategoryManager manager) {
		super();
		this.manager = manager;
		this.categoryMgt = new CategoryMgtImpl(this.manager);
	}

	@Override
	public List<Category> getCategoryTree() {
		return categoryMgt.getCategoryTree();
	}

	@Override
	public void loadSubCategories(Category category) {
		categoryMgt.loadSubCategories(category);
	}

	@Override
	public Category getCategoryById(Integer categoryId) {
		return categoryMgt.getCategoryById(categoryId);
	}

	@Override
	public void loadCategoryProducts(Category category) {
		categoryMgt.loadCategoryProducts(category);
	}

	@Override
	public Category saveCategory(Category category) {
		return categoryMgt.saveCategory(category);
	}

	@Override
	public List<Category> getAllCategories() {
		return manager.getCategoryDAO().getAll();
	}

	@Override
	public List<Category> getCategoriesByIds(Collection<Integer> ids) {
		return manager.getCategoryDAO().findByIds(ids);
	}

}
