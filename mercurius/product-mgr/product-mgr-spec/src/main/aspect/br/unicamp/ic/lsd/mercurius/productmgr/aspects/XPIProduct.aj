package br.unicamp.ic.lsd.mercurius.productmgr.aspects;

import br.unicamp.ic.lsd.mercurius.datatype.Product;
import br.unicamp.ic.lsd.mercurius.datatype.ProductQuantity;

public aspect XPIProduct {

	public pointcut productPointcut() : execution(public Product br.unicamp.ic.lsd.mercurius.productmgr.impl.ProductFacade.getProduct(..))
			|| execution(public Product br.unicamp.ic.lsd.mercurius.productmgr.impl.ProductFacade.loadCategories(..))
			|| execution(public Product br.unicamp.ic.lsd.mercurius.productmgr.impl.ProductFacade.loadCategories(..));

	public pointcut productQuantityPointcut() : execution(public ProductQuantity br.unicamp.ic.lsd.mercurius.productmgr.impl.ProductFacade.getProductQuantity(..));

}
