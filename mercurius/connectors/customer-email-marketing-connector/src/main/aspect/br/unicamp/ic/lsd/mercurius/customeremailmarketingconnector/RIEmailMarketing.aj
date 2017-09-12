package br.unicamp.ic.lsd.mercurius.customeremailmarketingconnector;

import java.util.List;

import javax.mail.MessagingException;

import br.unicamp.ic.lsd.mercurius.customermgr.aspects.XPICustomer;
import br.unicamp.ic.lsd.mercurius.datatype.Customer;

public privileged aspect RIEmailMarketing {
	EmailMarketingAdapter emailMarketingAdapter = new EmailMarketingAdapter();
	
	public pointcut sendEmail(List<Customer> customers) : XPICustomer.sendEmail(List<Customer>) && args(customers);
	
	void around(List<Customer> customers): XPICustomer.sendEmail(customers) {
		try {
			emailMarketingAdapter.sendEmail(customers);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
