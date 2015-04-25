package br.unicamp.ic.lsd.mercurius.cachemgr.aspects;

import java.util.HashMap;
import java.util.Map;

public abstract aspect AACache {

	protected abstract pointcut cachedOperation(String key, Object value);

	private static final long CACHE_TIME = 5*1000*60;

	private static long cachedTime;

	protected static Map<String, Object> cache;

	Object around(String key, Object value) : cachedOperation(key, value) {
		checkCache();

		Object ret = null;

		if (value != null) {
			ret = value;
			cache.put(key, value);
		} else if (cache.containsKey(key)) {
			ret = cache.get(key);
		}
		return ret;
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
