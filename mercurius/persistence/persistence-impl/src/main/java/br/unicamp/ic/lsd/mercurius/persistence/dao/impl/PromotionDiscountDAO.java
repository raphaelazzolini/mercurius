package br.unicamp.ic.lsd.mercurius.persistence.dao.impl;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import br.unicamp.ic.lsd.mercurius.datatype.Customer;
import br.unicamp.ic.lsd.mercurius.datatype.Promotion;
import br.unicamp.ic.lsd.mercurius.datatype.factory.PromotionFactory;
import br.unicamp.ic.lsd.mercurius.persistence.dao.PromotionDAO;

@Stateless(name = "promotionDiscountDAO")
@Local(PromotionDAO.class)
public class PromotionDiscountDAO extends AbstractDAO<Promotion, Integer> implements PromotionDAO {

	private static final long serialVersionUID = -2609221187409768308L;

	@EJB(name = "promotionDiscountFactory")
	private PromotionFactory productQuantityFactory;

	private static Class<? extends Promotion> entityClass;

	@Override
	public Promotion newInstance() {
		return productQuantityFactory.createInstance();
	}

	@Override
	public List<Promotion> getPromotions(Customer customer) {
		Date today = new Date();
		TypedQuery<Promotion> query = getEntityManager().createQuery(
				"from PromotionDiscount where startDate <= :today and endDate >= :today", Promotion.class);
		query.setParameter("today", today);
		return query.getResultList();
	}

	@Override
	protected Class<? extends Promotion> getEntityClass() {
		if (entityClass == null) {
			entityClass = productQuantityFactory.createInstance().getClass();
		}
		return entityClass;
	}

}
