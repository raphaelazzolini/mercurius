package br.unicamp.ic.lsd.mercurius.customersecurityconnector;

import br.unicamp.ic.lsd.mercurius.securitymgr.aspects.AASecurity;

public aspect DISecurity extends AASecurity {

	public pointcut encryptPassword(String password) : execution(String SecurityCustomerAdapter.encryptPassword(String)) && args(password);

}
