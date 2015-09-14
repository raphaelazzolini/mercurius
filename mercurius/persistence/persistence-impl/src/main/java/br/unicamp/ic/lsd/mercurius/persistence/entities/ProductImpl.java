package br.unicamp.ic.lsd.mercurius.persistence.entities;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.analysis.ASCIIFoldingFilterFactory;
import org.apache.solr.analysis.LowerCaseFilterFactory;
import org.apache.solr.analysis.StandardTokenizerFactory;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.AnalyzerDef;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Store;
import org.hibernate.search.annotations.TokenFilterDef;
import org.hibernate.search.annotations.TokenizerDef;

import br.unicamp.ic.lsd.mercurius.datatype.Category;
import br.unicamp.ic.lsd.mercurius.datatype.Manufacturer;
import br.unicamp.ic.lsd.mercurius.datatype.Product;
import br.unicamp.ic.lsd.mercurius.datatype.ProductImage;
import br.unicamp.ic.lsd.mercurius.datatype.ProductQuantity;

import com.google.common.base.Objects;

@Entity(name = "Product")
@Indexed
@AnalyzerDef(name = "productalyzer", tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class), filters = {
		@TokenFilterDef(factory = LowerCaseFilterFactory.class),
		@TokenFilterDef(factory = ASCIIFoldingFilterFactory.class) })
@Table(name = "product")
public class ProductImpl implements Product {

	private static final long serialVersionUID = 7054477156104881832L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "product_id")
	private Integer id;

	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
	@Analyzer(definition = "productalyzer")
	@Column(name = "product_name", nullable = false)
	private String name;

	@Column(name = "date_added", nullable = false)
	private Date dateAdded;

	@Column(name = "date_available", nullable = false)
	private Date dateAvailable;

	@Column(name = "expiry_date")
	private Date expiryDate;

	@Column(name = "price", nullable = false)
	private BigDecimal price;

	@Column(name = "weight", nullable = false)
	private BigDecimal weight;

	@Column(name = "width", nullable = false)
	private BigDecimal width;

	@Column(name = "height", nullable = false)
	private BigDecimal height;

	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
	@Analyzer(definition = "productalyzer")
	@Column(name = "details", nullable = false)
	private String details;

	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
	@Analyzer(definition = "productalyzer")
	@Column(name = "technical_details")
	private String technicalDetails;

	@Column(name = "free_gift", nullable = false)
	private boolean freeGift;

	@IndexedEmbedded
	@ManyToMany(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH },
			targetEntity = CategoryImpl.class)
	@JoinTable(name = "product_to_category", joinColumns = @JoinColumn(name = "product_id",
			referencedColumnName = "product_id"), inverseJoinColumns = @JoinColumn(name = "category_id",
			referencedColumnName = "category_id"))
	private List<Category> categories;

	@IndexedEmbedded
	@ManyToOne(targetEntity = ManufacturerImpl.class)
	@JoinColumn(name = "manufacturer_id", nullable = false)
	private Manufacturer manufacturer;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "product", targetEntity = ProductQuantityImpl.class)
	private List<ProductQuantity> quantities = new ArrayList<>();

	@Transient
	private String mainImage;

	@Transient
	private BigDecimal specialPrice;

	@Transient
	private String priceFormatted;

	@Transient
	private String specialPriceFormatted;

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
	public Date getDateAdded() {
		return dateAdded;
	}

	@Override
	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	@Override
	public Date getDateAvailable() {
		return dateAvailable;
	}

	@Override
	public void setDateAvailable(Date dateAvailable) {
		this.dateAvailable = dateAvailable;
	}

	@Override
	public Date getExpiryDate() {
		return expiryDate;
	}

	@Override
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	@Override
	public BigDecimal getPrice() {
		return price;
	}

	@Override
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Override
	public BigDecimal getWeight() {
		return weight;
	}

	@Override
	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	@Override
	public BigDecimal getWidth() {
		return width;
	}

	@Override
	public void setWidth(BigDecimal width) {
		this.width = width;
	}

	@Override
	public BigDecimal getHeight() {
		return height;
	}

	@Override
	public void setHeight(BigDecimal height) {
		this.height = height;
	}

	@Override
	public String getDetails() {
		return details;
	}

	@Override
	public void setDetails(String details) {
		this.details = details;
	}

	@Override
	public String getTechnicalDetails() {
		return technicalDetails;
	}

	@Override
	public void setTechnicalDetails(String technicalDetails) {
		this.technicalDetails = technicalDetails;
	}

	@Override
	public boolean isFreeGift() {
		return freeGift;
	}

	@Override
	public void setFreeGift(boolean freeGift) {
		this.freeGift = freeGift;
	}

	@Override
	public List<Category> getCategories() {
		return categories;
	}

	@Override
	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	@Override
	public Manufacturer getManufacturer() {
		return manufacturer;
	}

	@Override
	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}

	@Override
	public List<ProductQuantity> getQuantities() {
		return quantities;
	}

	@Override
	public void setQuantities(List<ProductQuantity> quantities) {
		this.quantities = quantities;
	}

	@Override
	public String getMainImage() {
		if (mainImage == null && CollectionUtils.isNotEmpty(quantities) && CollectionUtils.isNotEmpty(quantities)) {
			ProductQuantity mainQuantity = quantities.get(0);
			if (CollectionUtils.isNotEmpty(mainQuantity.getProductImages())) {
				List<ProductImage> images = mainQuantity.getProductImages();
				mainImage = images.get(0).getImagePath();
			}
		}
		return mainImage;
	}

	@Override
	public void setMainImage(String mainImage) {
		this.mainImage = mainImage;
	}

	@Override
	public BigDecimal getSpecialPrice() {
		return specialPrice;
	}

	@Override
	public void setSpecialPrice(BigDecimal specialPrice) {
		this.specialPrice = specialPrice;
	}

	@Override
	public String getPriceFormatted() {
		if (StringUtils.isBlank(priceFormatted)) {
			DecimalFormat decimalFormat = new DecimalFormat("R$ ###,###.00", new DecimalFormatSymbols(new Locale("pt",
					"BR")));
			priceFormatted = decimalFormat.format(price);
		}
		return priceFormatted;
	}

	@Override
	public String getSpecialPriceFormatted() {
		if (StringUtils.isBlank(specialPriceFormatted)) {
			DecimalFormat decimalFormat = new DecimalFormat("R$ ###,###.00", new DecimalFormatSymbols(new Locale("pt",
					"BR")));
			specialPriceFormatted = decimalFormat.format(specialPrice);
		}
		return specialPriceFormatted;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ProductImpl) {
			ProductImpl product = (ProductImpl) obj;
			return Objects.equal(product.getId(), id);
		}
		return false;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ProductImpl [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", dateAdded=");
		builder.append(dateAdded);
		builder.append(", dateAvailable=");
		builder.append(dateAvailable);
		builder.append(", expiryDate=");
		builder.append(expiryDate);
		builder.append(", price=");
		builder.append(price);
		builder.append(", weight=");
		builder.append(weight);
		builder.append(", width=");
		builder.append(width);
		builder.append(", height=");
		builder.append(height);
		builder.append(", details=");
		builder.append(details);
		builder.append(", technicalDetails=");
		builder.append(technicalDetails);
		builder.append(", freeGift=");
		builder.append(freeGift);
		builder.append(", categories=");
		builder.append(categories);
		builder.append("]");
		return builder.toString();
	}

}
