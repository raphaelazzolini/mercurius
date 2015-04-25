package br.unicamp.ic.lsd.mercurius.persistence.entities;

import javax.ejb.Local;
import javax.ejb.Singleton;

import br.unicamp.ic.lsd.mercurius.datatype.Category;
import br.unicamp.ic.lsd.mercurius.datatype.factory.CategoryFactory;

@Singleton(name = "categoryFactory")
@Local(CategoryFactory.class)
public class CategoryFactoryImpl implements CategoryFactory {

	private CategoryFactoryImpl() {
		super();
	}

	@Override
	public Category createInstance() {
		return new CategoryImpl();
	}

}
