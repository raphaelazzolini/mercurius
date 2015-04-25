package br.unicamp.ic.lsd.mercurius.categorymgr.impl;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.unicamp.ic.lsd.mercurius.categorymgr.spec.prov.CategoryManager;
import br.unicamp.ic.lsd.mercurius.categorymgr.spec.prov.CategoryMgt;
import br.unicamp.ic.lsd.mercurius.persistence.dao.CategoryDAO;
import br.unicamp.ic.sed.cosmos.AManager;
import br.unicamp.ic.sed.cosmos.IManager;

class Manager extends AManager implements CategoryManager {

	private static final String I_MANAGER = "IManager";
	private static final String CATEGORY_MGT = "CategoryMgt";

	private CategoryDAO categoryDAO;

	Manager() {
		super();
		try {
			Context context = new InitialContext();
			categoryDAO = (CategoryDAO) context.lookup("java:app/persistence/categoryDAO");
			setProvidedInterface(CATEGORY_MGT, new CategoryFacade(this));
			setProvidedInterface(I_MANAGER, this);
			setProvidedInterfaceType(CATEGORY_MGT, CategoryMgt.class);
			setProvidedInterfaceType(I_MANAGER, IManager.class);
		} catch (NamingException e) {
		}
	}

	@Override
	public CategoryDAO getCategoryDAO() {
		return categoryDAO;
	}

}
