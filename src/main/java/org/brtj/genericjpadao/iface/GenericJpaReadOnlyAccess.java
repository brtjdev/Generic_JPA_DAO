package org.brtj.genericjpadao.iface;

import java.util.List;
import java.util.Map;

public interface GenericJpaReadOnlyAccess {

	<T> Long countAll(Class<T> entityClass);

	<T, PK> T findById(Class<T> entityClass, PK primaryKey);

	<T> List<T> getAll(Class<T> entityClass);

	<T> List<T> findWithNamedQuery(String namedQueryName);

	<T> List<T> findWithNamedQuery(String namedQueryName, int resultLimit);

	<T> List<T> findWithNamedQuery(String namedQueryName, Map<String, Object> parameters);

	<T> List<T> findWithNamedQuery(String namedQueryName, Map<String, Object> parameters, int resultLimit);

	<T> List<T> findWithNativeQuery(String nativeQueryName);

	<T> List<T> findWithNativeQuery(String nativeQueryName, int resultLimit);

	<T> List<T> findWithNativeQuery(String nativeQueryName, Map<String, Object> parameters);

	<T> List<T> findWithNativeQuery(String nativeQueryName, Map<String, Object> parameters, int resultLimit);

	// <T> List<T> findByProperties(Class<T> entityClass, Map<String, Object>
	// properties);
	//
	// <T> List<T> findByProperties(Class<T> entityClass, Map<String, Object>
	// properties, Long resultLimit);

}
