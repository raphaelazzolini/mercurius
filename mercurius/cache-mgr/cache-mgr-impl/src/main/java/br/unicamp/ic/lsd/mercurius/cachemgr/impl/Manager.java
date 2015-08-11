package br.unicamp.ic.lsd.mercurius.cachemgr.impl;

import br.unicamp.ic.lsd.mercurius.cachemgr.spec.prov.CacheMgt;
import br.unicamp.ic.sed.cosmos.AManager;
import br.unicamp.ic.sed.cosmos.IManager;

class Manager extends AManager implements IManager {

	Manager() {
		super();
		setProvidedInterfaceType("IManager", IManager.class);
		setProvidedInterface("IManager", this);
		setProvidedInterfaceType("CacheMgt", CacheMgt.class);
		setProvidedInterface("CacheMgt", new CachaFacade(this));
	}

}
