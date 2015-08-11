package br.unicamp.ic.lsd.mercurius.cachemgr.spec.prov;

public interface CacheMgt {

	Object getFromCache(String key);

	void putOnCache(String key, Object value);

}
