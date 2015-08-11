package br.unicamp.ic.lsd.mercurius.persistencecacheconnector;

import br.unicamp.ic.sed.cosmos.IManager;

public class CacheConnectorComponentFactory {

	private CacheConnectorComponentFactory() {
		super();
	}

	public static IManager createInstance() {
		return new Manager();
	}

}
