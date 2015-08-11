package br.unicamp.ic.lsd.mercurius.customersecurityconnector;

import br.unicamp.ic.lsd.mercurius.customermgr.spec.req.CustomerSecurityMgt;
import br.unicamp.ic.lsd.mercurius.securitymgr.spec.prov.SecurityMgt;
import br.unicamp.ic.sed.cosmos.IManager;

class SecurityMgtAdapter implements CustomerSecurityMgt {

	private final IManager manager;
	private SecurityMgt securityMgt;

	SecurityMgtAdapter(IManager manager) {
		super();
		this.manager = manager;
		securityMgt = (SecurityMgt) this.manager.getRequiredInterface("SecurityMgt");
	}

	@Override
	public String encryptPassword(String password) {
		return securityMgt.encryptPassword(password);
	}

}
