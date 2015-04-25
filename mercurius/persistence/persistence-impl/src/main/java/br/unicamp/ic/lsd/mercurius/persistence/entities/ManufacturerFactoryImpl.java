package br.unicamp.ic.lsd.mercurius.persistence.entities;

import javax.ejb.Local;
import javax.ejb.Singleton;

import br.unicamp.ic.lsd.mercurius.datatype.Manufacturer;
import br.unicamp.ic.lsd.mercurius.datatype.factory.ManufacturerFactory;

@Singleton(name = "manufacturerFactory")
@Local(ManufacturerFactory.class)
public class ManufacturerFactoryImpl implements ManufacturerFactory {

	private ManufacturerFactoryImpl() {
		super();
	}

	@Override
	public Manufacturer createInstance() {
		return new ManufacturerImpl();
	}

}
