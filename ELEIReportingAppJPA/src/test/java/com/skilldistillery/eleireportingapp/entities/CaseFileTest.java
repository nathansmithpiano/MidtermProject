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

class CaseFileTest {
	
	private static  EntityManagerFactory emf;
	private static EntityManager em;
	private CaseFile file;

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
		file = em.find(CaseFile.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		file = null;
	}

	@Test
	@DisplayName("test basic CaseFile mappings")
	void test1() {
		
//		+----+-----------------+-------------+------+
//		| id | suspected_crime | description | flag |
//		+----+-----------------+-------------+------+
//		|  1 | Shoplifting     | Shoplifing  |    0 |
//		+----+-----------------+-------------+------+
		
		assertNotNull(file);
		assertEquals(file.getSuspectedCrime(), "Shoplifting");
		assertEquals(file.getDescription(), "Shoplifing");
		assertTrue(file.isFlag());
	}
	
	@Test
	@DisplayName("test Casefile m:m Note mappings")
	void test2() {
		assertNotNull(file);
		assertNotNull(file.getNotes());
		assertEquals(1, file.getNotes().size());
		assertEquals(1, file.getNotes().get(0).getId());
	}
	
	@Test
	@DisplayName("Testing CaseFile 1:m Incident mapping")
	void test3() {
		
		assertNotNull(file);
		
//		SELECT COUNT(*) FROM incident i JOIN case_file cf ON i.case_id = cf.id WHERE cf.id = 1;
//		+----------+
//		| COUNT(*) |
//		+----------+
//		|        2 |
//		+----------+
		
		assertTrue(file.getIncidents().size() > 0);
		assertNotNull(file.getIncidents());
		assertTrue(file.getIncidents().size() == 2);
		
	}

}
