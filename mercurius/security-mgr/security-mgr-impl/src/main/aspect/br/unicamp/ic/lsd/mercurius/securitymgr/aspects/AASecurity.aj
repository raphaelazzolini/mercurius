package br.unicamp.ic.lsd.mercurius.securitymgr.aspects;

import br.unicamp.ic.lsd.mercurius.cryptographymgr.spec.prov.CryptographyManager;
import br.unicamp.ic.lsd.mercurius.cryptographymgr.spec.prov.CryptographyMgt;
import br.unicamp.ic.lsd.mercurius.securitymgr.impl.SecurityMgrComponentFactory;
import br.unicamp.ic.lsd.mercurius.securitymgr.spec.prov.SecurityManager;

public abstract aspect AASecurity {

	private final SecurityManager manager = (SecurityManager) SecurityMgrComponentFactory.createInstance();

	abstract public pointcut encryptPassword(String password);

	String around(String password) : encryptPassword(String) && args(password) {
		CryptographyManager cryptographyManager = (CryptographyManager) manager.getInternalComponent("CryptographyMgr");
		CryptographyMgt cryptographyMgt = (CryptographyMgt) cryptographyManager.getProvidedInterface("CryptographyMgt");
		password = cryptographyMgt.encryptString(password);
		return proceed(password);
	}

}
