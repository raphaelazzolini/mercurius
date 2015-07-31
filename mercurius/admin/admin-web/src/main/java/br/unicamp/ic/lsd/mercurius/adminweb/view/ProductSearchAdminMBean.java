package br.unicamp.ic.lsd.mercurius.adminweb.view;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unicamp.ic.lsd.mercurius.admin.impl.AdminComponentFactory;
import br.unicamp.ic.lsd.mercurius.admin.spec.req.AdminProductSearchMgt;
import br.unicamp.ic.sed.cosmos.IManager;

@Named("productsSearchAdminMBean")
@ViewScoped
public class ProductSearchAdminMBean implements Serializable {

	private static final long serialVersionUID = 2219030144658730664L;

	private IManager adminManager;
	private AdminProductSearchMgt productSearchMgt;

	@PostConstruct
	public void init() {
		adminManager = AdminComponentFactory.createInstance();
		productSearchMgt = (AdminProductSearchMgt) adminManager.getRequiredInterface("AdminProductSearchMgt");
	}

	public void indexSearch() {
		productSearchMgt.indexSearch();
	}

}
