package br.unicamp.ic.lsd.mercurius.persistence.dao.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import br.unicamp.ic.lsd.mercurius.datatype.ProductImage;
import br.unicamp.ic.lsd.mercurius.datatype.ProductQuantity;
import br.unicamp.ic.lsd.mercurius.datatype.factory.ProductImageFactory;
import br.unicamp.ic.lsd.mercurius.persistence.dao.ProductImageDAO;

@Stateless(name = "productImageDAO")
@Local(ProductImageDAO.class)
public class ProductImageDAOImpl extends AbstractDAO<ProductImage, Integer> implements ProductImageDAO {

	private static final long serialVersionUID = 1L;

	@EJB
	private ProductImageFactory productImageFactory;

	private static Class<? extends ProductImage> entityClass;

	@Override
	public ProductImage newInstance() {
		return productImageFactory.createInstance();
	}

	@Override
	protected Class<? extends ProductImage> getEntityClass() {
		if (entityClass == null) {
			ProductImage entity = productImageFactory.createInstance();
			entityClass = entity.getClass();
		}
		return entityClass;
	}

	@Override
	public List<ProductImage> getImagesFromProductQuantity(ProductQuantity productQuantity) {
		TypedQuery<ProductImage> query = getEntityManager().createQuery(
				"from ProductImage where productQuantity = :productQuantity", ProductImage.class);
		query.setParameter("productQuantity", productQuantity);
		return query.getResultList();
	}

}
