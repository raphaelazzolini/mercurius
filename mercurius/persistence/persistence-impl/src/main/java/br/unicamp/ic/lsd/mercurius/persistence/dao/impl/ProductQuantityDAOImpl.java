package br.unicamp.ic.lsd.mercurius.persistence.dao.impl;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import org.hibernate.Hibernate;

import br.unicamp.ic.lsd.mercurius.datatype.ProductQuantity;
import br.unicamp.ic.lsd.mercurius.datatype.factory.ProductQuantityFactory;
import br.unicamp.ic.lsd.mercurius.persistence.dao.ProductImageDAO;
import br.unicamp.ic.lsd.mercurius.persistence.dao.ProductQuantityDAO;
import br.unicamp.ic.lsd.mercurius.persistence.spec.req.PersistenceCacheMgt;
import br.unicamp.ic.lsd.mercurius.persistencecacheconnector.CacheConnectorComponentFactory;

@Stateless(name = "productQuantityDAO")
@Local(ProductQuantityDAO.class)
public class ProductQuantityDAOImpl extends AbstractDAO<ProductQuantity, String> implements ProductQuantityDAO {

	private static final long serialVersionUID = 8741645631661393040L;

	@EJB
	private ProductQuantityFactory productQuantityFactory;

	@EJB
	private ProductImageDAO productImageDAO;

	private static Class<? extends ProductQuantity> entityClass;

	@Override
	public ProductQuantity newInstance() {
		return productQuantityFactory.createInstance();
	}

	@Override
	protected Class<? extends ProductQuantity> getEntityClass() {
		if (entityClass == null) {
			ProductQuantity entity = productQuantityFactory.createInstance();
			entityClass = entity.getClass();
		}
		return entityClass;
	}

	@Override
	public ProductQuantity loadAttributes(ProductQuantity productQuantity) {
		PersistenceCacheMgt cacheMgt = (PersistenceCacheMgt) CacheConnectorComponentFactory.createInstance()
				.getProvidedInterface("PersistenceCacheMgt");
		ProductQuantity cachedValue = null;
		if (cacheMgt != null) {
			cachedValue = cacheMgt.getFromQuantityCache(productQuantity);
		}
		if (cachedValue == null) {
			cachedValue = findById(productQuantity.getSku());
			Hibernate.initialize(cachedValue.getProductsAttributes());
			Hibernate.initialize(cachedValue.getProductImages());
		}
		if (cacheMgt != null) {
			cacheMgt.putOnQuantityCache(productQuantity, cachedValue);
		}
		return cachedValue;
	}

}
