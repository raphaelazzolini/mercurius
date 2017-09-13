package br.unicamp.ic.lsd.mercurius.emailmarketingmgr.impl;

import java.util.Collection;
import java.util.List;
import java.util.Vector;


import javax.mail.MessagingException;

import br.unicamp.ic.lsd.mercurius.datatype.Customer;
import br.unicamp.ic.lsd.mercurius.datatype.Product;
import br.unicamp.ic.lsd.mercurius.emailmarketingmgr.impl.EmailMarketingMgtImpl;
import br.unicamp.ic.lsd.mercurius.emailmarketingmgr.spec.prov.EmailMarketingManager;
import br.unicamp.ic.lsd.mercurius.emailmarketingmgr.spec.prov.EmailMarketingMgt;
import br.unicamp.ic.lsd.mercurius.emailmarketingmgr.spec.req.EmailMarketingCustomerMgt;
import br.unicamp.ic.lsd.mercurius.emailmarketingmgr.spec.req.EmailMarketingProductMgt;
import br.unicamp.ic.lsd.mercurius.productmgr.spec.req.ProductRecommendedProductsMgt;
import br.unicamp.ic.sed.cosmos.IManager;

class EmailMarketingFacade implements EmailMarketingMgt {
	
	private final EmailMarketingManager manager;
	private final EmailMarketingMgtImpl emailMarketingMgtImpl;
	
	EmailMarketingFacade(EmailMarketingManager manager) {
		super();
		this.manager = manager;
		this.emailMarketingMgtImpl = new EmailMarketingMgtImpl(this.manager);
	}
	
	@Override
	public void sendEmail() {
		
		// Gets list of products for email announce
		EmailMarketingProductMgt productMgt = (EmailMarketingProductMgt) manager.getRequiredInterface("EmailMarketingProductMgt");
		List<Product> products = productMgt.getProducts();
		
		// Gets list of clients' email
		EmailMarketingCustomerMgt customerMgt = (EmailMarketingCustomerMgt) manager.getRequiredInterface("EmailMarketingCustomerMgt");
		List<String> clients = customerMgt.getCustomers();
		
		// Sends email with products to clients on the database
		try {
			emailMarketingMgtImpl.sendEmail(clients, products);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
