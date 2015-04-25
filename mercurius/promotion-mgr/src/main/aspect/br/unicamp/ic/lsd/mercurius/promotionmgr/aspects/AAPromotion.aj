package br.unicamp.ic.lsd.mercurius.promotionmgr.aspects;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import br.unicamp.ic.lsd.mercurius.datatype.OrderTotal;
import br.unicamp.ic.lsd.mercurius.datatype.Product;
import br.unicamp.ic.lsd.mercurius.datatype.Promotion;
import br.unicamp.ic.lsd.mercurius.persistence.dao.PromotionDAO;
import br.unicamp.ic.lsd.mercurius.promotionmgr.impl.PromotionMgrComponentFactory;
import br.unicamp.ic.lsd.mercurius.promotionmgr.spec.prov.PromotionManager;

public abstract aspect AAPromotion {

	private final PromotionManager manager = (PromotionManager) PromotionMgrComponentFactory.createInstance();
	private Promotion promotionDiscount;

	abstract public pointcut productDiscount(Product product);

	abstract public pointcut orderTotalDiscount(List<Product> product, OrderTotal total);

	Product around(Product product) : productDiscount(Product) && args(product) {
		return setProductSpecialPrice(product);
	}

	OrderTotal around(List<Product> products, OrderTotal totalDiscount) : orderTotalDiscount(List<Product>, OrderTotal) && args(products, totalDiscount) {
		totalDiscount = proceed(products, totalDiscount);
		BigDecimal discount = BigDecimal.ZERO;
		for (Product product : products) {
			product = setProductSpecialPrice(product);
			if (product.getPrice() != null && product.getSpecialPrice() != null) {
				BigDecimal productDiscount = product.getSpecialPrice().subtract(product.getPrice());
				if (productDiscount.compareTo(BigDecimal.ZERO) < 0) {
					discount = discount.add(productDiscount);
				}
			}
		}
		totalDiscount.setValue(discount);

		if (promotionDiscount != null) {
			totalDiscount.setText(promotionDiscount.getDescription());
			totalDiscount.setTitle(promotionDiscount.getName());
		}

		return totalDiscount;
	}

	private List<Promotion> getPromotionsDiscount() {
		PromotionDAO promotionDAO = manager.getPromotionDiscountDAO();
		List<Promotion> promotions = promotionDAO.getPromotions(null);
		List<Promotion> cumulativePromotions = new ArrayList<Promotion>();
		Promotion nonCumulativePromotion = null;
		for (Promotion promotion : promotions) {
			if (promotion.isCumulative()) {
				cumulativePromotions.add(promotion);
			} else {
				if (nonCumulativePromotion == null || (!nonCumulativePromotion.isPercent() && promotion.isPercent())
						|| nonCumulativePromotion.getDiscountValue().compareTo(promotion.getDiscountValue()) < 0) {
					nonCumulativePromotion = promotion;
				}
			}
		}
		if (CollectionUtils.isNotEmpty(cumulativePromotions)) {
			if (cumulativePromotions.size() > 1) {
				return cumulativePromotions;
			}
			Promotion cumulativePromotion = cumulativePromotions.get(0);
			if (nonCumulativePromotion == null
					|| (!nonCumulativePromotion.isPercent() && cumulativePromotion.isPercent())
					|| nonCumulativePromotion.getDiscountValue().compareTo(cumulativePromotion.getDiscountValue()) < 0) {
				return cumulativePromotions;
			}
		}
		List<Promotion> returnPromotions = new ArrayList<Promotion>();
		if (nonCumulativePromotion != null) {
			returnPromotions.add(nonCumulativePromotion);
		}
		return returnPromotions;
	}

	private Product setProductSpecialPrice(Product product) {
		List<Promotion> promotions = getPromotionsDiscount();
		BigDecimal specialPrice = product.getPrice();
		for (Promotion promotion : promotions) {
			BigDecimal discount = BigDecimal.ZERO;
			if (promotion.isPercent()) {
				discount = promotion.getDiscountValue().multiply(specialPrice);
			} else {
				discount = promotion.getDiscountValue();
			}
			if (specialPrice.subtract(discount).compareTo(BigDecimal.ZERO) > 0) {
				specialPrice = specialPrice.subtract(discount);
				promotionDiscount = promotion;
			}
		}
		if (product.getPrice().compareTo(specialPrice) > 0) {
			product.setSpecialPrice(specialPrice);
		}
		return product;
	}
}
