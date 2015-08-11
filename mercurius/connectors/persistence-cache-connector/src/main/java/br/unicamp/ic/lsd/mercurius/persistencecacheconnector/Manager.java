package br.unicamp.ic.lsd.mercurius.persistencecacheconnector;

import br.unicamp.ic.lsd.mercurius.cachemgr.impl.CacheMgrComponentFactory;
import br.unicamp.ic.lsd.mercurius.cachemgr.spec.prov.CacheMgt;
import br.unicamp.ic.lsd.mercurius.persistence.spec.req.PersistenceCacheMgt;
import br.unicamp.ic.sed.cosmos.AManager;
import br.unicamp.ic.sed.cosmos.IManager;

class Manager extends AManager implements IManager {

	Manager() {
		super();
		setProvidedInterfaceType("IManager", IManager.class);
		setProvidedInterface("IManager", this);
		setRequiredInterfaceType("CacheMgt", CacheMgt.class);
		IManager cacheManager = CacheMgrComponentFactory.createInstance();
		setRequiredInterface("CacheMgt", cacheManager.getProvidedInterface("CacheMgt"));
		setProvidedInterfaceType("PersistenceCacheMgt", PersistenceCacheMgt.class);
		setProvidedInterface("PersistenceCacheMgt", new CacheAdapter(this));
	}

}
