package com.skilldistillery.eleireportingapp.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
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
		person = em.find(Person.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		person = null;
	}

	@Test
	void test_User_entity_mapping() {
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

}
