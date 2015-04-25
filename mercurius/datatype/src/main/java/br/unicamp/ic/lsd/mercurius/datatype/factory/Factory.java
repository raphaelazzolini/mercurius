package br.unicamp.ic.lsd.mercurius.datatype.factory;

import java.io.Serializable;

public interface Factory<T extends Serializable> {

	T createInstance();

}
