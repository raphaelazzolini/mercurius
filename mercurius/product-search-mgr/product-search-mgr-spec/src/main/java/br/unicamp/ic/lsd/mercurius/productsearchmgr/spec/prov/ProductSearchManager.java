package br.unicamp.ic.lsd.mercurius.productsearchmgr.spec.prov;

import javax.ejb.Local;
import javax.persistence.EntityManager;

import br.unicamp.ic.lsd.mercurius.persistence.dao.ProductDAO;
import br.unicamp.ic.sed.cosmos.IManager;

@Local
public interface ProductSearchManager extends IManager {

	ProductDAO getProductDAO();

	EntityManager getEm();

}
