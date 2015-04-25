package br.unicamp.ic.lsd.mercurius.view.impl;

import br.unicamp.ic.sed.cosmos.IManager;

public class ViewComponentFactory {

	private ViewComponentFactory() {
		super();
	}

	public static IManager createInstance() {
		return new Manager();
	}

}
