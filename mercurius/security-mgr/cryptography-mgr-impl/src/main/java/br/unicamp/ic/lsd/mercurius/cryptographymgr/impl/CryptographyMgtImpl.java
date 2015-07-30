package br.unicamp.ic.lsd.mercurius.cryptographymgr.impl;

import br.unicamp.ic.lsd.mercurius.cryptographymgr.spec.prov.CryptographyManager;

import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

class CryptographyMgtImpl {

	private final CryptographyManager manager;

	CryptographyMgtImpl(CryptographyManager manager) {
		super();
		this.manager = manager;
	}

	byte[] hashString(String string) {
		HashFunction hashFunction = Hashing.sha512();
		HashCode hashCode = hashFunction.newHasher().putString(string, Charsets.UTF_8).hash();
		return hashCode.asBytes();
	}

}
