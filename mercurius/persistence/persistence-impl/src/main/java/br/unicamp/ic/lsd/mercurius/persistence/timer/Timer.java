package br.unicamp.ic.lsd.mercurius.persistence.timer;

import javax.annotation.PostConstruct;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.mail.MessagingException;

import br.unicamp.ic.lsd.mercurius.customermgr.impl.CustomerMgrComponentFactory;
import br.unicamp.ic.lsd.mercurius.customermgr.spec.prov.CustomerMgt;
import br.unicamp.ic.sed.cosmos.IManager;

/*
 * Session Bean implementation class Timer
 */

@Singleton
public class Timer {
	IManager customerManager;
	CustomerMgt customerMgt;
	
	@PostConstruct
	public void init() {
		customerManager = CustomerMgrComponentFactory.createInstance();
		customerMgt = (CustomerMgt) customerManager.getProvidedInterface("CustomerMgt");
	}

	@Schedule(second="*/30", minute="*", hour="*", persistent=false)
	public void SendEmail() throws MessagingException {
		customerMgt.sendEmailMarketing();
	}

}
