package br.unicamp.ic.lsd.mercurius.persistence.entities;

import java.math.BigDecimal;

import br.unicamp.ic.lsd.mercurius.datatype.Order;
import br.unicamp.ic.lsd.mercurius.datatype.Payment;
import br.unicamp.ic.lsd.mercurius.datatype.PaymentMethod;

import com.google.common.base.Objects;

class PaymentImpl implements Payment {

	private static final long serialVersionUID = 351672084011852849L;

	private PaymentMethod paymentMethod;
	private BigDecimal value;
	private String paymentInformation;
	private Order order;

	protected PaymentImpl() {
		super();
	}

	@Override
	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	@Override
	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	@Override
	public BigDecimal getValue() {
		return value;
	}

	@Override
	public void setValue(BigDecimal value) {
		this.value = value;
	}

	@Override
	public String getPaymentInformation() {
		return paymentInformation;
	}

	@Override
	public void setPaymentInformation(String paymentInformation) {
		this.paymentInformation = paymentInformation;
	}

	@Override
	public Order getOrder() {
		return order;
	}

	@Override
	public void setOrder(Order order) {
		this.order = order;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(order);
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof PaymentImpl) {
			PaymentImpl that = (PaymentImpl) object;
			return Objects.equal(this.order, that.order);
		}
		return false;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PaymentImpl [paymentMethod=");
		builder.append(paymentMethod);
		builder.append(", value=");
		builder.append(value);
		builder.append(", paymentInformation=");
		builder.append(paymentInformation);
		builder.append(", order=");
		builder.append(order);
		builder.append("]");
		return builder.toString();
	}

}
