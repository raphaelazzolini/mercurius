package br.unicamp.ic.lsd.mercurius.productmgr.aspects;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import br.unicamp.ic.lsd.mercurius.datatype.Product;
import br.unicamp.ic.lsd.mercurius.datatype.ProductQuantity;

public aspect XPIProduct {

	public pointcut productPointcut() : execution(public Product br.unicamp.ic.lsd.mercurius.productmgr.impl.ProductFacade.getProduct(..))
			|| execution(public Product br.unicamp.ic.lsd.mercurius.productmgr.impl.ProductFacade.loadCategories(..))
			|| execution(public Product br.unicamp.ic.lsd.mercurius.productmgr.impl.ProductFacade.loadCategories(..));

	public pointcut productQuantityPointcut() : execution(public ProductQuantity br.unicamp.ic.lsd.mercurius.productmgr.impl.ProductFacade.getProductQuantity(..));

	public pointcut recommendedProductsPointcut(HttpServletRequest request, Integer quantity) : execution(public Collection<Product> br.unicamp.ic.lsd.mercurius.productmgr.impl.ProductFacade.getRandomProducts(HttpServletRequest, Integer)) && args(request, quantity);

}
