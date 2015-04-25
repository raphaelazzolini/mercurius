package br.unicamp.ic.lsd.mercurius.persistence.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.hibernate.Hibernate;

import br.unicamp.ic.lsd.mercurius.datatype.Category;
import br.unicamp.ic.lsd.mercurius.datatype.factory.CategoryFactory;
import br.unicamp.ic.lsd.mercurius.persistence.dao.CategoryDAO;

@Stateless(name = "categoryDAO")
@Local(CategoryDAO.class)
public class CategoryDAOImpl extends AbstractDAO<Category, Integer> implements CategoryDAO {

	private static final long serialVersionUID = 6088992034085106029L;

	private static final String FROM_CATEGORY_WHERE_PARENT_IS_NULL = "from Category where parent is null";

	@EJB
	private CategoryFactory categoryFactory;

	private static Class<? extends Category> entityClass;

	@Override
	public Category newInstance() {
		return categoryFactory.createInstance();
	}

	@Override
	protected Class<? extends Category> getEntityClass() {
		if (entityClass == null) {
			Category entity = categoryFactory.createInstance();
			entityClass = entity.getClass();
		}
		return entityClass;
	}

	@Override
	public void loadSubCategories(Category category) {
		Hibernate.initialize(category.getChildren());
	}

	@Override
	public void loadCategoryProducts(Category category) {
		Hibernate.initialize(category.getProducts());
	}

	@Override
	public List<Category> getMainCategories() {
		EntityManager entityManager = getEntityManager();
		TypedQuery<? extends Category> query = entityManager.createQuery(FROM_CATEGORY_WHERE_PARENT_IS_NULL,
				getEntityClass());

		List<Category> resultList = new ArrayList<Category>();
		for (Category category : query.getResultList()) {
			resultList.add(category);
		}

		return resultList;
	}

}
