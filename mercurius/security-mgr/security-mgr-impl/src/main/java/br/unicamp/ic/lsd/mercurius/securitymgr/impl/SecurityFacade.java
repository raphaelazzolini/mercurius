package br.unicamp.ic.lsd.mercurius.securitymgr.impl;

import br.unicamp.ic.lsd.mercurius.cryptographymgr.spec.prov.CryptographyManager;
import br.unicamp.ic.lsd.mercurius.cryptographymgr.spec.prov.CryptographyMgt;
import br.unicamp.ic.lsd.mercurius.securitymgr.spec.prov.SecurityMgt;
import br.unicamp.ic.sed.cosmos.AManagerComposite;

class SecurityFacade implements SecurityMgt {

	private final AManagerComposite manager;

	SecurityFacade(AManagerComposite manager) {
		super();
		this.manager = manager;
	}

	@Override
	public String encryptPassword(String password) {
		CryptographyManager cryptographyManager = (CryptographyManager) manager.getInternalComponent("CryptographyMgr");
		CryptographyMgt cryptographyMgt = (CryptographyMgt) cryptographyManager.getProvidedInterface("CryptographyMgt");
		password = cryptographyMgt.encryptString(password);
		return password;
	}

}
