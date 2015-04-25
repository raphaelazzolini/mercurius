package br.unicamp.ic.lsd.mercurius.productsearchmgr.impl;

import java.util.List;

import br.unicamp.ic.lsd.mercurius.datatype.Product;
import br.unicamp.ic.lsd.mercurius.productsearchmgr.spec.prov.ProductSearchManager;

class ProductSearchMgtImpl {

	private final ProductSearchManager manager;

	protected ProductSearchMgtImpl(ProductSearchManager manager) {
		super();
		this.manager = manager;
	}

	protected List<Product> searchByText(String text) {
		return manager.getProductDAO().searchByText(text);
	}
}
