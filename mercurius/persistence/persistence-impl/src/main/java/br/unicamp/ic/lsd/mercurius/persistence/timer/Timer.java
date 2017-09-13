package br.unicamp.ic.lsd.mercurius.persistence.timer;

import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import br.unicamp.ic.lsd.mercurius.customermgr.impl.CustomerMgrComponentFactory;
import br.unicamp.ic.lsd.mercurius.emailmarketingmgr.impl.EmailMarketingMgrComponentFactory;
import br.unicamp.ic.lsd.mercurius.emailmarketingmgr.spec.prov.EmailMarketingMgt;
import br.unicamp.ic.sed.cosmos.IManager;

@Singleton
public class Timer {
	IManager emailManager;
	EmailMarketingMgt emailMarketingMgt;
	
	@PostConstruct
	public void init() {
		emailManager = EmailMarketingMgrComponentFactory.createInstance();
		emailMarketingMgt = (EmailMarketingMgt) emailManager.getProvidedInterface("EmailMarketingMgt");
	}

	@Schedule(second="*/30", minute="*", hour="*", persistent=false)
	public void SendEmail() throws MessagingException {
		emailMarketingMgt.sendEmail();
	}
}
