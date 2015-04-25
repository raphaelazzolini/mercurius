package br.unicamp.ic.lsd.mercurius.persistence.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.unicamp.ic.lsd.mercurius.datatype.Order;
import br.unicamp.ic.lsd.mercurius.datatype.OrderStatus;
import br.unicamp.ic.lsd.mercurius.datatype.OrderStatusHistory;

import com.google.common.base.Objects;

@Entity(name = "OrderStatusHistory")
@Table(name = "order_status_history")
public class OrderStatusHistoryImpl implements OrderStatusHistory {

	private static final long serialVersionUID = 5108655122721588387L;

	protected OrderStatusHistoryImpl() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "order_status_history_id", nullable = false)
	private Integer id;

	@ManyToOne(targetEntity = OrderImpl.class)
	@JoinColumn(name = "order_id", nullable = false)
	private Order order;

	@ManyToOne(targetEntity = OrderStatusImpl.class)
	@JoinColumn(name = "order_status_id", nullable = false)
	private OrderStatus orderStatus;

	@Column(name = "date_added", nullable = false)
	private Date dateAdded;

	@Column(name = "comments")
	private String comments;

	@Column(name = "customer_notified", nullable = false)
	private Boolean customerNotified;

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public Date getDateAdded() {
		return dateAdded;
	}

	@Override
	public String getComments() {
		return comments;
	}

	@Override
	public boolean isCustomerNotified() {
		return customerNotified;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	@Override
	public void setComments(String comments) {
		this.comments = comments;
	}

	@Override
	public void setCustomerNotified(boolean customerNotified) {
		this.customerNotified = customerNotified;
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
	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	@Override
	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Boolean getCustomerNotified() {
		return customerNotified;
	}

	public void setCustomerNotified(Boolean customerNotified) {
		this.customerNotified = customerNotified;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(super.hashCode(), id);
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof OrderStatusHistoryImpl) {
			OrderStatusHistoryImpl that = (OrderStatusHistoryImpl) object;
			return Objects.equal(this.id, that.id);
		}
		return false;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OrderStatusHistoryImpl [id=");
		builder.append(id);
		builder.append(", order=");
		builder.append(order);
		builder.append(", orderStatus=");
		builder.append(orderStatus);
		builder.append(", dateAdded=");
		builder.append(dateAdded);
		builder.append(", comments=");
		builder.append(comments);
		builder.append(", customerNotified=");
		builder.append(customerNotified);
		builder.append("]");
		return builder.toString();
	}

}
