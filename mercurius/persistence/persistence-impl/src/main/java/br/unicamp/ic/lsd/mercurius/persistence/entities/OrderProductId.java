package br.unicamp.ic.lsd.mercurius.persistence.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.google.common.base.Objects;

@Embeddable
public class OrderProductId implements Serializable {

	private static final long serialVersionUID = -1291179902734725121L;

	@Column(name = "order_id", nullable = false)
	private Integer orderId;

	@Column(name = "sku", nullable = false)
	private String sku;

	public Integer getOrderId() {
		return orderId;
	}

	public String getSku() {
		return sku;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(super.hashCode(), orderId, sku);
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof OrderProductId) {
			OrderProductId that = (OrderProductId) object;
			return Objects.equal(this.orderId, that.orderId) && Objects.equal(this.sku, that.sku);
		}
		return false;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OrderProductId [orderId=");
		builder.append(orderId);
		builder.append(", sku=");
		builder.append(sku);
		builder.append("]");
		return builder.toString();
	}

}
