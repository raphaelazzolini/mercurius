package br.unicamp.ic.lsd.mercurius.persistence.entities;

import javax.ejb.Local;
import javax.ejb.Singleton;

import br.unicamp.ic.lsd.mercurius.datatype.Configuration;
import br.unicamp.ic.lsd.mercurius.datatype.factory.ConfigurationFactory;

@Singleton(name = "configurationFactory")
@Local(ConfigurationFactory.class)
public class ConfigurationFactoryImpl implements ConfigurationFactory {

	private ConfigurationFactoryImpl() {
		super();
	}

	@Override
	public Configuration createInstance() {
		return new ConfigurationImpl();
	}

}
