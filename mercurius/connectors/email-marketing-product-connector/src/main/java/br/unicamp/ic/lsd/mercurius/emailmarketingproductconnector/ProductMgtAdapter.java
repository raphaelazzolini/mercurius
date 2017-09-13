package br.unicamp.ic.lsd.mercurius.emailmarketingproductconnector;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.unicamp.ic.lsd.mercurius.datatype.Product;
import br.unicamp.ic.lsd.mercurius.emailmarketingmgr.spec.req.EmailMarketingProductMgt;
import br.unicamp.ic.lsd.mercurius.productmgr.spec.prov.ProductMgt;
import br.unicamp.ic.sed.cosmos.IManager;

public class ProductMgtAdapter implements EmailMarketingProductMgt {

	private final IManager manager;
	private ProductMgt productMgt;
	
	public ProductMgtAdapter(Manager manager) {
		super();
		this.manager = manager;
		productMgt = (ProductMgt) this.manager.getRequiredInterface("ProductMgt");
	}

	@Override
	public List<Product> getProducts() {
		// Gets a set of #4 products from the database
		Collection<Product> productsColl = productMgt.getRandomProducts(4);
		List<Product> products = new ArrayList<Product>(productsColl);

		return products;
	}

}