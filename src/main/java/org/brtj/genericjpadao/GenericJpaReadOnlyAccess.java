package org.brtj.genericjpadao;

import java.util.List;
import java.util.Map;

public interface GenericJpaReadOnlyAccess {

	<T> Long countAll(Class<T> entityClass);
	
	<T, PK> T findById(Class<T> entityClass, PK primaryKey);
	
	<T> List<T> getAll(Class<T> entityClass);
	
	<T> List<T> findByProperties(Class<T> entityClass, Map<String, Object> properties);
	
	<T> List<T> findByProperties(Class<T> entityClass, Map<String, Object> properties, Long resultLimit);
	
}
