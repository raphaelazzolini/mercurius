package br.unicamp.ic.lsd.mercurius.productmgr.spec.prov;

import javax.ejb.Local;

import br.unicamp.ic.lsd.mercurius.persistence.dao.ConfigurationDAO;
import br.unicamp.ic.lsd.mercurius.persistence.dao.ManufactoryDAO;
import br.unicamp.ic.lsd.mercurius.persistence.dao.ProductDAO;
import br.unicamp.ic.lsd.mercurius.persistence.dao.ProductImageDAO;
import br.unicamp.ic.lsd.mercurius.persistence.dao.ProductQuantityDAO;
import br.unicamp.ic.sed.cosmos.IManager;

@Local
public interface ProductManager extends IManager {

	ProductDAO getProductDAO();

	ProductQuantityDAO getProductQuantityDAO();

	ProductImageDAO getProductImageDAO();

	ManufactoryDAO getManufactoryDAO();

	ConfigurationDAO getConfigurationDAO();

}
