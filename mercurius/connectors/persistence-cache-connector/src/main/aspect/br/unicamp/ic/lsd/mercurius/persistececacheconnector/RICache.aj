package br.unicamp.ic.lsd.mercurius.persistececacheconnector;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import br.unicamp.ic.lsd.mercurius.datatype.Customer;
import br.unicamp.ic.lsd.mercurius.datatype.Product;
import br.unicamp.ic.lsd.mercurius.datatype.ProductQuantity;
import br.unicamp.ic.lsd.mercurius.datatype.Promotion;
import br.unicamp.ic.lsd.mercurius.persistence.aspects.PersistenceXPI;
import br.unicamp.ic.lsd.mercurius.persistencecacheconnector.CacheAdapter;


public aspect RICache {

	private CacheAdapter cacheAdapter = new CacheAdapter();

	List<Promotion> around(Customer customer) : PersistenceXPI.promotionCachePointcut(Customer) && args(customer) {
		String key = "promotionCache:noCustomer";
		if(customer != null && customer.getId() != null) {
			StringBuilder keyBuilder = new StringBuilder("promotionCache:");
			keyBuilder.append(customer.getId());
			key = keyBuilder.toString();
		}
		List<Promotion> promotions = (List<Promotion>) cacheAdapter.getFromCache(key, null);
		if (promotions == null) {
			promotions = proceed(customer);
			cacheAdapter.getFromCache(key, promotions);
		}
		return promotions;
	}

	ProductQuantity around(ProductQuantity quantity) : PersistenceXPI.quantityCachePointcut(ProductQuantity) && args(quantity) {
		if (quantity != null && quantity.getSku() != null) {
			StringBuilder keyBuilder = new StringBuilder("quantityCache:");
			keyBuilder.append(quantity.getSku());
			ProductQuantity cachedQuantity = (ProductQuantity) cacheAdapter.getFromCache(keyBuilder.toString(), null);
			if (cachedQuantity == null) {
				cachedQuantity = proceed(quantity);
				cacheAdapter.getFromCache(keyBuilder.toString(), cachedQuantity);
			}
			return cachedQuantity;
		}
		return proceed(quantity);
	}

	Product around(Product product) : PersistenceXPI.productCategoriesCachePointcut(Product) && args(product) {
		if (product != null && product.getId() != null) {
			StringBuilder keyBuilder = new StringBuilder("productCategories:");
			keyBuilder.append(product.getId());
			Product cachedProduct = (Product) cacheAdapter.getFromCache(keyBuilder.toString(), null);
			if (cachedProduct == null) {
				cachedProduct = proceed(product);
				cacheAdapter.getFromCache(keyBuilder.toString(), cachedProduct);
			}
			return cachedProduct;
		}
		return proceed(product);
	}

	Product around(Product product) : PersistenceXPI.productImagesCachePointcut(Product) && args(product) {
		if (product != null && product.getId() != null) {
			StringBuilder keyBuilder = new StringBuilder("productImages:");
			keyBuilder.append(product.getId());
			Product cachedProduct = (Product) cacheAdapter.getFromCache(keyBuilder.toString(), null);
			if (cachedProduct == null) {
				cachedProduct = proceed(product);
				cacheAdapter.getFromCache(keyBuilder.toString(), cachedProduct);
			}
			return cachedProduct;
		}
		return proceed(product);
	}

	List<Product> around(String text) : PersistenceXPI.searchProductCachePointcut(String) && args(text) {
		if (StringUtils.isNotBlank(text)) {
			StringBuilder keyBuilder = new StringBuilder("searchProductCache:");
			keyBuilder.append(text);
			List<Product> cachedProducts = (List<Product>) cacheAdapter.getFromCache(keyBuilder.toString(), null);
			if (cachedProducts == null) {
				cachedProducts = proceed(text);
				cacheAdapter.getFromCache(keyBuilder.toString(), cachedProducts);
			}
			return cachedProducts;
		}
		return proceed(text);
	}

}
