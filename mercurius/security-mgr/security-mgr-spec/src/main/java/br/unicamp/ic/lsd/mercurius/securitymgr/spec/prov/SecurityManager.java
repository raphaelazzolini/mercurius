package br.unicamp.ic.lsd.mercurius.securitymgr.spec.prov;

import br.unicamp.ic.sed.cosmos.IManager;

public interface SecurityManager extends IManager {

	Object getInternalComponent(String name);

}
