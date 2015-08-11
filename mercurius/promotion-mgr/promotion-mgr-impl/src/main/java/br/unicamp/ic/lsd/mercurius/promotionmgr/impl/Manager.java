package br.unicamp.ic.lsd.mercurius.promotionmgr.impl;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.unicamp.ic.lsd.mercurius.persistence.dao.PromotionDAO;
import br.unicamp.ic.lsd.mercurius.promotionmgr.spec.prov.PromotionManager;
import br.unicamp.ic.lsd.mercurius.promotionmgr.spec.prov.PromotionMgt;
import br.unicamp.ic.sed.cosmos.AManager;
import br.unicamp.ic.sed.cosmos.IManager;

class Manager extends AManager implements PromotionManager {

	private PromotionDAO promotionDiscountDAO;

	Manager() {
		super();
		try {
			Context context = new InitialContext();
			promotionDiscountDAO = (PromotionDAO) context.lookup("java:app/persistence/promotionDiscountDAO");
			setProvidedInterfaceType("IManager", IManager.class);
			setProvidedInterface("IManager", this);
			setProvidedInterfaceType("PromotionMgt", PromotionMgt.class);
			setProvidedInterface("PromotionMgt", new PromotionFacade(this));
		} catch (NamingException e) {
		}
	}

	@Override
	public PromotionDAO getPromotionDiscountDAO() {
		return promotionDiscountDAO;
	}

}
