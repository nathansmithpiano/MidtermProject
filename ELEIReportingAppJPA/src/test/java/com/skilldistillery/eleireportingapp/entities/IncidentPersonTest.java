package com.skilldistillery.eleireportingapp.entities;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class IncidentPersonTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private IncidentPerson IncidentPerson;

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
	    IncidentPerson = em.find(IncidentPerson.class, new IncidentPersonId(4, 1));
	}

	@AfterEach
	void tearDown() throws Exception {
	    em.close();
	    IncidentPerson = null;
	}
	
	@Test
	@DisplayName("test basic IncidentPerson mappings")
	void test1() {
		
//		SELECT * FROM incident_person ip WHERE (ip.incident_id = 1 && ip.person_id = 4);
//		+-----------+-------------+-----------------+-------------+-------------+-------------------------------------------------+
//		| person_id | incident_id | suspected_crime | age_minimum | age_maximum | description                                     |
//		+-----------+-------------+-----------------+-------------+-------------+-------------------------------------------------+
//		|         4 |           1 | Shoplift        |          20 |          25 | Suspect of shoplifting power tools from Walmart |
//		+-----------+-------------+-----------------+-------------+-------------+-------------------------------------------------+
		
		assertNotNull(IncidentPerson);
		assertNotNull(IncidentPerson.getIncidentPersonId());
		assertEquals(4, IncidentPerson.getIncidentPersonId().getPersonId());
		assertEquals(1, IncidentPerson.getIncidentPersonId().getIncidentId());
		assertEquals("Shoplift", IncidentPerson.getSuspectedCrime());
		assertEquals(20, IncidentPerson.getAgeMinimum());
		assertEquals(25, IncidentPerson.getAgeMaximum());
		assertEquals("Suspect of shoplifting power tools from Walmart", IncidentPerson.getDescription());
		
	}

}
