package br.unicamp.ic.lsd.mercurius.vieworderconnector;

import java.util.List;

import br.unicamp.ic.lsd.mercurius.datatype.Basket;
import br.unicamp.ic.lsd.mercurius.datatype.Order;
import br.unicamp.ic.lsd.mercurius.datatype.PaymentMethod;
import br.unicamp.ic.lsd.mercurius.excpetionhandler.exceptions.OutOfStockException;
import br.unicamp.ic.lsd.mercurius.excpetionhandler.exceptions.ProductNotFoundException;
import br.unicamp.ic.lsd.mercurius.ordermgr.spec.prov.OrderMgt;
import br.unicamp.ic.lsd.mercurius.view.spec.entities.CreditCardInformation;
import br.unicamp.ic.lsd.mercurius.view.spec.req.ViewOrderMgt;
import br.unicamp.ic.sed.cosmos.IManager;

class ViewOrderAdapter implements ViewOrderMgt {

	private final IManager manager;
	private OrderMgt orderMgt;

	ViewOrderAdapter(IManager manager) {
		super();
		this.manager = manager;
		orderMgt = (OrderMgt) this.manager.getRequiredInterface("OrderMgt");
	}

	@Override
	public Order createOrder(Basket basket) {
		return orderMgt.createOrder(basket);
	}

	@Override
	public Order saveOrder(Order order, PaymentMethod paymentMethod, CreditCardInformation creditCardInformation)
			throws ProductNotFoundException, OutOfStockException {
		return orderMgt.saveOrder(order, paymentMethod, creditCardInformation.toJson());
	}

	@Override
	public CreditCardInformation getCreditCardInformationInstance() {
		return new CreditCardInformationImpl();
	}

	@Override
	public List<PaymentMethod> getPaymentMethods(Order order) {
		return orderMgt.getPaymentMethods(order);
	}
}
