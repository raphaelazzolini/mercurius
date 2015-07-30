package br.unicamp.ic.lsd.mercurius.persistence.entities;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.unicamp.ic.lsd.mercurius.datatype.ProductAttribute;
import br.unicamp.ic.lsd.mercurius.datatype.ProductOption;
import br.unicamp.ic.lsd.mercurius.datatype.ProductQuantity;

import com.google.common.base.Objects;

@Entity(name = "ProductAttribute")
@Table(name = "product_attribute")
public class ProductAttributeImpl implements ProductAttribute {

	private static final long serialVersionUID = -2864251102114529287L;

	protected ProductAttributeImpl() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "product_attribute_id")
	private Integer id;

	@Column(name = "product_attribute_name", nullable = false)
	private String name;

	@ManyToOne(targetEntity = ProductOptionImpl.class)
	@JoinColumn(name = "product_option_id", nullable = false)
	private ProductOption option;

	@Column(name = "product_attribute_value")
	private BigDecimal value;

	@ManyToMany(targetEntity = ProductQuantityImpl.class)
	@JoinTable(name = "product_quantity_to_product_attribute", joinColumns = @JoinColumn(name = "product_attribute_id",
			referencedColumnName = "product_attribute_id"), inverseJoinColumns = @JoinColumn(name = "sku",
			referencedColumnName = "sku"))
	private List<ProductQuantity> productsQuantities;

	@Override
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public BigDecimal getValue() {
		return value;
	}

	@Override
	public void setValue(BigDecimal value) {
		this.value = value;
	}

	@Override
	public ProductOption getOption() {
		return option;
	}

	@Override
	public void setOption(ProductOption option) {
		this.option = option;
	}

	@Override
	public List<ProductQuantity> getProductsQuantities() {
		return productsQuantities;
	}

	public void setProductsQuantities(List<ProductQuantity> productsQuantities) {
		this.productsQuantities = productsQuantities;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ProductAttributeImpl) {
			ProductAttributeImpl attribute = (ProductAttributeImpl) obj;
			return Objects.equal(attribute.getId(), id);
		}
		return false;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ProductAttributeImpl [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", option=");
		builder.append(option);
		builder.append(", value=");
		builder.append(value);
		builder.append("]");
		return builder.toString();
	}

}
