package br.unicamp.ic.lsd.mercurius.persistence.entities;

import br.unicamp.ic.lsd.mercurius.datatype.PaymentMethod;

import com.google.common.base.Objects;

public class PaymentMethodImpl implements PaymentMethod {

	private static final long serialVersionUID = -171291662073648136L;

	private String name;

	protected PaymentMethodImpl() {
		super();
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(name);
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof PaymentMethodImpl) {
			PaymentMethodImpl that = (PaymentMethodImpl) object;
			return Objects.equal(this.name, that.name);
		}
		return false;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PaymentMethodImpl [name=");
		builder.append(name);
		builder.append("]");
		return builder.toString();
	}

}
