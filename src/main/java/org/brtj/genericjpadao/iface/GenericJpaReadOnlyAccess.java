package org.brtj.genericjpadao.iface;

import java.util.List;
import java.util.Map;

public interface GenericJpaReadOnlyAccess {

	<T> Long countAll(Class<T> entityClass);

	<T, PK> T findById(Class<T> entityClass, PK primaryKey);

	<T> List<T> getAll(Class<T> entityClass);

	List<?> findWithNamedQuery(String namedQueryName);

	List<?> findWithNamedQuery(String namedQueryName, int resultLimit);

	List<?> findWithNamedQuery(String namedQueryName, Map<String, Object> parameters);

	List<?> findWithNamedQuery(String namedQueryName, Map<String, Object> parameters, int resultLimit);

	List<?> findWithNativeQuery(String nativeQueryName);

	List<?> findWithNativeQuery(String nativeQueryName, int resultLimit);

	List<?> findWithNativeQuery(String nativeQueryName, Map<String, Object> parameters);

	List<?> findWithNativeQuery(String nativeQueryName, Map<String, Object> parameters, int resultLimit);

	// <T> List<T> findByProperties(Class<T> entityClass, Map<String, Object>
	// properties);
	//
	// <T> List<T> findByProperties(Class<T> entityClass, Map<String, Object>
	// properties, Long resultLimit);

}
