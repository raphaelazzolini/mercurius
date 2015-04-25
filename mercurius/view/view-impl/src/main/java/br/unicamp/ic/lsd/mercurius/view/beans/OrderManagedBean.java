package br.unicamp.ic.lsd.mercurius.view.beans;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.inject.Named;

import br.unicamp.ic.lsd.mercurius.datatype.Address;
import br.unicamp.ic.lsd.mercurius.datatype.Customer;
import br.unicamp.ic.lsd.mercurius.datatype.Order;
import br.unicamp.ic.lsd.mercurius.datatype.OrderTotal;
import br.unicamp.ic.lsd.mercurius.datatype.OrderTotalModule;
import br.unicamp.ic.lsd.mercurius.datatype.PaymentMethod;
import br.unicamp.ic.lsd.mercurius.excpetionhandler.exceptions.OutOfStockException;
import br.unicamp.ic.lsd.mercurius.excpetionhandler.exceptions.ProductNotFoundException;
import br.unicamp.ic.lsd.mercurius.view.spec.entities.CreditCardInformation;
import br.unicamp.ic.lsd.mercurius.view.spec.req.ViewOrderMgt;
import br.unicamp.ic.lsd.mercurius.vieworderconnector.ViewOrderConnectorFactory;
import br.unicamp.ic.sed.cosmos.IManager;

@Named("orderManagedBean")
@SessionScoped
public class OrderManagedBean implements Serializable {

	private static final long serialVersionUID = 2886934275296839521L;

	private IManager viewOrderConnector;
	private ViewOrderMgt orderMgt;
	private CreditCardInformation creditCardInformation;
	private PaymentMethod paymentMethod;

	@Inject
	private Instance<BasketManagedBean> basketManagedBean;

	@Inject
	private Instance<CustomerManagedBean> customerManagedBean;

	private Order order;
	private Customer customer;
	private Address address;

	@PostConstruct
	public void init() {
		viewOrderConnector = ViewOrderConnectorFactory.createInstance();
		orderMgt = (ViewOrderMgt) viewOrderConnector.getProvidedInterface("ViewOrderMgt");
		creditCardInformation = orderMgt.getCreditCardInformationInstance();
	}

	public void createOrder() {
		order = orderMgt.createOrder(basketManagedBean.get().getBasket());
		customer = customerManagedBean.get().getCustomer();
		address = customer.getDefaultAddress();
		paymentMethod = orderMgt.getPaymentMethods(order).get(0);
	}

	public String getNumberOfItems() {
		int numberOfItems = order.getOrderProducts().size();
		StringBuilder returnBuilder = new StringBuilder(numberOfItems);
		if (numberOfItems > 1) {
			returnBuilder.append(" itens");
		} else {
			returnBuilder.append(" item");
		}
		return returnBuilder.toString();
	}

	public BigDecimal getProductsTotal() {
		for (OrderTotal orderTotal : order.getOrderTotals()) {
			if (orderTotal.getModule().equals(OrderTotalModule.PRODUCTS_TOTAL)) {
				return orderTotal.getValue();
			}
		}
		return BigDecimal.ZERO;
	}

	public BigDecimal getShippingTotal() {
		for (OrderTotal orderTotal : order.getOrderTotals()) {
			if (orderTotal.getModule().equals(OrderTotalModule.SHIPPING_TOTAL)) {
				return orderTotal.getValue();
			}
		}
		return BigDecimal.ZERO;
	}

	public BigDecimal getTotalDiscount() {
		BigDecimal discounts = BigDecimal.ZERO;
		for (OrderTotal orderTotal : order.getOrderTotals()) {
			if (orderTotal.getModule().equals(OrderTotalModule.DISCOUNT)) {
				discounts = discounts.add(orderTotal.getValue());
			}
		}
		return discounts;
	}

	public BigDecimal getSubTotal() {
		return getTotalDiscount().add(getShippingTotal()).add(getProductsTotal());
	}

	public BigDecimal getTotal() {
		for (OrderTotal orderTotal : order.getOrderTotals()) {
			if (orderTotal.getModule().equals(OrderTotalModule.ORDER_TOTAL)) {
				return orderTotal.getValue();
			}
		}
		return BigDecimal.ZERO;
	}

	public String finalizarPedido() throws ProductNotFoundException, OutOfStockException {
		setOrderAddresses();
		order.setCustomer(customer);
		order.setShippingService("Convencional");
		order = orderMgt.saveOrder(order, paymentMethod, creditCardInformation);
		if (order.getId() != null) {
			return "confirmacao_pedido";
		}
		return null;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public CreditCardInformation getCreditCardInformation() {
		return creditCardInformation;
	}

	public void setCreditCardInformation(CreditCardInformation creditCardInformation) {
		this.creditCardInformation = creditCardInformation;
	}

	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	private void setOrderAddresses() {
		order.setBillingAddress1(address.getStreetAddress1());
		order.setBillingAddress2(address.getStreetAddress1());
		order.setBillingCity(address.getCity());
		order.setBillingName(address.getName());
		order.setBillingPostCode(address.getPostCode());
		order.setBillingState(address.getState());
		order.setBillingSuburb(address.getSuburb());
		order.setBillingTelephone(address.getTelephoneNumber());

		order.setCustomerAddress1(address.getStreetAddress1());
		order.setCustomerAddress2(address.getStreetAddress1());
		order.setCustomerCity(address.getCity());
		order.setCustomerName(address.getName());
		order.setCustomerPostCode(address.getPostCode());
		order.setCustomerState(address.getState());
		order.setCustomerSuburb(address.getSuburb());
		order.setCustomerTelephone(address.getTelephoneNumber());

		order.setDeliveryAddress1(address.getStreetAddress1());
		order.setDeliveryAddress2(address.getStreetAddress1());
		order.setDeliveryCity(address.getCity());
		order.setDeliveryName(address.getName());
		order.setDeliveryPostCode(address.getPostCode());
		order.setDeliveryState(address.getState());
		order.setDeliverySuburb(address.getSuburb());
		order.setDeliveryTelephone(address.getTelephoneNumber());
	}

}
