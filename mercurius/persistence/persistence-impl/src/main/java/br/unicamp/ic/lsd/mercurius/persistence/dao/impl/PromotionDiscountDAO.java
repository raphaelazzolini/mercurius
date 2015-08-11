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
import br.unicamp.ic.lsd.mercurius.persistence.spec.req.PersistenceCacheMgt;
import br.unicamp.ic.lsd.mercurius.persistencecacheconnector.CacheConnectorComponentFactory;

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
		PersistenceCacheMgt cacheMgt = (PersistenceCacheMgt) CacheConnectorComponentFactory.createInstance()
				.getProvidedInterface("PersistenceCacheMgt");
		List<Promotion> promotions = null;
		if (cacheMgt != null) {
			promotions = cacheMgt.getFromPromotionCache(customer);
		}
		if (promotions == null) {
			Date today = new Date();
			TypedQuery<Promotion> query = getEntityManager().createQuery(
					"from PromotionDiscount where startDate <= :today and endDate >= :today", Promotion.class);
			query.setParameter("today", today);
			promotions = query.getResultList();
		}
		if (cacheMgt != null) {
			cacheMgt.putOnPromotionCache(customer, promotions);
		}
		return promotions;
	}

	@Override
	protected Class<? extends Promotion> getEntityClass() {
		if (entityClass == null) {
			entityClass = productQuantityFactory.createInstance().getClass();
		}
		return entityClass;
	}

}
