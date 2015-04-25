package br.unicamp.ic.lsd.mercurius.persistence.entities;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.unicamp.ic.lsd.mercurius.datatype.Customer;
import br.unicamp.ic.lsd.mercurius.datatype.Order;
import br.unicamp.ic.lsd.mercurius.datatype.OrderProduct;
import br.unicamp.ic.lsd.mercurius.datatype.OrderStatus;
import br.unicamp.ic.lsd.mercurius.datatype.OrderStatusHistory;
import br.unicamp.ic.lsd.mercurius.datatype.OrderTotal;
import br.unicamp.ic.lsd.mercurius.datatype.PaymentNotification;

import com.google.common.base.Objects;

@Entity(name = "Orders")
@Table(name = "orders")
public class OrderImpl implements Order {

	private static final long serialVersionUID = 629024091837136619L;

	protected OrderImpl() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "order_id", nullable = false)
	private Integer id;

	@ManyToOne(targetEntity = CustomerImpl.class)
	@JoinColumn(name = "customer_id", nullable = false)
	private Customer customer;

	@Column(name = "billing_name")
	private String billingName;

	@Column(name = "billing_city")
	private String billingCity;

	@Column(name = "billing_state")
	private String billingState;

	@Column(name = "billing_suburb")
	private String billingSuburb;

	@Column(name = "billing_post_code")
	private String billingPostCode;

	@Column(name = "billing_address1")
	private String billingAddress1;

	@Column(name = "billing_address2")
	private String billingAddress2;

	@Column(name = "billing_telephone")
	private String billingTelephone;

	@Column(name = "customer_name")
	private String customerName;

	@Column(name = "customer_city")
	private String customerCity;

	@Column(name = "customer_state")
	private String customerState;

	@Column(name = "customer_suburb")
	private String customerSuburb;

	@Column(name = "customer_post_code")
	private String customerPostCode;

	@Column(name = "customer_address1")
	private String customerAddress1;

	@Column(name = "customer_address2")
	private String customerAddress2;

	@Column(name = "customer_telephone")
	private String customerTelephone;

	@Column(name = "delivery_name", nullable = false)
	private String deliveryName;

	@Column(name = "delivery_city", nullable = false)
	private String deliveryCity;

	@Column(name = "delivery_state", nullable = false)
	private String deliveryState;

	@Column(name = "delivery_suburb", nullable = false)
	private String deliverySuburb;

	@Column(name = "delivery_post_code", nullable = false)
	private String deliveryPostCode;

	@Column(name = "delivery_address1", nullable = false)
	private String deliveryAddress1;

	@Column(name = "delivery_address2")
	private String deliveryAddress2;

	@Column(name = "delivery_telephone", nullable = false)
	private String deliveryTelephone;

	@Column(name = "date_purchased", nullable = false)
	private Date datePurchased;

	@Column(name = "date_finished")
	private Date dateFinished;

	@Column(name = "date_modified", nullable = false)
	private Date dateModified;

	@ManyToOne(targetEntity = OrderStatusImpl.class)
	@JoinColumn(name = "order_status_id", nullable = false)
	private OrderStatus orderStatus;

	@Column(name = "shipping_service", nullable = false)
	private String shippingService;

	@Column(name = "payment_method", nullable = false)
	private String paymentMethod;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, targetEntity = OrderTotalImpl.class)
	private List<OrderTotal> orderTotals;

	@Transient
	private BigDecimal orderTotal;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, targetEntity = OrderProductImpl.class)
	private Set<OrderProduct> orderProducts;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, targetEntity = OrderStatusHistoryImpl.class)
	private List<OrderStatusHistory> orderStatusHistories;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, targetEntity = PaymentNotificationImpl.class)
	private List<PaymentNotification> paymentNotifications;

	@Column(name = "tracking_number")
	private String trackingNumber;

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public Customer getCustomer() {
		return customer;
	}

	@Override
	public String getBillingName() {
		return billingName;
	}

	@Override
	public String getBillingCity() {
		return billingCity;
	}

	@Override
	public String getBillingState() {
		return billingState;
	}

	@Override
	public String getBillingSuburb() {
		return billingSuburb;
	}

	@Override
	public String getBillingPostCode() {
		return billingPostCode;
	}

	@Override
	public String getBillingAddress1() {
		return billingAddress1;
	}

	@Override
	public String getBillingAddress2() {
		return billingAddress2;
	}

	@Override
	public String getBillingTelephone() {
		return billingTelephone;
	}

	@Override
	public String getCustomerName() {
		return customerName;
	}

	@Override
	public String getCustomerCity() {
		return customerCity;
	}

	@Override
	public String getCustomerState() {
		return customerState;
	}

	@Override
	public String getCustomerSuburb() {
		return customerSuburb;
	}

	@Override
	public String getCustomerPostCode() {
		return customerPostCode;
	}

	@Override
	public String getCustomerAddress1() {
		return customerAddress1;
	}

	@Override
	public String getCustomerAddress2() {
		return customerAddress2;
	}

	@Override
	public String getCustomerTelephone() {
		return customerTelephone;
	}

	@Override
	public String getDeliveryName() {
		return deliveryName;
	}

	@Override
	public String getDeliveryCity() {
		return deliveryCity;
	}

	@Override
	public String getDeliveryState() {
		return deliveryState;
	}

	@Override
	public String getDeliverySuburb() {
		return deliverySuburb;
	}

	@Override
	public String getDeliveryPostCode() {
		return deliveryPostCode;
	}

	@Override
	public String getDeliveryAddress1() {
		return deliveryAddress1;
	}

	@Override
	public String getDeliveryAddress2() {
		return deliveryAddress2;
	}

	@Override
	public String getDeliveryTelephone() {
		return deliveryTelephone;
	}

	@Override
	public Date getDatePurchased() {
		return datePurchased;
	}

	@Override
	public Date getDateFinished() {
		return dateFinished;
	}

	@Override
	public Date getDateModified() {
		return dateModified;
	}

	@Override
	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	@Override
	public String getShippingService() {
		return shippingService;
	}

	@Override
	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public void setBillingName(String billingName) {
		this.billingName = billingName;
	}

	@Override
	public void setBillingCity(String billingCity) {
		this.billingCity = billingCity;
	}

	@Override
	public void setBillingState(String billingState) {
		this.billingState = billingState;
	}

	@Override
	public void setBillingSuburb(String billingSuburb) {
		this.billingSuburb = billingSuburb;
	}

	@Override
	public void setBillingPostCode(String billingPostCode) {
		this.billingPostCode = billingPostCode;
	}

	@Override
	public void setBillingAddress1(String billingAddress1) {
		this.billingAddress1 = billingAddress1;
	}

	@Override
	public void setBillingAddress2(String billingAddress2) {
		this.billingAddress2 = billingAddress2;
	}

	@Override
	public void setBillingTelephone(String billingTelephone) {
		this.billingTelephone = billingTelephone;
	}

	@Override
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	@Override
	public void setCustomerCity(String customerCity) {
		this.customerCity = customerCity;
	}

	@Override
	public void setCustomerState(String customerState) {
		this.customerState = customerState;
	}

	@Override
	public void setCustomerSuburb(String customerSuburb) {
		this.customerSuburb = customerSuburb;
	}

	@Override
	public void setCustomerPostCode(String customerPostCode) {
		this.customerPostCode = customerPostCode;
	}

	@Override
	public void setCustomerAddress1(String customerAddress1) {
		this.customerAddress1 = customerAddress1;
	}

	@Override
	public void setCustomerAddress2(String customerAddress2) {
		this.customerAddress2 = customerAddress2;
	}

	@Override
	public void setCustomerTelephone(String customerTelephone) {
		this.customerTelephone = customerTelephone;
	}

	@Override
	public void setDeliveryName(String deliveryName) {
		this.deliveryName = deliveryName;
	}

	@Override
	public void setDeliveryCity(String deliveryCity) {
		this.deliveryCity = deliveryCity;
	}

	@Override
	public void setDeliveryState(String deliveryState) {
		this.deliveryState = deliveryState;
	}

	@Override
	public void setDeliverySuburb(String deliverySuburb) {
		this.deliverySuburb = deliverySuburb;
	}

	@Override
	public void setDeliveryPostCode(String deliveryPostCode) {
		this.deliveryPostCode = deliveryPostCode;
	}

	@Override
	public void setDeliveryAddress1(String deliveryAddress1) {
		this.deliveryAddress1 = deliveryAddress1;
	}

	@Override
	public void setDeliveryAddress2(String deliveryAddress2) {
		this.deliveryAddress2 = deliveryAddress2;
	}

	@Override
	public void setDeliveryTelephone(String deliveryTelephone) {
		this.deliveryTelephone = deliveryTelephone;
	}

	@Override
	public void setDatePurchased(Date datePurchased) {
		this.datePurchased = datePurchased;
	}

	@Override
	public void setDateFinished(Date dateFinished) {
		this.dateFinished = dateFinished;
	}

	@Override
	public String getTrackingNumber() {
		return trackingNumber;
	}

	@Override
	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}

	@Override
	public List<OrderTotal> getOrderTotals() {
		return orderTotals;
	}

	@Override
	public void setOrderTotals(List<OrderTotal> orderTotals) {
		this.orderTotals = orderTotals;
	}

	@Override
	public BigDecimal getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(BigDecimal orderTotal) {
		this.orderTotal = orderTotal;
	}

	@Override
	public Set<OrderProduct> getOrderProducts() {
		return orderProducts;
	}

	@Override
	public void setOrderProducts(Set<OrderProduct> orderProducts) {
		this.orderProducts = orderProducts;
	}

	@Override
	public List<OrderStatusHistory> getOrderStatusHistories() {
		return orderStatusHistories;
	}

	@Override
	public void setOrderStatusHistories(List<OrderStatusHistory> orderStatusHistories) {
		this.orderStatusHistories = orderStatusHistories;
	}

	@Override
	public List<PaymentNotification> getPaymentNotifications() {
		return paymentNotifications;
	}

	public void setPaymentNotifications(List<PaymentNotification> paymentNotifications) {
		this.paymentNotifications = paymentNotifications;
	}

	@Override
	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}

	@Override
	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	@Override
	public void setShippingService(String shippingService) {
		this.shippingService = shippingService;
	}

	@Override
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(super.hashCode(), id);
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof OrderImpl) {
			OrderImpl that = (OrderImpl) object;
			return Objects.equal(this.id, that.id);
		}
		return false;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OrderImpl [id=");
		builder.append(id);
		builder.append(", customer=");
		builder.append(customer);
		builder.append(", billingName=");
		builder.append(billingName);
		builder.append(", billingCity=");
		builder.append(billingCity);
		builder.append(", billingState=");
		builder.append(billingState);
		builder.append(", billingSuburb=");
		builder.append(billingSuburb);
		builder.append(", billingPostCode=");
		builder.append(billingPostCode);
		builder.append(", billingAddress1=");
		builder.append(billingAddress1);
		builder.append(", billingAddress2=");
		builder.append(billingAddress2);
		builder.append(", billingTelephone=");
		builder.append(billingTelephone);
		builder.append(", customerName=");
		builder.append(customerName);
		builder.append(", customerCity=");
		builder.append(customerCity);
		builder.append(", customerState=");
		builder.append(customerState);
		builder.append(", customerSuburb=");
		builder.append(customerSuburb);
		builder.append(", customerPostCode=");
		builder.append(customerPostCode);
		builder.append(", customerAddress1=");
		builder.append(customerAddress1);
		builder.append(", customerAddress2=");
		builder.append(customerAddress2);
		builder.append(", customerTelephone=");
		builder.append(customerTelephone);
		builder.append(", deliveryName=");
		builder.append(deliveryName);
		builder.append(", deliveryCity=");
		builder.append(deliveryCity);
		builder.append(", deliveryState=");
		builder.append(deliveryState);
		builder.append(", deliverySuburb=");
		builder.append(deliverySuburb);
		builder.append(", deliveryPostCode=");
		builder.append(deliveryPostCode);
		builder.append(", deliveryAddress1=");
		builder.append(deliveryAddress1);
		builder.append(", deliveryAddress2=");
		builder.append(deliveryAddress2);
		builder.append(", deliveryTelephone=");
		builder.append(deliveryTelephone);
		builder.append(", datePurchased=");
		builder.append(datePurchased);
		builder.append(", dateFinished=");
		builder.append(dateFinished);
		builder.append(", dateModified=");
		builder.append(dateModified);
		builder.append(", orderStatus=");
		builder.append(orderStatus);
		builder.append(", shippingService=");
		builder.append(shippingService);
		builder.append(", paymentMethod=");
		builder.append(paymentMethod);
		builder.append(", orderTotal=");
		builder.append(orderTotal);
		builder.append("]");
		return builder.toString();
	}

}
