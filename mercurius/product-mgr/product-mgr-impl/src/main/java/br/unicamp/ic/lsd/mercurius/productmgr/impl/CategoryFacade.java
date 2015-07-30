package br.unicamp.ic.lsd.mercurius.productmgr.impl;

import java.util.Collection;
import java.util.List;

import br.unicamp.ic.lsd.mercurius.categorymgr.spec.prov.CategoryMgt;
import br.unicamp.ic.lsd.mercurius.datatype.Category;
import br.unicamp.ic.lsd.mercurius.productmgr.spec.prov.ProductCategoryMgt;
import br.unicamp.ic.sed.cosmos.IManager;

class CategoryFacade implements ProductCategoryMgt {

	private static final String CATEGORY_MGT = "CategoryMgt";
	private static final String CATEGORY_MANAGER = "CategoryManager";
	private final Manager manager;
	private final CategoryMgt internalCategoryMgt;

	CategoryFacade(Manager manager) {
		super();
		this.manager = manager;
		IManager categoryManager = this.manager.getInternalComponent(CATEGORY_MANAGER);
		internalCategoryMgt = (CategoryMgt) categoryManager.getProvidedInterface(CATEGORY_MGT);
	}

	@Override
	public List<Category> getCategoryTree() {
		return internalCategoryMgt.getCategoryTree();
	}

	@Override
	public void loadSubCategories(Category category) {
		internalCategoryMgt.loadSubCategories(category);
	}

	@Override
	public Category getCategoryById(Integer categoryId) {
		return internalCategoryMgt.getCategoryById(categoryId);
	}

	@Override
	public void loadCategoryProducts(Category category) {
		internalCategoryMgt.loadCategoryProducts(category);
	}

	@Override
	public Category saveCategory(Category category) {
		return internalCategoryMgt.saveCategory(category);
	}

	@Override
	public List<Category> getAllCategories() {
		return internalCategoryMgt.getAllCategories();
	}

	@Override
	public List<Category> getCategoriesByIds(Collection<Integer> ids) {
		return internalCategoryMgt.getCategoriesByIds(ids);
	}

}
