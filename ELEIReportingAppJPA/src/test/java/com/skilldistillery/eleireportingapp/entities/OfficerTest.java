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

class OfficerTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Officer officer;

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
	    officer = em.find(Officer.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
	    em.close();
	    officer = null;
	}
	
	@Test
	@DisplayName("test basic Officer mappings")
	void test1() {
		
//		SELECT * FROM officer o WHERE o.id = 1;
//		+----+-----------+---------------+-------+-----------+
//		| id | person_id | supervisor_id | badge | image_url |
//		+----+-----------+---------------+-------+-----------+
//		|  1 |         1 |          NULL | 2201  | NULL      |
//		+----+-----------+---------------+-------+-----------+
		
		assertNotNull(officer);
		assertEquals("2201", officer.getBadge());
		assertEquals(null, officer.getImageUrl());
		
	}

}
