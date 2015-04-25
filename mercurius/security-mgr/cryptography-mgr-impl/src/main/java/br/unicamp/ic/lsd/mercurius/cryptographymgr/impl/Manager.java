package br.unicamp.ic.lsd.mercurius.cryptographymgr.impl;

import br.unicamp.ic.lsd.mercurius.cryptographymgr.spec.prov.CryptographyManager;
import br.unicamp.ic.lsd.mercurius.cryptographymgr.spec.prov.CryptographyMgt;
import br.unicamp.ic.sed.cosmos.AManager;
import br.unicamp.ic.sed.cosmos.IManager;

class Manager extends AManager implements CryptographyManager {

	private static final String CRYPTOGRAPHY_MGT = "CryptographyMgt";
	private static final String I_MANAGER = "IManager";

	Manager() {
		super();
		setProvidedInterfaceType(I_MANAGER, IManager.class);
		setProvidedInterfaceType(CRYPTOGRAPHY_MGT, CryptographyMgt.class);
		setProvidedInterface(I_MANAGER, this);
		setProvidedInterface(CRYPTOGRAPHY_MGT, new CryptographyFacade(this));
	}

}
