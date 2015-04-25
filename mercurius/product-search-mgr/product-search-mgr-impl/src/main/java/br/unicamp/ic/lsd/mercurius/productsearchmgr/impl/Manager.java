package br.unicamp.ic.lsd.mercurius.productsearchmgr.impl;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;

import br.unicamp.ic.lsd.mercurius.persistence.dao.ProductDAO;
import br.unicamp.ic.lsd.mercurius.productsearchmgr.spec.prov.ProductSearchManager;
import br.unicamp.ic.lsd.mercurius.productsearchmgr.spec.prov.ProductSearchMgt;
import br.unicamp.ic.sed.cosmos.AManager;

class Manager extends AManager implements ProductSearchManager {

	private static final String PRODUCT_SEARCH_MGT = "ProductSearchMgt";

	private EntityManager em;
	private ProductDAO productDAO;

	Manager() {
		super();
		try {
			Context context = new InitialContext();
			productDAO = (ProductDAO) context.lookup("java:app/persistence/productDAO");
			em = productDAO.getEntityManager();
			setProvidedInterfaceType(PRODUCT_SEARCH_MGT, ProductSearchMgt.class);
			setProvidedInterface(PRODUCT_SEARCH_MGT, new ProductSearchFacade(this));
		} catch (NamingException e) {
		}
	}

	@Override
	public ProductDAO getProductDAO() {
		return productDAO;
	}

	@Override
	public EntityManager getEm() {
		return em;
	}

}
