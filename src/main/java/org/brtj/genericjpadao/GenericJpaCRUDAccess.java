package org.brtj.genericjpadao;

import java.util.List;

public interface GenericJpaCRUDAccess extends GenericJpaReadOnlyAccess {

	<T> T create(T entity);
	
	<T> List<T> createAll(List<T> entityList);
	
	<T> T update(T entity);
	
	<T> List<T> updateAll(List<T> entityList);
	
	<T> void delete(T entity);
	
	<T> void deleteAll(Class<T> entityClass);
	
}
