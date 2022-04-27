package com.skilldistillery.eleireportingapp.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class IncidentTest {
	
	private static  EntityManagerFactory emf;
	private static EntityManager em;
	private Incident incident;

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
		incident = em.find(Incident.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		incident = null;
	}

	@Test
	@DisplayName("test basic Incident mappings")
	void test1() {
		
//		+----+-----------------------------+----------+------------+---------+-------------+------+---------------------+
//		| id | reason_for_contact          | location | address_id | case_id | description | flag | incident_date       |
//		+----+-----------------------------+----------+------------+---------+-------------+------+---------------------+
//		|  1 | Dispatched call for service | Walmart  |          3 |       1 | Shoplift    |    0 | 2022-04-22 00:00:00 |
//		+----+-----------------------------+----------+------------+---------+-------------+------+---------------------+
		
		assertNotNull(incident);
		assertEquals("Dispatched call for service", incident.getReasonForContact());
		assertEquals("Walmart", incident.getLocation());
		assertEquals("Shoplift", incident.getDescription());
		assertFalse(incident.isFlag());
		assertEquals(Timestamp.valueOf("2022-04-22 00:00:00"), incident.getIncidentDate());
	}
	
	@Test
	@DisplayName("Testing Incident m:1 Address mapping")
	void test2() {
		
		assertNotNull(incident);
		
//		SELECT a.street1 FROM address a JOIN incident i ON i.address_id = a.id WHERE i.id = 1;
//		+---------------+
//		| street1       |
//		+---------------+
//		| 9901 Grant St |
//		+---------------+
		
		assertEquals("9901 Grant St", incident.getAddress().getStreetOne());
		
	}
	
	@Test
	@DisplayName("Testing Incident m:1 CaseFile mapping")
	void test3() {
		
		assertNotNull(incident);
		
//		SELECT cf.suspected_crime FROM case_file cf JOIN incident i ON i.case_id = cf.id WHERE i.id = 1;
//		+-----------------+
//		| suspected_crime |
//		+-----------------+
//		| Shoplifting     |
//		+-----------------+		
		
		assertEquals("Shoplifting", incident.getCaseFile().getSuspectedCrime());
		
	}
	
	@Test
	@DisplayName("Testing Incident m:m Person mapping")
	void test4() {
		
		assertNotNull(incident);
		
//		SELECT COUNT(*) FROM incident i JOIN incident_person ip ON i.id = ip.incident_id JOIN person p ON ip.person_id = p.id;
//		+----------+
//		| COUNT(*) |
//		+----------+
//		|       10 |
//		+----------+
		
		assertTrue(incident.getPersons().size() > 0);
		assertNotNull(incident.getPersons());
//		assertTrue(incident.getPersons().size() == 2);
		
	}
	
	@Test
	@DisplayName("Testing Incident m:m Note mapping")
	void test5() {
		
		assertNotNull(incident);
		
//		SELECT COUNT(*) FROM note n JOIN incident_note inc ON inc.note_id = n.id JOIN incident i ON inc.incident_id = i.id WHERE i.id = 1;
//		+----------+
//		| COUNT(*) |
//		+----------+
//		|        1 |
//		+----------+
		
		assertTrue(incident.getNotes().size() > 0);
		assertNotNull(incident.getNotes());
		assertTrue(incident.getNotes().size() == 1);
		
	}
	
	@Test
	@DisplayName("Testing Incident m:1 Department mapping")
	void test6() {
		
		assertNotNull(incident);
		assertNotNull(incident.getDepartment());
		assertEquals(1, incident.getDepartment().getId());
	}

	@Test
	@DisplayName("Testing Incident m:1 Officer mapping")
	void test7() {
		
		assertNotNull(incident);
		assertNotNull(incident.getOfficer());
		assertEquals(2, incident.getOfficer().getId());
	}
}
