package br.unicamp.ic.lsd.mercurius.productmarketingconnector;

import java.util.Collection;

import org.apache.commons.collections4.CollectionUtils;

import br.unicamp.ic.lsd.mercurius.datatype.Product;

public class MarketingAdapter {

	public MarketingAdapter() {
		super();
	}

	public Product getProductWithPromotion(Product product) {
		return product;
	}

	public Collection<Product> getRecommendedProducts(String idsList, Integer quantity) {
		return CollectionUtils.emptyCollection();
	}

}
