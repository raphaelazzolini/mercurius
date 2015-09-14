package br.unicamp.ic.lsd.mercurius.securitymgr.impl;

import br.unicamp.ic.lsd.mercurius.cryptographymgr.impl.CryptographyMgrComponentFactory;
import br.unicamp.ic.lsd.mercurius.securitymgr.spec.prov.SecurityManager;
import br.unicamp.ic.lsd.mercurius.securitymgr.spec.req.SecurityRiskAnalysisMgt;
import br.unicamp.ic.sed.cosmos.AManagerComposite;
import br.unicamp.ic.sed.cosmos.IManager;

class Manager extends AManagerComposite implements SecurityManager {

	private static final String CRYPTOGRAPHY_MGR = "CryptographyMgr";
	private static final String SECURITY_RISK_ANALYSIS_MGT = "SecurityRiskAnalysisMgt";
	private static final String I_MANAGER = "IManager";

	Manager() {
		super();
		setProvidedInterfaceType(I_MANAGER, IManager.class);
		setProvidedInterface(I_MANAGER, this);
		setRequiredInterfaceType(SECURITY_RISK_ANALYSIS_MGT, SecurityRiskAnalysisMgt.class);
		setInternalComponent(CRYPTOGRAPHY_MGR, CryptographyMgrComponentFactory.createInstance());
	}

}
