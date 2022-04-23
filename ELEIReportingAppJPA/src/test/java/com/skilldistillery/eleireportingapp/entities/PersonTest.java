package com.skilldistillery.eleireportingapp.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PersonTest {

	private static EntityManagerFactory emf;
	private static EntityManager em;
	private Person person;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("ELEIReportingAppJPA");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
		person = em.find(Person.class, 3);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		person = null;
	}

	@Test
	@DisplayName("test basic Person mappings")
	void test1() {

//		+----+------------+-------------+-----------+-------+------------+--------+------------+------+-------------------+-------+--------------+
//		| id | first_name | middle_name | last_name | title | birthdate  | gender | address_id | flag | description       | notes | ethnicity_id |
//		+----+------------+-------------+-----------+-------+------------+--------+------------+------+-------------------+-------+--------------+
//		|  1 | William    | Aaron       | Padget    | NULL  | 1988-06-27 | Male   |          1 |    0 | Police Supervisor | NULL  |            1 |
//		|  2 | Omar       | NULL        | Hernandez | jr    | 1995-03-15 | Male   |          1 |    0 | Police Officer    | NULL  |            3 |
//		|  3 | Steven     | Adam        | Burris    | NULL  | 1990-02-07 | Male   |          1 |    0 | Police Officer    | NULL  |            1 |
//		|  4 | John       | Adams       | Parker    | NULL  | 1992-07-01 | Male   |          2 |    0 | Contacted Person  | NULL  |            1 |
//		+----+------------+-------------+-----------+-------+------------+--------+------------+------+-------------------+-------+--------------+

		assertNotNull(person);
		assertEquals("Steven", person.getFirstName());
		assertEquals("Adam", person.getMiddleName());
		assertEquals("Burris", person.getLastName());
		assertNull(person.getTitle());
		assertEquals(person.getBirthDate(), LocalDate.parse("1990-02-07"));
		assertEquals(person.getGender(), "Male");
		assertFalse(person.isFlag());
		assertEquals(person.getDescription(), "Police Officer");
		assertNull(person.getNotes());
	}

	@Test
	@DisplayName("Testing Person m:m Address mapping")
	void test2() {

		person = null;
		person = em.find(Person.class, 1); // // had to use id 1 for this test due to the current limited DB entries
		
		assertNotNull(person);

//		SELECT COUNT(*) FROM address a JOIN person_address pa ON a.id = pa.address_id JOIN person p ON p.id = pa.person_id WHERE p.id = 1;
//		+----------+
//		| COUNT(*) |
//		+----------+
//		|        1 |
//		+----------+

		assertTrue(person.getAddresses().size() > 0);
		assertNotNull(person.getAddresses());
		assertTrue(person.getAddresses().size() == 1);

	}

}
