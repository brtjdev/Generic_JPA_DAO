package org.brtj.genericjpadao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.brtj.genericjpadao.iface.GenericJpaCRUDAccess;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GenericJpaDaoTest {

	GenericJpaCRUDAccess genericDAO;

	private static String PERSISTENCE_UNIT_NAME = "persistenceUnit";

	@Before
	public void setUp() {
	}

	@Test
	public void test() {
		Person person = new Person("John", 34);
		Person storedPerson = genericDAO.create(person);
		assertNotNull(storedPerson);
		assertEquals(storedPerson.getAge(), person.getAge());
		assertEquals(storedPerson.getName(), person.getName());
		Long personEntityCount = genericDAO.countAll(Person.class);
		assertEquals(personEntityCount, new Long(1));
	}

	@After
	public void cleanUp() {
	}

}
