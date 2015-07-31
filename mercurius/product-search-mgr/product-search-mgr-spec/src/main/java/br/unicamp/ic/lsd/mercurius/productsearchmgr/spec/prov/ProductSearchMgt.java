package br.unicamp.ic.lsd.mercurius.productsearchmgr.spec.prov;

import java.util.List;

import br.unicamp.ic.lsd.mercurius.datatype.Product;

public interface ProductSearchMgt {

	/**
	 * Returns a {@link List} of {@link Product} for the given parameters.
	 * 
	 * @param params
	 * @return
	 */
	List<Product> searchByParams(String... params);

	/**
	 * Returns a {@link List} of {@link Product} for the given text.
	 * 
	 * @param text
	 * @return
	 */
	List<Product> searchByText(String text);

	/**
	 * Returns a {@link List} with the last {@link Product} searched by current
	 * customer.
	 * 
	 * @return
	 */
	List<Product> lastProductSearched();

	/**
	 * Returns a {@link List} with the {@link Product} from the last categories
	 * seen by the current customer.
	 * 
	 * @return
	 */
	List<Product> searchByLasCategoriesSeen();

	/**
	 * Indexes the products to the search
	 */
	void indexSearch();

}
