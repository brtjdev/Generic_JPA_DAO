package org.brtj.genericjpadao.impl;

import java.util.List;

import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.brtj.genericjpadao.iface.GenericJpaCRUDAccess;

public class GenericJpaCRUDDao extends GenericJpaReadOnlyDao implements GenericJpaCRUDAccess {

	@Override
	@Transactional(TxType.REQUIRES_NEW)
	public <T> T create(T entity) {
		entityManager.persist(entity);
		entityManager.flush();
		entityManager.refresh(entity);
		return entity;
	}

	@Override
	public <T> List<T> createAll(List<T> entityList) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		for (T entity : entityList) {
			entityManager.persist(entity);
			entityManager.refresh(entity);
		}
		transaction.commit();
		return entityList;
	}

	@Override
	@Transactional(TxType.REQUIRES_NEW)
	public <T> T update(T entity) {
		return entityManager.merge(entity);
	}

	@Override
	public <T> List<T> updateAll(List<T> entityList) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		for (T entity : entityList) {
			entityManager.persist(entity);
			entityManager.refresh(entity);
		}
		transaction.commit();
		return entityList;
	}

	@Override
	@Transactional(TxType.REQUIRES_NEW)
	public <T> void delete(T entity) {
		entityManager.remove(
				entityManager.contains(entity) ? entity : entityManager.merge(entity));
		entityManager.refresh(entity);
	}

	@Override
	@Transactional(TxType.REQUIRES_NEW)
	public <T> Integer deleteAll(Class<T> entityClass) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
	    CriteriaDelete<T> query = builder.createCriteriaDelete(entityClass);
	    query.from(entityClass);
	    return entityManager.createQuery(query).executeUpdate();
	}

	@Override
	public <T> void deleteAll(List<T> entityList) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		for (T entity : entityList) {
			entityManager.remove(
					entityManager.contains(entity) ? entity : entityManager.merge(entity));
			entityManager.refresh(entity);
		}
		transaction.commit();
	}

}
