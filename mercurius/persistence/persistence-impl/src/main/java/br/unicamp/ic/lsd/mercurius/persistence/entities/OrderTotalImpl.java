package br.unicamp.ic.lsd.mercurius.persistence.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.unicamp.ic.lsd.mercurius.datatype.Order;
import br.unicamp.ic.lsd.mercurius.datatype.OrderTotal;
import br.unicamp.ic.lsd.mercurius.datatype.OrderTotalModule;

import com.google.common.base.Objects;

@Entity(name = "OrderTotal")
@Table(name = "order_total")
public class OrderTotalImpl implements OrderTotal {

	private static final long serialVersionUID = -6344452072187915414L;

	protected OrderTotalImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "order_total_id")
	private Integer id;

	@ManyToOne(targetEntity = OrderImpl.class)
	@JoinColumn(name = "order_id", nullable = false)
	private Order order;

	@Enumerated(EnumType.STRING)
	@Column(name = "order_total_module", nullable = false)
	private OrderTotalModule module;

	@Column(name = "title")
	private String title;

	@Column(name = "text")
	private String text;

	@Column(name = "value", nullable = false)
	private BigDecimal value;

	@Override
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
	public OrderTotalModule getModule() {
		return module;
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public String getText() {
		return text;
	}

	@Override
	public BigDecimal getValue() {
		return value;
	}

	@Override
	public void setModule(OrderTotalModule module) {
		this.module = module;
	}

	@Override
	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public void setText(String text) {
		this.text = text;
	}

	@Override
	public void setValue(BigDecimal value) {
		this.value = value;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(super.hashCode(), id);
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof OrderTotalImpl) {
			OrderTotalImpl that = (OrderTotalImpl) object;
			return Objects.equal(this.id, that.id);
		}
		return false;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OrderTotalImpl [id=");
		builder.append(id);
		builder.append(", order=");
		builder.append(order);
		builder.append(", module=");
		builder.append(module);
		builder.append(", title=");
		builder.append(title);
		builder.append(", text=");
		builder.append(text);
		builder.append(", value=");
		builder.append(value);
		builder.append("]");
		return builder.toString();
	}

}
