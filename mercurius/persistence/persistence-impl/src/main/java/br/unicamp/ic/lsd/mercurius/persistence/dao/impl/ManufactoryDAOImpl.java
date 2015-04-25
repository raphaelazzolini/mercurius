package br.unicamp.ic.lsd.mercurius.persistence.dao.impl;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import br.unicamp.ic.lsd.mercurius.datatype.Manufacturer;
import br.unicamp.ic.lsd.mercurius.datatype.factory.ManufacturerFactory;
import br.unicamp.ic.lsd.mercurius.persistence.dao.ManufactoryDAO;

@Stateless(name = "manufactoryDAO")
@Local(ManufactoryDAO.class)
public class ManufactoryDAOImpl extends AbstractDAO<Manufacturer, Integer> implements ManufactoryDAO {

	private static final long serialVersionUID = 1048995080553843442L;

	@EJB
	private ManufacturerFactory manufacturerFactory;

	private static Class<? extends Manufacturer> entityClass;

	@Override
	public Manufacturer newInstance() {
		return manufacturerFactory.createInstance();
	}

	@Override
	protected Class<? extends Manufacturer> getEntityClass() {
		if (entityClass == null) {
			Manufacturer entity = manufacturerFactory.createInstance();
			entityClass = entity.getClass();
		}
		return entityClass;
	}

}
