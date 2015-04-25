package br.unicamp.ic.lsd.mercurius.productsearchmgr.impl;

import java.util.List;

import br.unicamp.ic.lsd.mercurius.datatype.Product;
import br.unicamp.ic.lsd.mercurius.productsearchmgr.spec.prov.ProductSearchManager;
import br.unicamp.ic.lsd.mercurius.productsearchmgr.spec.prov.ProductSearchMgt;

class ProductSearchFacade implements ProductSearchMgt {

	private final ProductSearchManager manager;

	private ProductSearchMgtImpl productSearchMgtImpl;

	protected ProductSearchFacade(ProductSearchManager manager) {
		super();
		this.manager = manager;
		productSearchMgtImpl = new ProductSearchMgtImpl(this.manager);
	}

	@Override
	public List<Product> searchByParams(String... params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> searchByText(String text) {
		return productSearchMgtImpl.searchByText(text);
	}

	@Override
	public List<Product> lastProductSearched() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> searchByLasCategoriesSeen() {
		// TODO Auto-generated method stub
		return null;
	}

}
