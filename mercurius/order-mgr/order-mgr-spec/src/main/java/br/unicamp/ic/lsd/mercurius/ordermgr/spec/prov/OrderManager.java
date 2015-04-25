package br.unicamp.ic.lsd.mercurius.ordermgr.spec.prov;

import javax.ejb.Local;

import br.unicamp.ic.lsd.mercurius.datatype.factory.OrderProductFactory;
import br.unicamp.ic.lsd.mercurius.datatype.factory.OrderTotalFactory;
import br.unicamp.ic.lsd.mercurius.persistence.dao.OrderDAO;
import br.unicamp.ic.lsd.mercurius.persistence.dao.OrderProductDAO;
import br.unicamp.ic.lsd.mercurius.persistence.dao.OrderStatusDAO;
import br.unicamp.ic.lsd.mercurius.persistence.dao.OrderStatusHistoryDAO;
import br.unicamp.ic.lsd.mercurius.persistence.dao.OrderTotalDAO;
import br.unicamp.ic.lsd.mercurius.persistence.dao.PaymentNotificationDAO;
import br.unicamp.ic.sed.cosmos.IManager;

@Local
public interface OrderManager extends IManager {

	OrderStatusDAO getOrderStatusDAO();

	PaymentNotificationDAO getPaymentNotificationDAO();

	OrderDAO getOrderDAO();

	OrderProductDAO getOrderProductDAO();

	OrderTotalDAO getOrderTotalDAO();

	OrderStatusHistoryDAO getOrderStatusHistoryDAO();

	OrderProductFactory getOrderProductFactory();

	OrderTotalFactory getOrderTotalFactory();

}
