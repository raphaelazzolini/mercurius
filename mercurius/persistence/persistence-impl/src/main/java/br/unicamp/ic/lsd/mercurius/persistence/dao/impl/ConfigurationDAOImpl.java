package br.unicamp.ic.lsd.mercurius.persistence.dao.impl;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import br.unicamp.ic.lsd.mercurius.datatype.Configuration;
import br.unicamp.ic.lsd.mercurius.datatype.factory.ConfigurationFactory;
import br.unicamp.ic.lsd.mercurius.persistence.dao.ConfigurationDAO;

@Stateless(name = "configurationDAO")
@Local(ConfigurationDAO.class)
public class ConfigurationDAOImpl extends AbstractDAO<Configuration, String> implements ConfigurationDAO {

	private static final long serialVersionUID = -1653400017447266588L;

	@EJB
	private ConfigurationFactory configurationFactory;

	private static Class<? extends Configuration> entityClass;

	@Override
	public Configuration newInstance() {
		return configurationFactory.createInstance();
	}

	@Override
	protected Class<? extends Configuration> getEntityClass() {
		if (entityClass == null) {
			Configuration entity = configurationFactory.createInstance();
			entityClass = entity.getClass();
		}
		return entityClass;
	}

}
