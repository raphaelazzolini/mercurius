package br.unicamp.ic.lsd.mercurius.persistence.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.google.common.base.Objects;

@Embeddable
public class BasketItemId implements Serializable {

	private static final long serialVersionUID = -9009055419399761243L;

	@Column(name = "basket_id", nullable = false, insertable = false, updatable = false)
	private Integer basketId;

	@Column(name = "sku")
	private String sku;

	Integer getBasketId() {
		return basketId;
	}

	void setBasketId(Integer basketId) {
		this.basketId = basketId;
	}

	String getSku() {
		return sku;
	}

	void setSku(String sku) {
		this.sku = sku;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(super.hashCode(), basketId, sku);
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof BasketItemId) {
			BasketItemId that = (BasketItemId) object;
			return Objects.equal(this.basketId, that.basketId) && Objects.equal(this.sku, that.sku);
		}
		return false;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BasketItemId [basketId=");
		builder.append(basketId);
		builder.append(", sku=");
		builder.append(sku);
		builder.append("]");
		return builder.toString();
	}

}
