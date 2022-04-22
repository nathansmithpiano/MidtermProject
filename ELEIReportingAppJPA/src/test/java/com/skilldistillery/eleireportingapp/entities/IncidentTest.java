package com.skilldistillery.eleireportingapp.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;

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
		assertEquals(LocalDateTime.parse("2022-04-22T00:00"), incident.getIncidentDate());
	}

}
