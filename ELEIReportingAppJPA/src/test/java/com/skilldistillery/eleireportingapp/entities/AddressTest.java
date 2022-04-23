package com.skilldistillery.eleireportingapp.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AddressTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Address address;

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
	    address = em.find(Address.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
	    em.close();
	    address = null;
	}
	
	@Test
	@DisplayName("test basic Address mappings")
	void test1() {
		
//		SELECT * FROM address a WHERE a.id = 1;
//		+----+-------------+----------------------+---------+----------+------------+-------+------+
//		| id | description | street1              | street2 | city     | state_code | zip   | flag |
//		+----+-------------+----------------------+---------+----------+------------+-------+------+
//		|  1 | Employer    | 9551 Civic Center Dr |         | Thornton | CO         | 80229 |    0 |
//		+----+-------------+----------------------+---------+----------+------------+-------+------+
		
		assertNotNull(address);
		assertEquals("Employer", address.getDescription());
		assertEquals("9551 Civic Center Dr", address.getStreetOne());
		assertEquals("", address.getStreetTwo());
		assertEquals("Thornton", address.getCity());
		assertEquals("CO", address.getStateCode());
		assertEquals(80229, address.getZip());
		assertEquals(false, address.isFlag());
		
	}
	
	@Test
	@DisplayName("Testing Address 1:m Incident mapping")
	void test2() {
		
		address = null;
		address = em.find(Address.class, 3); // had to use id 3 for this test due to the current limited DB entries
		
		assertNotNull(address);
		
//		SELECT COUNT(*) FROM incident i JOIN address a ON a.id = i.address_id WHERE a.id = 3;
//		+----------+
//		| COUNT(*) |
//		+----------+
//		|        1 |
//		+----------+
		
		assertTrue(address.getIncidents().size() > 0);
		assertNotNull(address.getIncidents());
		assertTrue(address.getIncidents().size() == 1);
		
	}
	
	@Test
	@DisplayName("Testing Address m:m Person mapping")
	void test3() {
		
		assertNotNull(address);
		
//		SELECT COUNT(*) FROM person p JOIN person_address pa ON p.id = pa.person_id JOIN address a ON a.id = pa.address_id WHERE a.id = 1;
//		+----------+
//		| COUNT(*) |
//		+----------+
//		|        1 |
//		+----------+
		
		assertTrue(address.getPersons().size() > 0);
		assertNotNull(address.getPersons());
		assertTrue(address.getPersons().size() == 1);
		
	}
	
}
