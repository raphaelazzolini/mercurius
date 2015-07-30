package br.unicamp.ic.lsd.mercurius.persistence.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.unicamp.ic.lsd.mercurius.datatype.ProductAttribute;
import br.unicamp.ic.lsd.mercurius.datatype.ProductOption;

import com.google.common.base.Objects;

@Entity(name = "ProductOption")
@Table(name = "product_option")
public class ProductOptionImpl implements ProductOption {

	private static final long serialVersionUID = 6222272988011062337L;

	protected ProductOptionImpl() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "product_option_id")
	private Integer id;

	@Column(name = "product_option_name", nullable = false)
	private String name;

	@OneToMany(mappedBy = "option", targetEntity = ProductAttributeImpl.class)
	private List<ProductAttribute> attributes;

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
	public List<ProductAttribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<ProductAttribute> attributes) {
		this.attributes = attributes;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ProductOptionImpl) {
			ProductOptionImpl option = (ProductOptionImpl) obj;
			return Objects.equal(option.getId(), id);
		}
		return false;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ProductOptionImpl [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append("]");
		return builder.toString();
	}

}
