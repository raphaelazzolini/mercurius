package br.unicamp.ic.lsd.mercurius.recommendedproducts.impl;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.unicamp.ic.lsd.mercurius.persistence.dao.ProductDAO;
import br.unicamp.ic.lsd.mercurius.recommendedproducts.spec.prov.RecommendedProductsManager;
import br.unicamp.ic.lsd.mercurius.recommendedproducts.spec.prov.RecommendedProductsMgt;
import br.unicamp.ic.sed.cosmos.AManager;
import br.unicamp.ic.sed.cosmos.IManager;

class Manager extends AManager implements RecommendedProductsManager {

	private ProductDAO productDAO;

	Manager() {
		super();
		try {
			Context context = new InitialContext();
			productDAO = (ProductDAO) context.lookup("java:app/persistence/productDAO");
			setProvidedInterfaceType("IManager", IManager.class);
			setProvidedInterface("IManager", this);
			setProvidedInterfaceType("RecommendedProductsMgt", RecommendedProductsMgt.class);
			setProvidedInterface("RecommendedProductsMgt", new RecommendedProductsFacade(this));
		} catch (NamingException e) {
		}
	}

	@Override
	public ProductDAO getProductDAO() {
		return productDAO;
	}

}
