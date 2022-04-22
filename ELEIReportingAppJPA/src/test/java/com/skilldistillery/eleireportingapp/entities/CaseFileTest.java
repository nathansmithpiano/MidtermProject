package com.skilldistillery.eleireportingapp.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
		assertFalse(file.isFlag());
	}

}
