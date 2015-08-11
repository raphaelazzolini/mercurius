package br.unicamp.ic.lsd.mercurius.productsearchmgr.impl;

import java.util.List;

import br.unicamp.ic.lsd.mercurius.datatype.Product;
import br.unicamp.ic.lsd.mercurius.productsearchmgr.spec.prov.ProductSearchManager;
import br.unicamp.ic.lsd.mercurius.productsearchmgr.spec.prov.ProductSearchMgt;
import br.unicamp.ic.lsd.mercurius.productsearchmgr.spec.req.ProductSearchLoggingMgt;
import br.unicamp.ic.lsd.mercurius.productsearchmgr.spec.req.ProductSearchPromotionMgt;

class ProductSearchFacade implements ProductSearchMgt {

	private final ProductSearchManager manager;

	private ProductSearchMgtImpl productSearchMgtImpl;
	private ProductSearchLoggingMgt loggingMgt;

	protected ProductSearchFacade(ProductSearchManager manager) {
		super();
		this.manager = manager;
		productSearchMgtImpl = new ProductSearchMgtImpl(this.manager);
		loggingMgt = (ProductSearchLoggingMgt) this.manager.getRequiredInterface("ProductSearchLoggingMgt");
	}

	@Override
	public List<Product> searchByParams(String... params) {
		try {
			loggingMgt.debug("Executing method searchByParams");
			// TODO Auto-generated method stub
			return null;
		} catch (Exception e) {
			loggingMgt.error("Error executing method searchByParams", e);
			throw e;
		}
	}

	@Override
	public List<Product> searchByText(String text) {
		try {
			ProductSearchPromotionMgt promotionMgt = (ProductSearchPromotionMgt) manager
					.getRequiredInterface("ProductSearchPromotionMgt");
			loggingMgt.debug("Executing method searchByText");
			List<Product> products = productSearchMgtImpl.searchByText(text);
			return promotionMgt.getProductsWithPromotion(products);
		} catch (Exception e) {
			loggingMgt.error("Error executing method searchByText", e);
			throw e;
		}
	}

	@Override
	public List<Product> lastProductSearched() {
		try {
			loggingMgt.debug("Executing method lastProductSearched");
			// TODO Auto-generated method stub
			return null;
		} catch (Exception e) {
			loggingMgt.error("Error executing method lastProductSearched", e);
			throw e;
		}
	}

	@Override
	public List<Product> searchByLasCategoriesSeen() {
		try {
			loggingMgt.debug("Executing method searchByLasCategoriesSeen");
			// TODO Auto-generated method stub
			return null;
		} catch (Exception e) {
			loggingMgt.error("Error executing method searchByLasCategoriesSeen", e);
			throw e;
		}
	}

	@Override
	public void indexSearch() {
		manager.getProductDAO().indexProductSearch();
	}

}
