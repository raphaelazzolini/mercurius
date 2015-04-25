package br.unicamp.ic.lsd.mercurius.persistence.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.unicamp.ic.lsd.mercurius.datatype.ProductImage;
import br.unicamp.ic.lsd.mercurius.datatype.ProductQuantity;

import com.google.common.base.Objects;

@Entity(name = "ProductImage")
@Table(name = "product_image")
public class ProductImageImpl implements ProductImage {

	private static final long serialVersionUID = -1313049162473239545L;

	protected ProductImageImpl() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "product_image_id")
	private Integer id;

	@Column(name = "image_type", nullable = false)
	private String imageType;

	@Column(name = "image_path", nullable = false)
	private String imagePath;

	@ManyToOne(targetEntity = ProductQuantityImpl.class)
	@JoinColumn(name = "sku", nullable = false)
	private ProductQuantity productQuantity;

	@Override
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String getImageType() {
		return imageType;
	}

	@Override
	public void setImageType(String imageType) {
		this.imageType = imageType;
	}

	@Override
	public String getImagePath() {
		return imagePath;
	}

	@Override
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	@Override
	public ProductQuantity getProductQuantity() {
		return productQuantity;
	}

	@Override
	public void setProductQuantity(ProductQuantity productQuantity) {
		this.productQuantity = productQuantity;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ProductImageImpl) {
			ProductImageImpl image = (ProductImageImpl) obj;
			return Objects.equal(image.getId(), id);
		}
		return false;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ProductImageImpl [id=");
		builder.append(id);
		builder.append(", imageType=");
		builder.append(imageType);
		builder.append(", imagePath=");
		builder.append(imagePath);
		builder.append(", productQuantity=");
		builder.append(productQuantity);
		builder.append("]");
		return builder.toString();
	}

}
