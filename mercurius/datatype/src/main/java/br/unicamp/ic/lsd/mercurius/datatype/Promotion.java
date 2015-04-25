package br.unicamp.ic.lsd.mercurius.datatype;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public interface Promotion extends Serializable {

	/**
	 * Returns the {@link Promotion} id.
	 * 
	 * @return
	 */
	Integer getId();

	/**
	 * Returns the date that the promotion was created.
	 * 
	 * @return
	 */
	Date getDateAdded();

	/**
	 * Sets the date that the promotion was created.
	 * 
	 * @param date
	 */
	void setDateAdded(Date date);

	/**
	 * Returns the date that the promotion will start to be available.
	 * 
	 * @return
	 */
	Date getStartDate();

	/**
	 * Sets the date that the promotion will start to be available.
	 * 
	 * @param date
	 */
	void setStartDate(Date date);

	/**
	 * Returns the that the promotion will end.
	 * 
	 * @return
	 */
	Date getEndDate();

	/**
	 * Sets the that the promotion will end.
	 * 
	 * @param date
	 */
	void setEndDate(Date date);

	/**
	 * Returns the date that the promotion was last modified.
	 * 
	 * @return
	 */
	Date getDateModified();

	/**
	 * Sets the date that the promotion was last modified.
	 * 
	 * @param date
	 */
	void setDateModified(Date date);

	/**
	 * Returns the promotion's name.
	 * 
	 * @return
	 */
	String getName();

	/**
	 * Sets the promotion's name.
	 * 
	 * @param name
	 */
	void setName(String name);

	/**
	 * Returns the promotion's description.
	 * 
	 * @return
	 */
	String getDescription();

	/**
	 * Sets the promotion's description.
	 * 
	 * @param description
	 */
	void setDescription(String description);

	/**
	 * Returns <code>true</code> if this promotion can be given with other
	 * promotions.
	 * 
	 * @return
	 */
	boolean isCumulative();

	/**
	 * Sets if this promotion can be given with other promotions.
	 * 
	 * @param cumulative
	 */
	void setCumulative(boolean cumulative);

	/**
	 * Returns <code>true</code> if this promotion is percent.
	 */
	boolean isPercent();

	/**
	 * Sets if this promotion is percent.
	 * 
	 * @param cumulative
	 */
	void setPercent(boolean percent);

	/**
	 * Returns the discount given by this promotion.
	 * 
	 * @return
	 */
	BigDecimal getDiscountValue();

	/**
	 * Sets the discount given by this promotion.
	 */
	void setDiscountValue(BigDecimal discountValue);

}
