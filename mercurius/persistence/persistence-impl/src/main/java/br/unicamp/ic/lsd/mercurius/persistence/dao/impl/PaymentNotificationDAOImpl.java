package br.unicamp.ic.lsd.mercurius.persistence.dao.impl;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import br.unicamp.ic.lsd.mercurius.datatype.Order;
import br.unicamp.ic.lsd.mercurius.datatype.PaymentNotification;
import br.unicamp.ic.lsd.mercurius.datatype.factory.PaymentNotificationFactory;
import br.unicamp.ic.lsd.mercurius.persistence.dao.PaymentNotificationDAO;

@Stateless(name = "paymentNotificationDAO")
@Local(PaymentNotificationDAO.class)
public class PaymentNotificationDAOImpl extends AbstractDAO<PaymentNotification, Integer> implements
		PaymentNotificationDAO {

	private static final long serialVersionUID = 2113231310814361035L;

	@EJB
	private PaymentNotificationFactory paymentNotificationFactory;

	private static Class<? extends PaymentNotification> entityClass;

	@Override
	public PaymentNotification newInstance() {
		return paymentNotificationFactory.createInstance();
	}

	@Override
	protected Class<? extends PaymentNotification> getEntityClass() {
		if (entityClass == null) {
			PaymentNotification entity = paymentNotificationFactory.createInstance();
			entityClass = entity.getClass();
		}
		return entityClass;
	}

	@Override
	public String getOrderTransactionId(Order order) {
		return null;
	}

}
