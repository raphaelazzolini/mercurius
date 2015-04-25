package br.unicamp.ic.lsd.mercurius.persistence.entities;

import javax.ejb.Local;
import javax.ejb.Singleton;

import br.unicamp.ic.lsd.mercurius.datatype.Promotion;
import br.unicamp.ic.lsd.mercurius.datatype.factory.PromotionFactory;

@Singleton(name = "promotionDiscountFactory")
@Local(PromotionFactory.class)
public class PromotionDiscountFactory implements PromotionFactory {

	private PromotionDiscountFactory() {
		super();
	}

	@Override
	public Promotion createInstance() {
		return new PromotionDiscount();
	}

}
