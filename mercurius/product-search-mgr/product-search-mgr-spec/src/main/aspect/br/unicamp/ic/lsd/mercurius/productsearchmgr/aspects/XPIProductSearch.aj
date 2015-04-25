package br.unicamp.ic.lsd.mercurius.productsearchmgr.aspects;

import java.util.List;

import br.unicamp.ic.lsd.mercurius.datatype.Product;

public privileged aspect XPIProductSearch {

	public pointcut loggingErrorScope() : within(br.unicamp.ic.lsd.mercurius.productsearchmgr.spec..*);

	public pointcut loggingInfoScope() : execution(public * br.unicamp.ic.lsd.mercurius.productsearchmgr.spec.none.*(..));

	public pointcut loggingDebugScope() : execution(public * br.unicamp.ic.lsd.mercurius.productsearchmgr.spec.prov.ProductSearchMgt.*(..));

	public pointcut loggingWarnScope() : execution(public * br.unicamp.ic.lsd.mercurius.productsearchmgr.spec.none.*(..));

	public pointcut productPointcut() : execution(public List<Product> br.unicamp.ic.lsd.mercurius.productsearchmgr.spec.prov.ProductSearchMgt.*(..));

}
