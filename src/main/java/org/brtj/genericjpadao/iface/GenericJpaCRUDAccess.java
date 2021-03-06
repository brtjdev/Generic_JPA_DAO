package org.brtj.genericjpadao.iface;

import java.util.List;

public interface GenericJpaCRUDAccess extends GenericJpaReadOnlyAccess {

	<T> T create(T entity);
	
	<T> List<T> createAll(List<T> entityList);
	
	<T> T update(T entity);
	
	<T> List<T> updateAll(List<T> entityList);
	
	<T> void delete(T entity);
	
	<T> Integer deleteAll(Class<T> entityClass);
	
	<T> void deleteAll(List<T> entityList);
	
}
