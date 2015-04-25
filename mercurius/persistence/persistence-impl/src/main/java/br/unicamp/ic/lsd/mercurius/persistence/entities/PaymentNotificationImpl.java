package br.unicamp.ic.lsd.mercurius.persistence.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.unicamp.ic.lsd.mercurius.datatype.Order;
import br.unicamp.ic.lsd.mercurius.datatype.PaymentNotification;
import br.unicamp.ic.lsd.mercurius.datatype.PaymentStatus;

@Entity(name = "PaymentNotification")
@Table(name = "payment_notification")
public class PaymentNotificationImpl implements PaymentNotification {

	private static final long serialVersionUID = 1957841255070797272L;

	protected PaymentNotificationImpl() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "payment_notification_id")
	private Integer id;

	@ManyToOne(targetEntity = OrderImpl.class)
	@JoinColumn(name = "order_id", nullable = false)
	private Order order;

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
	public PaymentStatus getPaymentStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPaymentStatus(PaymentStatus status) {
		// TODO Auto-generated method stub

	}

}
