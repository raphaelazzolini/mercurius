package br.unicamp.ic.lsd.mercurius.promotionmgr.spec.prov;

import br.unicamp.ic.lsd.mercurius.persistence.dao.PromotionDAO;
import br.unicamp.ic.sed.cosmos.IManager;

public interface PromotionManager extends IManager {

	PromotionDAO getPromotionDiscountDAO();

}
