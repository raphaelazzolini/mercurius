package br.unicamp.ic.lsd.mercurius.admin.impl;

import br.unicamp.ic.lsd.mercurius.admin.spec.prov.AdminMgt;
import br.unicamp.ic.sed.cosmos.IManager;

class AdminMgtImpl implements AdminMgt {

	private final IManager manager;

	AdminMgtImpl(IManager manager) {
		super();
		this.manager = manager;
	}

}
