package br.unicamp.ic.lsd.mercurius.cryptographymgr.impl;

import br.unicamp.ic.lsd.mercurius.cryptographymgr.spec.prov.CryptographyManager;
import br.unicamp.ic.lsd.mercurius.cryptographymgr.spec.prov.CryptographyMgt;

class CryptographyFacade implements CryptographyMgt {

	private final CryptographyMgtImpl cryptographyMgtImpl;
	private final CryptographyManager manager;

	CryptographyFacade(CryptographyManager manager) {
		super();
		this.manager = manager;
		this.cryptographyMgtImpl = new CryptographyMgtImpl(this.manager);
	}

	@Override
	public String encryptString(String string) {
		byte[] hash = cryptographyMgtImpl.hashString(string);
		StringBuilder encryptedStringBuilder = new StringBuilder();

		for (byte hashByte : hash) {
			encryptedStringBuilder.append(String.format("%02x", hashByte & 0xff));
		}

		return encryptedStringBuilder.toString();
	}

}
