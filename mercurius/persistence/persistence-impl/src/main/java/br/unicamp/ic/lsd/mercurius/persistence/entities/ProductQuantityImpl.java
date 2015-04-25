package br.unicamp.ic.lsd.mercurius.persistence.entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.unicamp.ic.lsd.mercurius.datatype.Product;
import br.unicamp.ic.lsd.mercurius.datatype.ProductAttribute;
import br.unicamp.ic.lsd.mercurius.datatype.ProductImage;
import br.unicamp.ic.lsd.mercurius.datatype.ProductQuantity;

import com.google.common.base.Objects;

@Entity(name = "ProductQuantity")
@Table(name = "product_quantity")
public class ProductQuantityImpl implements ProductQuantity {

	private static final long serialVersionUID = -8442333410908653267L;

	protected ProductQuantityImpl() {
		super();
	}

	@Id
	@Column(name = "sku")
	private String sku;

	@ManyToOne(targetEntity = ProductImpl.class)
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;

	@Column(name = "quantity", nullable = false)
	private Integer quantity;

	@ManyToMany(targetEntity = ProductAttributeImpl.class)
	@JoinTable(name = "product_quantity_to_product_attribute", joinColumns = @JoinColumn(name = "sku",
			referencedColumnName = "sku"), inverseJoinColumns = @JoinColumn(name = "product_attribute_id",
			referencedColumnName = "product_attribute_id"))
	private List<ProductAttribute> productsAttributes;

	@OneToMany(mappedBy = "productQuantity", cascade = CascadeType.ALL, targetEntity = ProductImageImpl.class)
	private List<ProductImage> productImages;

	@Transient
	private BigDecimal itemPrice;

	@Override
	public String getSku() {
		return sku;
	}

	@Override
	public void setSku(String sku) {
		this.sku = sku;
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
	public Product getProduct() {
		return product;
	}

	@Override
	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public List<ProductAttribute> getProductsAttributes() {
		return productsAttributes;
	}

	@Override
	public void setProductsAttributes(List<ProductAttribute> productsAttributes) {
		this.productsAttributes = productsAttributes;
	}

	@Override
	public List<ProductImage> getProductImages() {
		return productImages;
	}

	public void setProductImages(List<ProductImage> productImages) {
		this.productImages = productImages;
	}

	@Override
	public BigDecimal getItemPrice() {
		if (itemPrice == null) {
			itemPrice = product.getPrice();
			for (ProductAttribute attribute : productsAttributes) {
				if (attribute.getValue() != null) {
					itemPrice = itemPrice.add(attribute.getValue());
				}
			}
		}
		return itemPrice;
	}

	public void setItemPrice(BigDecimal itemPrice) {
		this.itemPrice = itemPrice;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(super.hashCode(), sku);
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof ProductQuantityImpl) {
			ProductQuantityImpl that = (ProductQuantityImpl) object;
			return Objects.equal(this.sku, that.sku);
		}
		return false;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ProductQuantityImpl [sku=");
		builder.append(sku);
		builder.append(", product=");
		builder.append(product);
		builder.append(", quantity=");
		builder.append(quantity);
		builder.append(", productsAttributes=");
		builder.append(productsAttributes);
		builder.append(", productImages=");
		builder.append(productImages);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public List<ProductImage> getProductSmallImages() {
		List<ProductImage> smallImages = new ArrayList<>();
		for (ProductImage image : productImages) {
			if (image.getImageType().equals("SMALL")) {
				smallImages.add(image);
			}
		}
		return smallImages;
	}

	@Override
	public List<ProductImage> getProductMediumImages() {
		List<ProductImage> smallImages = new ArrayList<>();
		for (ProductImage image : productImages) {
			if (image.getImageType().equals("MEDIUM")) {
				smallImages.add(image);
			}
		}
		return smallImages;
	}

}
