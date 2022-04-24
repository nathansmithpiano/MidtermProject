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

class IncidentWithPersonTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private IncidentWithPerson incidentWithPerson;

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
	    incidentWithPerson = em.find(IncidentWithPerson.class, new IncidentWithPersonId(4, 1));
	}

	@AfterEach
	void tearDown() throws Exception {
	    em.close();
	    incidentWithPerson = null;
	}
	
	@Test
	@DisplayName("test basic IncidentWithPerson mappings")
	void test1() {
		
//		SELECT * FROM incident_with_person iwp WHERE (iwp.incident_id = 1 && iwp.person_id = 4);
//		+-----------+-------------+-----------------+-------------+-------------+-------+-------------------------------------------------+
//		| person_id | incident_id | suspected_crime | age_minimum | age_maximum | notes | description                                     |
//		+-----------+-------------+-----------------+-------------+-------------+-------+-------------------------------------------------+
//		|         4 |           1 | Shoplift        |          20 |          25 | NULL  | Suspect of shoplifting power tools from Walmart |
//		+-----------+-------------+-----------------+-------------+-------------+-------+-------------------------------------------------+
		
		assertNotNull(incidentWithPerson);
		assertNotNull(incidentWithPerson.getIncidentWithPersonId());
		assertEquals(4, incidentWithPerson.getIncidentWithPersonId().getPersonId());
		assertEquals(1, incidentWithPerson.getIncidentWithPersonId().getIncidentId());
		assertEquals("Shoplift", incidentWithPerson.getSuspectedCrime());
		assertEquals(20, incidentWithPerson.getAgeMinimum());
		assertEquals(25, incidentWithPerson.getAgeMaximum());
		assertEquals("Suspect of shoplifting power tools from Walmart", incidentWithPerson.getDescription());
		
	}

}
