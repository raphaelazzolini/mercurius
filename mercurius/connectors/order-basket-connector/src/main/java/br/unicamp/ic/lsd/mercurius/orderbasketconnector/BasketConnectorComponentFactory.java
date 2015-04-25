package br.unicamp.ic.lsd.mercurius.orderbasketconnector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.unicamp.ic.sed.cosmos.IManager;

public class BasketConnectorComponentFactory {

	private static final String MANAGER_JNDI = "java:comp/env/ejb/basketOrderConnectorManager";

	private BasketConnectorComponentFactory() {
		super();
	}

	public static IManager createInstance() {
		try {
			Context context = new InitialContext();
			IManager manager = (IManager) context.lookup(MANAGER_JNDI);
			return manager;
		} catch (NamingException e) {
			return null;
		}
	}

}
