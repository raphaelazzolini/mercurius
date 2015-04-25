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

import br.unicamp.ic.lsd.mercurius.datatype.Basket;
import br.unicamp.ic.lsd.mercurius.datatype.BasketItem;
import br.unicamp.ic.lsd.mercurius.datatype.Product;
import br.unicamp.ic.lsd.mercurius.datatype.ProductAttribute;

import com.google.common.base.Objects;

@Entity(name = "BasketItem")
@Table(name = "basket_item")
public class BasketItemImpl implements BasketItem {

	private static final long serialVersionUID = -1894757317070085685L;

	protected BasketItemImpl() {
		super();
	}

	@EmbeddedId
	private BasketItemId id;

	@ManyToOne
	@JoinColumn(name = "product_id", nullable = false)
	private ProductImpl product;

	@ManyToOne
	@JoinColumn(name = "basket_id", nullable = false, insertable = false, updatable = false)
	private BasketImpl basket;

	@ManyToMany(targetEntity = ProductAttributeImpl.class)
	@JoinTable(name = "basket_item_to_product_attribute", joinColumns = {
			@JoinColumn(name = "basket_id", referencedColumnName = "basket_id"),
			@JoinColumn(name = "sku", referencedColumnName = "sku") }, inverseJoinColumns = @JoinColumn(
			name = "product_attribute_id", referencedColumnName = "product_attribute_id"))
	private List<ProductAttribute> productAttributes;

	@Column(name = "basket_item_price", nullable = false)
	private BigDecimal itemPrice;

	@Column(name = "basket_item_special_price", nullable = false)
	private BigDecimal itemSpecialPrice;

	@Column(name = "quantity", nullable = false)
	private Integer quantity;

	@Override
	public BigDecimal getTotalPrice() {
		BigDecimal price = itemPrice;
		if (itemSpecialPrice != null) {
			price = itemSpecialPrice;
		}
		if (price != null && quantity != null) {
			return price.multiply(new BigDecimal(quantity));
		}
		return BigDecimal.ZERO;
	}

	@Override
	public String getSku() {
		return id.getSku();
	}

	@Override
	public Product getProduct() {
		return product;
	}

	@Override
	public BigDecimal getItemPrice() {
		return itemPrice;
	}

	@Override
	public void setItemPrice(BigDecimal itemPrice) {
		this.itemPrice = itemPrice;
	}

	@Override
	public Integer getQuantity() {
		return quantity;
	}

	@Override
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public Basket getBasket() {
		return basket;
	}

	@Override
	public List<ProductAttribute> getProductAttributes() {
		return productAttributes;
	}

	public void setProductAttributes(List<ProductAttribute> productAttributes) {
		this.productAttributes = productAttributes;
	}

	public BasketItemId getId() {
		return id;
	}

	public void setId(BasketItemId id) {
		this.id = id;
	}

	public void setProduct(ProductImpl product) {
		this.product = product;
	}

	public void setBasket(BasketImpl basket) {
		this.basket = basket;
	}

	@Override
	public BigDecimal getItemSpecialPrice() {
		return itemSpecialPrice;
	}

	@Override
	public void setItemSpecialPrice(BigDecimal itemSpecialPrice) {
		this.itemSpecialPrice = itemSpecialPrice;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(super.hashCode(), id);
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof BasketItemImpl) {
			BasketItemImpl that = (BasketItemImpl) object;
			return Objects.equal(this.id, that.id);
		}
		return false;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BasketItemImpl [id=");
		builder.append(id);
		builder.append(", product=");
		builder.append(product);
		builder.append(", basket=");
		builder.append(basket);
		builder.append(", productAttributes=");
		builder.append(productAttributes);
		builder.append(", itemPrice=");
		builder.append(itemPrice);
		builder.append(", itemSpecialPrice=");
		builder.append(itemSpecialPrice);
		builder.append(", quantity=");
		builder.append(quantity);
		builder.append("]");
		return builder.toString();
	}
}
