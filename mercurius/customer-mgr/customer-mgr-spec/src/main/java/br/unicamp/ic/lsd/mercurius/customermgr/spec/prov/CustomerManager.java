package br.unicamp.ic.lsd.mercurius.customermgr.spec.prov;

import br.unicamp.ic.lsd.mercurius.persistence.dao.CustomerDAO;
import br.unicamp.ic.sed.cosmos.IManager;

public interface CustomerManager extends IManager {

	IManager getInternalComponent(String name);

	CustomerDAO getCustomerDAO();

}
