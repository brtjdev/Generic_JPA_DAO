package org.brtj.genericjpadao;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GenericJpaDaoTest {

	EntityManager entityManager;

	private static String PERSISTENCE_UNIT_NAME = "persistenceUnit";

	@Before
	public void setUp() {
		entityManager = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();
	}

	@Test
	public void test() {
		Person person = new Person("John", 34);
		entityManager.persist(person);
		Person foundPerson = entityManager.find(Person.class, new Long(1));
		System.out.println(foundPerson.getId());
	}

	@After
	public void cleanUp() {
		entityManager.clear();
		entityManager.close();
	}

}
