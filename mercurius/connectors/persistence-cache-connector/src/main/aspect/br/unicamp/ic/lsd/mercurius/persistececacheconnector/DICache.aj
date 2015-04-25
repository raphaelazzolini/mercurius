package br.unicamp.ic.lsd.mercurius.persistececacheconnector;

import br.unicamp.ic.lsd.mercurius.cachemgr.aspects.AACache;
import br.unicamp.ic.lsd.mercurius.persistencecacheconnector.CacheAdapter;

public aspect DICache extends AACache {

	protected pointcut cachedOperation(String key, Object value) : execution(public Object CacheAdapter.getFromCache(String, Object)) && args(key, value);

}
