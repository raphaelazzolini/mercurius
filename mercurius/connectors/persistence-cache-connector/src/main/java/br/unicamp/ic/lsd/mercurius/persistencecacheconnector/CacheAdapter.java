package br.unicamp.ic.lsd.mercurius.persistencecacheconnector;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import br.unicamp.ic.lsd.mercurius.cachemgr.spec.prov.CacheMgt;
import br.unicamp.ic.lsd.mercurius.datatype.Customer;
import br.unicamp.ic.lsd.mercurius.datatype.Product;
import br.unicamp.ic.lsd.mercurius.datatype.ProductQuantity;
import br.unicamp.ic.lsd.mercurius.datatype.Promotion;
import br.unicamp.ic.lsd.mercurius.persistence.spec.req.PersistenceCacheMgt;
import br.unicamp.ic.sed.cosmos.IManager;

class CacheAdapter implements PersistenceCacheMgt {

	private final IManager manager;

	CacheAdapter(IManager manager) {
		super();
		this.manager = manager;
	}

	@Override
	public List<Promotion> getFromPromotionCache(Customer customer) {
		CacheMgt cacheMgt = (CacheMgt) manager.getRequiredInterface("CacheMgt");
		StringBuilder keyBuilder = new StringBuilder("promotionCache:");
		if (customer != null && customer.getId() != null) {
			keyBuilder.append(customer.getId());
		} else {
			keyBuilder.append("noCustomer");
		}
		Object cachedValue = cacheMgt.getFromCache(keyBuilder.toString());
		if (cachedValue != null) {
			return (List<Promotion>) cachedValue;
		}
		return null;
	}

	@Override
	public void putOnPromotionCache(Customer customer, List<Promotion> value) {
		CacheMgt cacheMgt = (CacheMgt) manager.getRequiredInterface("CacheMgt");
		StringBuilder keyBuilder = new StringBuilder("promotionCache:");
		if (customer != null && customer.getId() != null) {
			keyBuilder.append(customer.getId());
		} else {
			keyBuilder.append("noCustomer");
			cacheMgt.putOnCache(keyBuilder.toString(), value);
		}
	}

	@Override
	public ProductQuantity getFromQuantityCache(ProductQuantity quantity) {
		CacheMgt cacheMgt = (CacheMgt) manager.getRequiredInterface("CacheMgt");
		StringBuilder keyBuilder = new StringBuilder("quantityCache:");
		if (quantity != null) {
			keyBuilder.append(quantity.getSku());
			Object cachedValue = cacheMgt.getFromCache(keyBuilder.toString());
			if (cachedValue != null) {
				return (ProductQuantity) cachedValue;
			}
		}
		return null;
	}

	@Override
	public void putOnQuantityCache(ProductQuantity quantity, ProductQuantity value) {
		CacheMgt cacheMgt = (CacheMgt) manager.getRequiredInterface("CacheMgt");
		StringBuilder keyBuilder = new StringBuilder("quantityCache:");
		if (quantity != null) {
			keyBuilder.append(quantity.getSku());
			cacheMgt.putOnCache(keyBuilder.toString(), value);
		}
	}

	@Override
	public Product getFromProductWithCategoriesCache(Product product) {
		CacheMgt cacheMgt = (CacheMgt) manager.getRequiredInterface("CacheMgt");
		StringBuilder keyBuilder = new StringBuilder("productWithCategoriesCache:");
		if (product != null) {
			keyBuilder.append(product.getId());
			Object cachedValue = cacheMgt.getFromCache(keyBuilder.toString());
			if (cachedValue != null) {
				return (Product) cachedValue;
			}
		}
		return null;
	}

	@Override
	public void putOnProductWithCategoriesCache(Product product, Product value) {
		CacheMgt cacheMgt = (CacheMgt) manager.getRequiredInterface("CacheMgt");
		StringBuilder keyBuilder = new StringBuilder("productWithCategoriesCache:");
		if (product != null) {
			keyBuilder.append(product.getId());
			cacheMgt.putOnCache(keyBuilder.toString(), value);
		}
	}

	@Override
	public Product getFromProductWithImagesCache(Product product) {
		CacheMgt cacheMgt = (CacheMgt) manager.getRequiredInterface("CacheMgt");
		StringBuilder keyBuilder = new StringBuilder("productWithImagesCache:");
		if (product != null) {
			keyBuilder.append(product.getId());
			Object cachedValue = cacheMgt.getFromCache(keyBuilder.toString());
			if (cachedValue != null) {
				return (Product) cachedValue;
			}
		}
		return null;
	}

	@Override
	public void putOnProductWithImagesCache(Product product, Product value) {
		CacheMgt cacheMgt = (CacheMgt) manager.getRequiredInterface("CacheMgt");
		StringBuilder keyBuilder = new StringBuilder("productWithImagesCache:");
		if (product != null) {
			keyBuilder.append(product.getId());
			cacheMgt.putOnCache(keyBuilder.toString(), value);
		}
	}

	@Override
	public List<Product> getFromProductSearchCache(String text) {
		CacheMgt cacheMgt = (CacheMgt) manager.getRequiredInterface("CacheMgt");
		StringBuilder keyBuilder = new StringBuilder("productSearchCache:");
		if (StringUtils.isNotBlank(text)) {
			keyBuilder.append(text);
			Object cachedValue = cacheMgt.getFromCache(keyBuilder.toString());
			if (cachedValue != null) {
				return (List<Product>) cachedValue;
			}
		}
		return null;
	}

	@Override
	public void putOnProductSearchCache(String text, List<Product> value) {
		CacheMgt cacheMgt = (CacheMgt) manager.getRequiredInterface("CacheMgt");
		StringBuilder keyBuilder = new StringBuilder("productSearchCache:");
		if (StringUtils.isNotBlank(text)) {
			keyBuilder.append(text);
			cacheMgt.putOnCache(keyBuilder.toString(), value);
		}
	}

}
