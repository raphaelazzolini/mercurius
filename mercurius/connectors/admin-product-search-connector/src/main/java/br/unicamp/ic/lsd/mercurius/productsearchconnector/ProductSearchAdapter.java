package br.unicamp.ic.lsd.mercurius.productsearchconnector;

import br.unicamp.ic.lsd.mercurius.admin.spec.req.AdminProductSearchMgt;
import br.unicamp.ic.lsd.mercurius.productsearchmgr.spec.prov.ProductSearchMgt;
import br.unicamp.ic.sed.cosmos.IManager;

class ProductSearchAdapter implements AdminProductSearchMgt {

	private final IManager manager;

	private ProductSearchMgt productSearchMgt;

	ProductSearchAdapter(IManager manager) {
		super();
		this.manager = manager;
		productSearchMgt = (ProductSearchMgt) this.manager.getRequiredInterface("ProductSearchMgt");
	}

	@Override
	public void indexSearch() {
		productSearchMgt.indexSearch();
	}

}
