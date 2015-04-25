package br.unicamp.ic.lsd.mercurius.persistence.entities;

import javax.ejb.Local;
import javax.ejb.Singleton;

import br.unicamp.ic.lsd.mercurius.datatype.Basket;
import br.unicamp.ic.lsd.mercurius.datatype.factory.BasketFactory;

@Singleton(name = "basketFactory")
@Local(BasketFactory.class)
public class BasketFactoryImpl implements BasketFactory {

	private BasketFactoryImpl() {
		super();
	}

	@Override
	public Basket createInstance() {
		return new BasketImpl();
	}

}
