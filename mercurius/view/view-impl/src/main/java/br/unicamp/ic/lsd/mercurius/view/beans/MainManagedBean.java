package br.unicamp.ic.lsd.mercurius.view.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;

import br.unicamp.ic.lsd.mercurius.datatype.Configuration;
import br.unicamp.ic.lsd.mercurius.persistence.dao.ConfigurationDAO;

@Named("mainManagedBean")
@ApplicationScoped
public class MainManagedBean implements Serializable {

	private static final long serialVersionUID = -7703449526113353913L;

	private String urlPath;
	private String imagesPath;

	@EJB
	private ConfigurationDAO configurationDAO;

	@PostConstruct
	public void init() {
		Configuration urlConfiguration = configurationDAO.findById("urlPath");
		Configuration imagesPathConfiguration = configurationDAO.findById("imagesPath");
		urlPath = StringUtils.EMPTY;
		imagesPath = StringUtils.EMPTY;

		if (urlConfiguration != null) {
			urlPath = urlConfiguration.getValue();
		}
		if (imagesPathConfiguration != null) {
			imagesPath = imagesPathConfiguration.getValue();
		}
	}

	public String getUrlPath() {
		return urlPath;
	}

	public void setUrlPath(String urlPath) {
		this.urlPath = urlPath;
	}

	public String getImagesPath() {
		return imagesPath;
	}

	public void setImagesPath(String imagesPath) {
		this.imagesPath = imagesPath;
	}

}
