package br.unicamp.ic.lsd.mercurius.orderpaymentconnector;

import java.util.ArrayList;
import java.util.List;

import br.unicamp.ic.lsd.mercurius.creditcardmgr.spec.prov.CreditCardMgt;
import br.unicamp.ic.lsd.mercurius.datatype.Order;
import br.unicamp.ic.lsd.mercurius.datatype.Payment;
import br.unicamp.ic.lsd.mercurius.datatype.PaymentMethod;
import br.unicamp.ic.lsd.mercurius.datatype.PaymentNotification;
import br.unicamp.ic.lsd.mercurius.ordermgr.spec.req.PaymentMgt;
import br.unicamp.ic.sed.cosmos.IManager;

class PaymentAdapter implements PaymentMgt {

	private final IManager manager;
	private CreditCardMgt creditCardMgt;

	PaymentAdapter(IManager manager) {
		super();
		this.manager = manager;
		creditCardMgt = (CreditCardMgt) this.manager.getRequiredInterface("CreditCardMgt");
	}

	@Override
	public List<PaymentMethod> getPaymentMethods(Order order) {
		List<PaymentMethod> paymentMethods = new ArrayList<PaymentMethod>();
		paymentMethods.add(creditCardMgt.getPaymentMethod());
		return paymentMethods;
	}

	@Override
	public PaymentNotification makeAPayment(Payment payment) {
		if (payment.getPaymentMethod().equals(creditCardMgt.getPaymentMethod())) {
			return creditCardMgt.makeAPayment(payment);
		}
		return null;
	}

	@Override
	public List<PaymentNotification> checkPaymentNotificatons(String transactionId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Payment createPayment(Order order, String paymentInformation, PaymentMethod paymentMethod) {
		if (paymentMethod.equals(creditCardMgt.getPaymentMethod())) {
			return creditCardMgt.createPayment(order.getOrderTotal(), paymentInformation);
		}
		return null;
	}

}
