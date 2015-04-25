package br.unicamp.ic.lsd.mercurius.persistence.entities;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.unicamp.ic.lsd.mercurius.datatype.Order;
import br.unicamp.ic.lsd.mercurius.datatype.OrderProduct;
import br.unicamp.ic.lsd.mercurius.datatype.Product;
import br.unicamp.ic.lsd.mercurius.datatype.ProductAttribute;

import com.google.common.base.Objects;

@Entity(name = "OrderProduct")
@Table(name = "order_product")
public class OrderProductImpl implements OrderProduct {

	private static final long serialVersionUID = 572860680809991732L;

	protected OrderProductImpl() {
		super();
	}

	@EmbeddedId
	private OrderProductId id;

	@ManyToOne(targetEntity = OrderImpl.class)
	@JoinColumn(name = "order_id", nullable = false, insertable = false, updatable = false)
	private Order order;

	@ManyToOne(targetEntity = ProductImpl.class)
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;

	@ManyToMany(targetEntity = ProductAttributeImpl.class)
	@JoinTable(name = "order_product_to_product_attribute", joinColumns = {
			@JoinColumn(name = "order_id", referencedColumnName = "order_id"),
			@JoinColumn(name = "sku", referencedColumnName = "sku") }, inverseJoinColumns = @JoinColumn(
			name = "product_attribute_id", referencedColumnName = "product_attribute_id"))
	private List<ProductAttribute> productAttributes;

	@Column(name = "price", nullable = false)
	private BigDecimal price;

	@Column(name = "special_price")
	private BigDecimal specialPrice;

	@Column(name = "quantity", nullable = false)
	private Integer quantity;

	@Override
	public String getSku() {
		return id.getSku();
	}

	public OrderProductId getId() {
		return id;
	}

	@Override
	public BigDecimal getPrice() {
		return price;
	}

	@Override
	public BigDecimal getSpecialPrice() {
		return specialPrice;
	}

	@Override
	public Integer getQuantity() {
		return quantity;
	}

	public void setId(OrderProductId id) {
		this.id = id;
	}

	@Override
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Override
	public void setSpecialPrice(BigDecimal specialPrice) {
		this.specialPrice = specialPrice;
	}

	@Override
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public Order getOrder() {
		return order;
	}

	@Override
	public void setOrder(Order order) {
		this.id.setOrderId(order.getId());
		this.order = order;
	}

	@Override
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public List<ProductAttribute> getProductAttributes() {
		return productAttributes;
	}

	public void setProductAttributes(List<ProductAttribute> productAttributes) {
		this.productAttributes = productAttributes;
	}

	@Override
	public BigDecimal getTotalPrice() {
		BigDecimal finalPrice = price;
		if (specialPrice != null) {
			finalPrice = specialPrice;
		}
		if (finalPrice != null && quantity != null) {
			return finalPrice.multiply(new BigDecimal(quantity));
		}
		return BigDecimal.ZERO;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(super.hashCode(), id);
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof OrderProductImpl) {
			OrderProductImpl that = (OrderProductImpl) object;
			return Objects.equal(this.id, that.id);
		}
		return false;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OrderProductImpl [id=");
		builder.append(id);
		builder.append(", order=");
		builder.append(order);
		builder.append(", product=");
		builder.append(product);
		builder.append(", price=");
		builder.append(price);
		builder.append(", specialPrice=");
		builder.append(specialPrice);
		builder.append(", quantity=");
		builder.append(quantity);
		builder.append("]");
		return builder.toString();
	}

}
