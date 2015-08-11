package br.unicamp.ic.lsd.mercurius.cachemgr.impl;

import java.util.HashMap;
import java.util.Map;

import br.unicamp.ic.lsd.mercurius.cachemgr.spec.prov.CacheMgt;
import br.unicamp.ic.sed.cosmos.IManager;

class CachaFacade implements CacheMgt {

	private static final long CACHE_TIME = 5 * 1000 * 60;

	private final IManager manager;

	private static Map<String, Object> cache;
	private static long cachedTime;

	CachaFacade(IManager manager) {
		super();
		this.manager = manager;
	}

	@Override
	public Object getFromCache(String key) {
		checkCache();
		Object ret = null;
		if (cache.containsKey(key)) {
			ret = cache.get(key);
		}
		return ret;
	}

	@Override
	public void putOnCache(String key, Object value) {
		checkCache();
		if (value != null) {
			cache.put(key, value);
		}
	}

	private void checkCache() {
		long currentTime = System.currentTimeMillis();
		long cacheEndTime = cachedTime + CACHE_TIME;
		if (cache == null || cacheEndTime < currentTime) {
			cache = new HashMap<>();
			cachedTime = System.currentTimeMillis();
		}
	}

}
