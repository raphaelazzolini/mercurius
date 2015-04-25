package br.unicamp.ic.lsd.mercurius.addressmgr.spec.prov;

import br.unicamp.ic.lsd.mercurius.persistence.dao.AddressDAO;
import br.unicamp.ic.sed.cosmos.IManager;

public interface AddressManager extends IManager {

	AddressDAO getAddressDAO();

}
