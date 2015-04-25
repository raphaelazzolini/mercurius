package br.unicamp.ic.lsd.mercurius.view.spec.req;

import java.util.List;

import br.unicamp.ic.lsd.mercurius.datatype.Basket;
import br.unicamp.ic.lsd.mercurius.datatype.Order;
import br.unicamp.ic.lsd.mercurius.datatype.PaymentMethod;
import br.unicamp.ic.lsd.mercurius.excpetionhandler.exceptions.OutOfStockException;
import br.unicamp.ic.lsd.mercurius.excpetionhandler.exceptions.ProductNotFoundException;
import br.unicamp.ic.lsd.mercurius.view.spec.entities.CreditCardInformation;

public interface ViewOrderMgt {

	Order createOrder(Basket basket);

	Order saveOrder(Order order, PaymentMethod paymentMethod, CreditCardInformation creditCardInformation)
			throws ProductNotFoundException, OutOfStockException;

	CreditCardInformation getCreditCardInformationInstance();

	List<PaymentMethod> getPaymentMethods(Order order);

}
