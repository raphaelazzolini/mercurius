package br.unicamp.ic.lsd.mercurius.emailmarketingmgr.impl;


import br.unicamp.ic.lsd.mercurius.emailmarketingcustomerconnector.EmailMarketingCustomerConnectorComponentFactory;
import br.unicamp.ic.lsd.mercurius.emailmarketingproductconnector.EmailMarketingProductConnectorComponentFactory;
import br.unicamp.ic.lsd.mercurius.emailmarketingmgr.spec.prov.EmailMarketingManager;
import br.unicamp.ic.lsd.mercurius.emailmarketingmgr.spec.prov.EmailMarketingMgt;
import br.unicamp.ic.lsd.mercurius.emailmarketingmgr.spec.req.EmailMarketingCustomerMgt;
import br.unicamp.ic.lsd.mercurius.emailmarketingmgr.spec.req.EmailMarketingProductMgt;
import br.unicamp.ic.sed.cosmos.AManagerComposite;
import br.unicamp.ic.sed.cosmos.IManager;

class Manager extends AManagerComposite implements EmailMarketingManager {

	private static final String I_MANAGER = "IManager";
	private static final String EMAIL_MARKETING_MGT = "EmailMarketingMgt";
	private static final String EMAIL_MARKETING_CUSTOMER_MGT = "EmailMarketingCustomerMgt";
	private static final String EMAIL_MARKETING_PRODUCT_MGT = "EmailMarketingProductMgt";

	Manager() {
		super();
		/* Customer Manager REQUIRED Connector */
		IManager customerManager = EmailMarketingCustomerConnectorComponentFactory.createInstance();
		setRequiredInterfaceType(EMAIL_MARKETING_CUSTOMER_MGT, EmailMarketingCustomerMgt.class);
		// Saves adapter implementation from connector
		setRequiredInterface(EMAIL_MARKETING_CUSTOMER_MGT,
			customerManager.getProvidedInterface(EMAIL_MARKETING_CUSTOMER_MGT));
		
		/* Product Manager REQUIRED Connector */
		IManager productManager = EmailMarketingProductConnectorComponentFactory.createInstance();
		setRequiredInterfaceType(EMAIL_MARKETING_PRODUCT_MGT, EmailMarketingProductMgt.class);
		// Saves adapter implementation from connector
		setRequiredInterface(EMAIL_MARKETING_PRODUCT_MGT,
				productManager.getProvidedInterface(EMAIL_MARKETING_PRODUCT_MGT));
		
		/* PROVIDED by Email Marketing */
		setProvidedInterfaceType(I_MANAGER, IManager.class);
		setProvidedInterface(I_MANAGER, this);	
		setProvidedInterfaceType(EMAIL_MARKETING_MGT, EmailMarketingMgt.class);
		setProvidedInterface(EMAIL_MARKETING_MGT, new EmailMarketingFacade(this));
	}


}
