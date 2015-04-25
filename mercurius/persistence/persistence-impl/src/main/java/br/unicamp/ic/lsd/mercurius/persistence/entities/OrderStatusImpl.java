package br.unicamp.ic.lsd.mercurius.persistence.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.unicamp.ic.lsd.mercurius.datatype.OrderStatus;

import com.google.common.base.Objects;

@Entity(name = "OrderStatus")
@Table(name = "order_status")
public class OrderStatusImpl implements OrderStatus {

	private static final long serialVersionUID = -6776649882689514901L;

	protected OrderStatusImpl() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "order_status_id", nullable = false)
	private Integer id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "cancelable", nullable = false)
	private Boolean cancelable;

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean isCancelable() {
		return cancelable;
	}

	public void setCancelable(Boolean cancelable) {
		this.cancelable = cancelable;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(super.hashCode(), id);
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof OrderStatusImpl) {
			OrderStatusImpl that = (OrderStatusImpl) object;
			return Objects.equal(this.id, that.id);
		}
		return false;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OrderStatusImpl [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append("]");
		return builder.toString();
	}

}
