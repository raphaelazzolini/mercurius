package br.unicamp.ic.lsd.mercurius.persistence.aspects;

import java.util.List;

import br.unicamp.ic.lsd.mercurius.datatype.Customer;
import br.unicamp.ic.lsd.mercurius.datatype.Product;
import br.unicamp.ic.lsd.mercurius.datatype.ProductQuantity;
import br.unicamp.ic.lsd.mercurius.datatype.Promotion;

public aspect PersistenceXPI {

	public pointcut promotionCachePointcut(Customer customer) : execution(public List<Promotion> br.unicamp.ic.lsd.mercurius.persistence.dao.PromotionDAO.getPromotions(Customer)) && args(customer);

	public pointcut quantityCachePointcut(ProductQuantity quantity) : execution(public ProductQuantity br.unicamp.ic.lsd.mercurius.persistence.dao.ProductQuantityDAO.loadAttributes(ProductQuantity)) && args(quantity);

	public pointcut productCategoriesCachePointcut(Product product) : execution(public Product br.unicamp.ic.lsd.mercurius.persistence.dao.ProductDAO.loadCategories(Product)) && args(product);

	public pointcut productImagesCachePointcut(Product product) : execution(public Product br.unicamp.ic.lsd.mercurius.persistence.dao.ProductDAO.loadProductImages(Product)) && args(product);

	public pointcut searchProductCachePointcut(String text) : execution(public List<Product> br.unicamp.ic.lsd.mercurius.persistence.dao.ProductDAO.searchByText(String)) && args(text);

}
