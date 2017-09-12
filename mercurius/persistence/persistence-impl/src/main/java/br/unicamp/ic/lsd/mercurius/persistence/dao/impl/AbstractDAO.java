package br.unicamp.ic.lsd.mercurius.persistence.dao.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.collections4.MapUtils;

import br.unicamp.ic.lsd.mercurius.datatype.Customer;
import br.unicamp.ic.lsd.mercurius.persistence.dao.DAO;

public abstract class AbstractDAO<T extends Serializable, ID extends Serializable> implements DAO<T, ID> {

	private static final long serialVersionUID = 2154109715030701386L;

	@PersistenceContext(unitName = "mercuriusPU")
	private EntityManager em;

	protected AbstractDAO() {
		super();
	}

	protected CriteriaBuilder createCriteriaBuilder() {
		return getEntityManager().getCriteriaBuilder();
	}

	@Override
	public void refresh(final T c) {
		getEntityManager().refresh(c);
	}

	@SuppressWarnings("unchecked")
	protected CriteriaQuery<T> createCriteriaQuery(final CriteriaBuilder cb) {
		return (CriteriaQuery<T>) cb.createQuery(getEntityClass());
	}

	protected TypedQuery<T> createTypedQuery(CriteriaQuery<T> cq) {
		return getEntityManager().createQuery(cq);
	}

	@SuppressWarnings("unchecked")
	protected Root<T> createRoot(final CriteriaQuery<T> cq) {
		return (Root<T>) cq.from(getEntityClass());
	}

	protected List<T> findByCriteria(CriteriaQuery<T> cq) {

		TypedQuery<T> type = createTypedQueryFromCriteria(cq);
		return type.getResultList();
	}

	@Override
	public T findById(final ID id) {
		return getEntityManager().find(getEntityClass(), id);
	}

	@Override
	public List<T> getAll() {
		return findByCriteria(null);
	}

	@Override
	public List<T> getAll(int offset, int maxResults) {
		TypedQuery<T> typedQuery = createTypedQueryFromCriteria(null);
		typedQuery.setFirstResult(offset);
		typedQuery.setMaxResults(maxResults);
		return typedQuery.getResultList();
	}

	protected T getSingleResult(final CriteriaQuery<? extends T> cq) {

		try {
			return getEntityManager().createQuery(cq).getSingleResult();
		} catch (final NoResultException nre) {
			return null;
		}

	}

	protected T getSingleResult(final TypedQuery<T> tq) {

		try {
			return tq.getSingleResult();
		} catch (final NoResultException nre) {
			return null;
		}

	}

	@Override
	public T merge(final T v) {
		return getEntityManager().merge(v);
	}

	@Override
	public void persist(final T v) {
		getEntityManager().persist(v);
	}

	@Override
	public void persistWithFlush(final T v) {
		getEntityManager().persist(v);
		getEntityManager().flush();
		getEntityManager().clear();
	}

	@Override
	public void flushAndClear() {
		getEntityManager().flush();
		getEntityManager().clear();
	}

	@Override
	public void removeById(ID id) {
		T c = findById(id);
		getEntityManager().remove(c);
	}

	public Predicate createEqualPredicate(CriteriaBuilder cb, Root<T> root, String attr, Object value) {
		Path<?> path = root.get(attr);
		return cb.equal(path, value);
	}

	public List<T> createResultList(CriteriaQuery<T> cq, Collection<Predicate> predicates) {

		int size = predicates.size();
		Predicate[] array = predicates.toArray(new Predicate[size]);

		cq.where(array);

		TypedQuery<T> tq = createTypedQuery(cq);
		return tq.getResultList();

	}

	@Override
	public List<T> getListFromQuery(String query, Map<String, Object> parameters) {
		TypedQuery<T> tq = getTypedQuery(query, parameters);
		return tq.getResultList();
	}

	@Override
	public T getSingleResultFromQuery(String query, Map<String, Object> parameters) {
		TypedQuery<T> tq = getTypedQuery(query, parameters);
		return getSingleResult(tq);
	}

	@SuppressWarnings("unchecked")
	private TypedQuery<T> getTypedQuery(String query, Map<String, Object> parameters) {
		TypedQuery<T> tq = (TypedQuery<T>) getEntityManager().createQuery(query, getEntityClass());
		if (MapUtils.isNotEmpty(parameters)) {
			for (String key : parameters.keySet()) {
				tq.setParameter(key, parameters.get(key));
			}
		}
		return tq;
	}

	protected EntityManager getEntityManager() {
		return em;
	}

	protected abstract Class<? extends T> getEntityClass();

	private TypedQuery<T> createTypedQueryFromCriteria(CriteriaQuery<T> cq) {
		if (cq == null) {
			cq = createCriteriaQuery(createCriteriaBuilder());

			final Root<T> root = createRoot(cq);
			cq.select(root);
		}

		TypedQuery<T> type = getEntityManager().createQuery(cq);
		return type;
	}

	public List<? extends Customer> getCustomers() {
		return null;
	}

}
