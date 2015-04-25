package br.unicamp.ic.lsd.mercurius.persistence.dao.impl;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import org.hibernate.Hibernate;

import br.unicamp.ic.lsd.mercurius.datatype.ProductQuantity;
import br.unicamp.ic.lsd.mercurius.datatype.factory.ProductQuantityFactory;
import br.unicamp.ic.lsd.mercurius.persistence.dao.ProductQuantityDAO;

@Stateless(name = "productQuantityDAO")
@Local(ProductQuantityDAO.class)
public class ProductQuantityDAOImpl extends AbstractDAO<ProductQuantity, String> implements ProductQuantityDAO {

	private static final long serialVersionUID = 8741645631661393040L;

	@EJB
	private ProductQuantityFactory productQuantityFactory;

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
		productQuantity = findById(productQuantity.getSku());
		Hibernate.initialize(productQuantity.getProductsAttributes());
		Hibernate.initialize(productQuantity.getProductImages());
		return productQuantity;
	}

}
