package br.unicamp.ic.lsd.mercurius.adminweb.view;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import br.unicamp.ic.lsd.mercurius.datatype.Configuration;
import br.unicamp.ic.lsd.mercurius.persistence.dao.ConfigurationDAO;

@Named("adminMainMBean")
@ApplicationScoped
public class AdminMainMBean {

	@EJB
	private ConfigurationDAO configurationDAO;

	private String imagesPath;
	private String imagesDirectory;

	@PostConstruct
	public void init() {
		Configuration imagesPathConfiguration = configurationDAO.findById("imagesPath");
		imagesPath = imagesPathConfiguration.getValue();
		Configuration imagesDirectoryConfiguration = configurationDAO.findById("imagesDirectory");
		imagesDirectory = imagesDirectoryConfiguration.getValue();
	}

	public String getImagesPath() {
		return imagesPath;
	}

	public void setImagesPath(String imagesPath) {
		this.imagesPath = imagesPath;
	}

	public String getImagesDirectory() {
		return imagesDirectory;
	}

	public void setImagesDirectory(String imagesDirectory) {
		this.imagesDirectory = imagesDirectory;
	}

}
