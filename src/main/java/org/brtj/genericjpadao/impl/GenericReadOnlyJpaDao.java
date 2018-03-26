package org.brtj.genericjpadao.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.brtj.genericjpadao.iface.GenericJpaReadOnlyAccess;

public class GenericReadOnlyJpaDao implements GenericJpaReadOnlyAccess {
	
	@PersistenceContext
    private  EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	private <T> List<T> findWithQuery(Query query, Map<String, Object> parameters, int resultLimit) {
		if (parameters != null) {
			Set<String> keySet = parameters.keySet();
			for (String key : keySet) query.setParameter(key, parameters.get(key));
		}
		if (resultLimit > 0) {
			query.setMaxResults(resultLimit);
		}
		return (List<T>) query.getResultList();
	}

	@Override
	public <T> Long countAll(Class<T> entityClass) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<T> root = criteriaQuery.from(entityClass);
		criteriaQuery.select(criteriaBuilder.count(root));
		Query query = entityManager.createQuery(criteriaQuery);
		return (Long) query.getSingleResult();
	}

	@Override
	public <T, PK> T findById(Class<T> entityClass, PK primaryKey) {
		return entityManager.find(entityClass, primaryKey);
	}

	@Override
	public <T> List<T> getAll(Class<T> entityClass) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entityClass);
		Root<T> root = criteriaQuery.from(entityClass);
		CriteriaQuery<T> all = criteriaQuery.select(root);
		TypedQuery<T> allQuery = entityManager.createQuery(all);
		return (List<T>) allQuery.getResultList();
	}

	@Override
	public <T> List<T> findWithNamedQuery(String namedQueryName) {
		return findWithNamedQuery(namedQueryName, null, -1);
	}

	@Override
	public <T> List<T> findWithNamedQuery(String namedQueryName, int resultLimit) {
		return findWithNamedQuery(namedQueryName, null, resultLimit);
	}

	@Override
	public <T> List<T> findWithNamedQuery(String namedQueryName, Map<String, Object> parameters) {
		return findWithNamedQuery(namedQueryName, parameters, -1);
	}

	@Override
	public <T> List<T> findWithNamedQuery(String namedQueryName, Map<String, Object> parameters, int resultLimit) {
		Query query = entityManager.createNamedQuery(namedQueryName);
		return findWithQuery(query, parameters, resultLimit);
	}

	@Override
	public <T> List<T> findWithNativeQuery(String nativeQueryName) {
		return findWithNativeQuery(nativeQueryName, null, -1);
	}

	@Override
	public <T> List<T> findWithNativeQuery(String nativeQueryName, int resultLimit) {
		return findWithNativeQuery(nativeQueryName, null, resultLimit);
	}

	@Override
	public <T> List<T> findWithNativeQuery(String nativeQueryName, Map<String, Object> parameters) {
		return findWithNativeQuery(nativeQueryName, parameters, -1);
	}

	@Override
	public <T> List<T> findWithNativeQuery(String nativeQueryName, Map<String, Object> parameters, int resultLimit) {
		Query query = entityManager.createNativeQuery(nativeQueryName);
		return findWithQuery(query, parameters, resultLimit);
	}
	
}
