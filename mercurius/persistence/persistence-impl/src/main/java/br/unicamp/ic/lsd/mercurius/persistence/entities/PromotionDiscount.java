package br.unicamp.ic.lsd.mercurius.persistence.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.unicamp.ic.lsd.mercurius.datatype.Promotion;

import com.google.common.base.Objects;

@Entity(name = "PromotionDiscount")
@Table(name = "promotion_discount")
public class PromotionDiscount implements Promotion {

	private static final long serialVersionUID = 5867068228221802749L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "promotion_id")
	private Integer id;

	@Column(name = "date_added")
	private Date dateAdded;

	@Column(name = "start_date")
	private Date startDate;

	@Column(name = "end_date")
	private Date endDate;

	@Column(name = "date_modified")
	private Date dateModified;

	@Column(name = "promotion_name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "cumulative")
	private Boolean cumulative;

	@Column(name = "percent")
	private Boolean percent;

	@Column(name = "discount_value")
	private BigDecimal discountValue;

	@Override
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
	public Date getStartDate() {
		return startDate;
	}

	@Override
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Override
	public Date getEndDate() {
		return endDate;
	}

	@Override
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Override
	public Date getDateModified() {
		return dateModified;
	}

	@Override
	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
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
	public String getDescription() {
		return description;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getCumulative() {
		return cumulative;
	}

	public void setCumulative(Boolean cumulative) {
		this.cumulative = cumulative;
	}

	public Boolean getPercent() {
		return percent;
	}

	public void setPercent(Boolean percent) {
		this.percent = percent;
	}

	@Override
	public BigDecimal getDiscountValue() {
		return discountValue;
	}

	@Override
	public void setDiscountValue(BigDecimal discountValue) {
		this.discountValue = discountValue;
	}

	@Override
	public boolean isCumulative() {
		return cumulative;
	}

	@Override
	public void setCumulative(boolean cumulative) {
		this.cumulative = cumulative;
	}

	@Override
	public boolean isPercent() {
		return percent;
	}

	@Override
	public void setPercent(boolean percent) {
		this.percent = percent;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id);
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof PromotionDiscount) {
			PromotionDiscount that = (PromotionDiscount) object;
			return Objects.equal(this.id, that.id);
		}
		return false;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PromotionDiscountImpl [id=");
		builder.append(id);
		builder.append(", dateAdded=");
		builder.append(dateAdded);
		builder.append(", startDate=");
		builder.append(startDate);
		builder.append(", endDate=");
		builder.append(endDate);
		builder.append(", dateModified=");
		builder.append(dateModified);
		builder.append(", name=");
		builder.append(name);
		builder.append(", description=");
		builder.append(description);
		builder.append(", cumulative=");
		builder.append(cumulative);
		builder.append(", percentual=");
		builder.append(percent);
		builder.append(", discountValue=");
		builder.append(discountValue);
		builder.append("]");
		return builder.toString();
	}

}
