package br.unicamp.ic.lsd.mercurius.categorymgr.spec.prov;

import javax.ejb.Local;

import br.unicamp.ic.lsd.mercurius.persistence.dao.CategoryDAO;
import br.unicamp.ic.sed.cosmos.IManager;

@Local
public interface CategoryManager extends IManager {

	CategoryDAO getCategoryDAO();

}
