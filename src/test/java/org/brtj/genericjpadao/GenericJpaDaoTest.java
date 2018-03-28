package org.brtj.genericjpadao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.brtj.genericjpadao.iface.GenericJpaCRUDAccess;
import org.brtj.genericjpadao.impl.GenericJpaCRUDDao;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GenericJpaDaoTest {

	private EntityManager entityManager;

	private GenericJpaCRUDAccess genericDAO;

	private static String PERSISTENCE_UNIT_NAME = "persistenceUnit";

	@Before
	public void setUp() {
		entityManager = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();
		genericDAO = new GenericJpaCRUDDao();
		((GenericJpaCRUDDao) genericDAO).setEntityManager(entityManager);
	}

	@After
	public void cleanUp() {
		entityManager.clear();
		entityManager.close();
	}

	@Test
	public void createTest() {
		Person person = new Person("John", 34);
		EntityTransaction  tx = entityManager.getTransaction();
		tx.begin();
		Person storedPerson = genericDAO.create(person);
		tx.commit();
		assertNotNull(storedPerson);
		assertNotNull(storedPerson.getId());
		assertEquals(storedPerson.getAge(), person.getAge());
		assertEquals(storedPerson.getName(), person.getName());
		entityManager.remove(entityManager.contains(storedPerson) ? storedPerson : entityManager.merge(storedPerson));
	}

	@Test
	public void countAllTest() {
		EntityTransaction  tx = entityManager.getTransaction();
		tx.begin();
		Person person1 = new Person("John", 34);
		Person person2 = new Person("Mary", 23);
		Person person3 = new Person("Chris", 45);
		genericDAO.create(person1);
		genericDAO.create(person2);
		genericDAO.create(person3);
		tx.commit();
		assertEquals(new Long(3), genericDAO.countAll(Person.class));
		entityManager.remove(entityManager.contains(person1) ? person1 : entityManager.merge(person1));
		entityManager.remove(entityManager.contains(person2) ? person2 : entityManager.merge(person2));
		entityManager.remove(entityManager.contains(person3) ? person3 : entityManager.merge(person3));
	}

	@Test
	public void createAllTest() {
	}

	@Test
	public void findByIdTest() {
	}

	@Test
	public void updateTest() {
	}

	@Test
	public void getAllTest() {
	}

	@Test
	public void updateAllTest() {
	}

//	@Test
//	public void findWithNamedQueryTest(String namedQueryName) {
//	}
//
//	@Test
//	public void deleteTest(List entityList) {
//	}
//
//	@Test
//	public void deleteAllTest(Class entityClass) {
//	}
//
//	@Test
//	public void findWithNamedQueryTest(String namedQueryName, int resultLimit) {
//	}

	@Test
	public void deleteAllTest() {
	}
//
//	@Test
//	public void findWithNamedQueryTest(String namedQueryName, Map<String, Object> parameters) {
//	}
//
//	@Test
//	public void findWithNamedQueryTest(String namedQueryName, Map<String, Object> parameters, int resultLimit) {
//	}
//
//	@Test
//	public void findWithNativeQueryTest(String nativeQueryName) {
//	}
//
//	@Test
//	public void findWithNativeQueryTest(String nativeQueryName, int resultLimit) {
//	}
//
//	@Test
//	public void findWithNativeQueryTest(String nativeQueryName, Map<String, Object> parameters) {
//	}
//
//	@Test
//	public void findWithNativeQueryTest(String nativeQueryName, Map<String, Object> parameters, int resultLimit) {
//	}

}
