package br.unicamp.ic.lsd.mercurius.persistence.entities;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.unicamp.ic.lsd.mercurius.datatype.Basket;
import br.unicamp.ic.lsd.mercurius.datatype.BasketItem;
import br.unicamp.ic.lsd.mercurius.datatype.Customer;

import com.google.common.base.Objects;

@Entity(name = "Basket")
@Table(name = "basket")
public class BasketImpl implements Basket {

	private static final long serialVersionUID = -3694595442261144039L;

	protected BasketImpl() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "basket_id")
	private Integer id;

	@Column(name = "session_id", nullable = false)
	private String sessionId;

	@Column(name = "date_created", nullable = false)
	private Date dateCreated;

	@OneToMany(mappedBy = "basket", cascade = CascadeType.ALL, targetEntity = BasketItemImpl.class)
	private Set<BasketItem> basketItems;

	@OneToOne
	@JoinColumn(name = "customer_id", unique = true)
	private CustomerImpl customer;

	@Override
	public BigDecimal getBasketPrice() {
		BigDecimal basketPrice = BigDecimal.ZERO;
		for (BasketItem item : basketItems) {
			basketPrice = basketPrice.add(item.getTotalPrice());
		}
		return basketPrice;
	}

	@Override
	public Set<BasketItem> getBasketItems() {
		return basketItems;
	}

	@Override
	public void setBasketItems(Set<BasketItem> basketItems) {
		this.basketItems = basketItems;
	}

	@Override
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String getSessionId() {
		return sessionId;
	}

	@Override
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	@Override
	public Date getDateCreated() {
		return dateCreated;
	}

	@Override
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	@Override
	public Customer getCustomer() {
		return customer;
	}

	@Override
	public void setCustomer(Customer customer) {
		this.customer = (CustomerImpl) customer;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof BasketImpl) {
			BasketImpl basket = (BasketImpl) obj;
			return Objects.equal(basket.getId(), id);
		}
		return false;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BasketImpl [id=");
		builder.append(id);
		builder.append(", sessionId=");
		builder.append(sessionId);
		builder.append(", dateCreated=");
		builder.append(dateCreated);
		builder.append(", basketItems=");
		builder.append(basketItems);
		builder.append("]");
		return builder.toString();
	}

}
