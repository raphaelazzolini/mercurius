package br.unicamp.ic.lsd.mercurius.recommendedproductsmgr.spec.prov;

import br.unicamp.ic.lsd.mercurius.persistence.dao.ProductDAO;
import br.unicamp.ic.sed.cosmos.IManager;

public interface RecommendedProductsManager extends IManager {

	ProductDAO getProductDAO();

}
