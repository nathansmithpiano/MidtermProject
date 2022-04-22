package com.skilldistillery.eleireportingapp.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

class EthnicityTest {
	
	private static  EntityManagerFactory emf;
	private static EntityManager em;
	private Ethnicity ethnicity;

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
		ethnicity = em.find(Ethnicity.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		ethnicity = null;
	}

	@Test
	@DisplayName("test basic Ethnicity mappings")
	void test1() {
		
//		+----+------------------+
//		| id | name             |
//		+----+------------------+
//		|  1 | White            |
//		|  2 | Black            |
//		|  3 | Hispanic         |
//		|  4 | Asian            |
//		|  5 | American Indian  |
//		|  6 | Pacific Islander |
//		|  7 | Other            |
//		+----+------------------+
		
		assertNotNull(ethnicity);
		assertEquals(ethnicity.getName(), "White");
	}

}
