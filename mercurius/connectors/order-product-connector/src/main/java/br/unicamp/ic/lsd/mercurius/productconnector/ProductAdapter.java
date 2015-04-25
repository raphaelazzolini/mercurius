package br.unicamp.ic.lsd.mercurius.productconnector;

import br.unicamp.ic.lsd.mercurius.datatype.Product;
import br.unicamp.ic.lsd.mercurius.datatype.ProductQuantity;
import br.unicamp.ic.lsd.mercurius.ordermgr.spec.req.OrderProductMgt;
import br.unicamp.ic.lsd.mercurius.productmgr.spec.prov.ProductMgt;
import br.unicamp.ic.sed.cosmos.IManager;

class ProductAdapter implements OrderProductMgt {

	private final IManager manager;

	ProductAdapter(IManager manager) {
		super();
		this.manager = manager;
	}

	@Override
	public boolean hasQuantityAvailable(String sku, int quantity) {
		ProductMgt productMgt = (ProductMgt) this.manager.getRequiredInterface("ProductMgt");
		ProductQuantity availableQuantity = productMgt.getProductQuantity(sku);
		if (availableQuantity != null) {
			return availableQuantity.getQuantity() >= quantity;
		}
		return false;
	}

	@Override
	public boolean productExists(Integer productId) {
		ProductMgt productMgt = (ProductMgt) this.manager.getRequiredInterface("ProductMgt");
		Product product = productMgt.getProduct(productId);
		return product != null;
	}

}
