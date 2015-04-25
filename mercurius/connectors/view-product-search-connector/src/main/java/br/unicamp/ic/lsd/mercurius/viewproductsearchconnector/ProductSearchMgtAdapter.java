package br.unicamp.ic.lsd.mercurius.viewproductsearchconnector;

import java.util.List;

import br.unicamp.ic.lsd.mercurius.datatype.Product;
import br.unicamp.ic.lsd.mercurius.productsearchmgr.spec.prov.ProductSearchMgt;
import br.unicamp.ic.lsd.mercurius.view.spec.req.ViewProductSearchMgt;
import br.unicamp.ic.sed.cosmos.IManager;

class ProductSearchMgtAdapter implements ViewProductSearchMgt {

	private final IManager manager;
	private ProductSearchMgt productSearchMgt;

	ProductSearchMgtAdapter(IManager manager) {
		super();
		this.manager = manager;
		productSearchMgt = (ProductSearchMgt) this.manager.getRequiredInterface("ProductSearchMgt");
	}

	@Override
	public List<Product> searchByText(String text) {
		return productSearchMgt.searchByText(text);
	}

}
