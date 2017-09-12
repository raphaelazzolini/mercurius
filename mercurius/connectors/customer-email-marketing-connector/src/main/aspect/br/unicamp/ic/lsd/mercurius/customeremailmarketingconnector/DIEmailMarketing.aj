package br.unicamp.ic.lsd.mercurius.customeremailmarketingconnector;

import java.util.List;

import br.unicamp.ic.lsd.mercurius.datatype.Customer;
import br.unicamp.ic.lsd.mercurius.emailmarketingmgr.aspects.AAEmailMarketing;

public aspect DIEmailMarketing extends AAEmailMarketing {

	//Exposes sendEmail() pointcut at EmailMarketingAdapter so that it can be crosscut by AAEmailMarketing
	public pointcut sendEmail(List<Customer> customers) :
			execution(void EmailMarketingAdapter.sendEmail(List<Customer>)) && args(customers);

}