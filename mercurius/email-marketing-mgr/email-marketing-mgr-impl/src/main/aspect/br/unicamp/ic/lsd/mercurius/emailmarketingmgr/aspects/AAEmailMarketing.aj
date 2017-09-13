package br.unicamp.ic.lsd.mercurius.emailmarketingmgr.aspects;

import java.util.Collection;
import java.util.List;

import javax.mail.MessagingException;

import br.unicamp.ic.lsd.mercurius.datatype.Customer;
import br.unicamp.ic.lsd.mercurius.datatype.Product;
import br.unicamp.ic.lsd.mercurius.emailmarketingmgr.impl.EmailMarketingMgrComponentFactory;
import br.unicamp.ic.lsd.mercurius.emailmarketingmgr.spec.prov.EmailMarketingMgt;
import br.unicamp.ic.lsd.mercurius.emailmarketingmgr.spec.prov.EmailMarketingManager;
import br.unicamp.ic.lsd.mercurius.emailmarketingmgr.spec.req.EmailMarketingProductMgt;
import br.unicamp.ic.lsd.mercurius.emailmarketingmgr.impl.SendEmail;
import br.unicamp.ic.lsd.mercurius.emailmarketingmgr.impl.Manager;

public abstract aspect AAEmailMarketing {

	private final EmailMarketingManager manager = (EmailMarketingManager) EmailMarketingMgrComponentFactory.createInstance();
	
	abstract public pointcut sendEmail(List<Customer> customers);

	void around(List<Customer> customers) : sendEmail(List<Customer>) && args(customers) {
		
		EmailMarketingProductMgt productMgt = (EmailMarketingProductMgt) manager.getRequiredInterface("EmailMarketingProductMgt");
		
		List<Product> products = productMgt.getProducts();
		
		SendEmail email = new SendEmail();
		
		try {
			email.sendEmail(customers, products);
		} catch(MessagingException e) {}
	}
}
