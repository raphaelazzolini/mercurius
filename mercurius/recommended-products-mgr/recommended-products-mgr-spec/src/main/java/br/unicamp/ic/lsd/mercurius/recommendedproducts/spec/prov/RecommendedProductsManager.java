package br.unicamp.ic.lsd.mercurius.recommendedproducts.spec.prov;

import br.unicamp.ic.lsd.mercurius.persistence.dao.ProductDAO;
import br.unicamp.ic.sed.cosmos.IManager;

public interface RecommendedProductsManager extends IManager {

	ProductDAO getProductDAO();

}
