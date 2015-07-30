package br.unicamp.ic.lsd.mercurius.persistence.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.ejb.Local;

@Local
public interface DAO<T extends Serializable, ID extends Serializable> extends Serializable {

	void persist(T c);

	void persistWithFlush(T c);

	T merge(T c);

	T findById(ID id);

	void removeById(ID id);

	List<T> getAll();

	List<T> getAll(int offset, int maxResults);

	T newInstance();

	void refresh(T c);

	void flushAndClear();

	List<T> getListFromQuery(String query, Map<String, Object> parameters);

	T getSingleResultFromQuery(String query, Map<String, Object> parameters);

}
