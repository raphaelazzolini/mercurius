package br.unicamp.ic.lsd.mercurius.persistence.dao.impl;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import br.unicamp.ic.lsd.mercurius.datatype.Configuration;
import br.unicamp.ic.lsd.mercurius.datatype.OrderStatus;
import br.unicamp.ic.lsd.mercurius.datatype.factory.OrderStatusFactory;
import br.unicamp.ic.lsd.mercurius.persistence.dao.ConfigurationDAO;
import br.unicamp.ic.lsd.mercurius.persistence.dao.OrderStatusDAO;

import com.google.common.primitives.Ints;

@Stateless(name = "orderStatusDAO")
@Local(OrderStatusDAO.class)
public class OrderStatusDAOImpl extends AbstractDAO<OrderStatus, Integer> implements OrderStatusDAO {

	private static final long serialVersionUID = -1565201193564417263L;

	private static final String ORDER_ARRIVED_STATUS = "ORDER_ARRIVED_STATUS";
	private static final String ORDER_SENT_STATUS = "ORDER_SENT_STATUS";
	private static final String FAILED_PAYMENT_ORDER_STATUS = "FAILED_PAYMENT_ORDER_STATUS";
	private static final String CONFIRMED_PAYMENT_ORDER_STATUS = "CONFIRMED_PAYMENT_ORDER_STATUS";
	private static final String CANCELED_ORDER_STATUS = "CANCELED_ORDER_STATUS";
	private static final String FIRST_ORDER_STATUS = "FIRST_ORDER_STATUS";

	@EJB
	private OrderStatusFactory orderStatusFactory;

	@EJB
	private ConfigurationDAO configurationDAO;

	private static Class<? extends OrderStatus> entityClass;

	@Override
	public OrderStatus newInstance() {
		return orderStatusFactory.createInstance();
	}

	@Override
	protected Class<? extends OrderStatus> getEntityClass() {
		if (entityClass == null) {
			OrderStatus entity = orderStatusFactory.createInstance();
			entityClass = entity.getClass();
		}
		return entityClass;
	}

	@Override
	public OrderStatus getFirstOrderStatus() {
		Configuration firstStatusConfig = configurationDAO.findById(FIRST_ORDER_STATUS);
		return getOrderStatusFromConfiguration(firstStatusConfig);
	}

	@Override
	public OrderStatus getCanceledOrderStatus() {
		Configuration canceledStatusConfig = configurationDAO.findById(CANCELED_ORDER_STATUS);
		return getOrderStatusFromConfiguration(canceledStatusConfig);
	}

	@Override
	public OrderStatus getPaymentConfirmedOrderStatus() {
		Configuration canceledStatusConfig = configurationDAO.findById(CONFIRMED_PAYMENT_ORDER_STATUS);
		return getOrderStatusFromConfiguration(canceledStatusConfig);
	}

	@Override
	public OrderStatus getPaymentFailedOrderStatus() {
		Configuration canceledStatusConfig = configurationDAO.findById(FAILED_PAYMENT_ORDER_STATUS);
		return getOrderStatusFromConfiguration(canceledStatusConfig);
	}

	@Override
	public OrderStatus getOrderSentStatus() {
		Configuration canceledStatusConfig = configurationDAO.findById(ORDER_SENT_STATUS);
		return getOrderStatusFromConfiguration(canceledStatusConfig);
	}

	@Override
	public OrderStatus getOrderArrivedStatus() {
		Configuration canceledStatusConfig = configurationDAO.findById(ORDER_ARRIVED_STATUS);
		return getOrderStatusFromConfiguration(canceledStatusConfig);
	}

	private OrderStatus getOrderStatusFromConfiguration(Configuration configuration) {
		if (configuration != null) {
			Integer orderStatusId = Ints.tryParse(configuration.getValue());
			if (orderStatusId != null) {
				return findById(orderStatusId);
			}
		}
		return null;
	}

}
