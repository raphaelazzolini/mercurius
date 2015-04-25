package br.unicamp.ic.lsd.mercurius.basketmgr.spec.prov;

import javax.ejb.Local;

import br.unicamp.ic.lsd.mercurius.datatype.factory.BasketItemFactory;
import br.unicamp.ic.lsd.mercurius.persistence.dao.BasketDAO;
import br.unicamp.ic.sed.cosmos.IManager;

@Local
public interface BasketManager extends IManager {

	BasketDAO getBasketDAO();

	BasketItemFactory getBasketItemFactory();

}
