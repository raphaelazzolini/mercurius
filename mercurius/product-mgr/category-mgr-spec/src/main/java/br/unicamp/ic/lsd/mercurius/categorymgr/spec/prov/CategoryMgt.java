package br.unicamp.ic.lsd.mercurius.categorymgr.spec.prov;

import java.util.Collection;
import java.util.List;

import br.unicamp.ic.lsd.mercurius.datatype.Category;
import br.unicamp.ic.lsd.mercurius.datatype.Product;

public interface CategoryMgt {

	/**
	 * Returns a {@link List} with all the {@link Category} of root level. The
	 * interface {@link Category} has a {@link List} with the subcategories,
	 * therefore is possible to travel all the tree.
	 * 
	 * @return
	 */
	List<Category> getCategoryTree();

	/**
	 * Loads the {@link List} of subcategories of the informed {@link Category}.
	 * The class that implements the {@link Category} interface must have a
	 * {@link List} of {@link Category} of the fetch type LAZY.
	 * 
	 * @param category
	 *            the {@link Category} that will have the {@link List} loaded
	 */
	void loadSubCategories(Category category);

	/**
	 * Returns the {@link Category} that has the informed id or
	 * <code>null</code> if there is no {@link Category} with this id.
	 * 
	 * @param categoryId
	 *            the id of the {@link Category} to return
	 * @return
	 */
	Category getCategoryById(Integer categoryId);

	/**
	 * Loads the {@link List} of {@link Product} of the informed
	 * {@link Category}. The class that implements the {@link Category}
	 * interface must have a {@link List} of {@link Product} of the fetch type
	 * LAZY.
	 * 
	 * @param category
	 *            the {@link Category} that will have the {@link List} loaded
	 */
	void loadCategoryProducts(Category category);

	/**
	 * Saves the informed {@link Category}.
	 * 
	 * @param category
	 *            the {@link Category} to be saved
	 * @return the saved {@link Category}
	 */
	Category saveCategory(Category category);

	/**
	 * Returns all categories from database
	 * 
	 * @param product
	 * @return
	 */
	List<Category> getAllCategories();

	/**
	 * Returns a list of categories by their id
	 * 
	 * @param id
	 * @return
	 */
	List<Category> getCategoriesByIds(Collection<Integer> ids);

}
