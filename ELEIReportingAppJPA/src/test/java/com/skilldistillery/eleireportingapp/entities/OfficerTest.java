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
	
	@Test
	@DisplayName("testing Officer 1:1 Person mapping")
	void test2 (){
		
		assertNotNull(officer);
		
//		SELECT CONCAT(p.first_name, " ", p.last_name) FROM person p JOIN officer o ON o.person_id = p.id WHERE o.id
//				= 1;
//				+----------------------------------------+
//				| CONCAT(p.first_name, " ", p.last_name) |
//				+----------------------------------------+
//				| William  Padget                        |
//				+----------------------------------------+
		
		assertEquals("William  Padget", officer.getPerson().getFirstName() + " " + officer.getPerson().getLastName());
		
		
	}
	
	@Test
	@DisplayName("Testing Officer m:m Department mapping")
	void test3() {

		assertNotNull(officer);

//		SELECT COUNT(*) FROM department d JOIN department_employee de ON de.department_id = d.id JOIN officer o ON de.officer_id = o.id WHERE o.id = 1;
//		+----------+
//		| COUNT(*) |
//		+----------+
//		|        1 |
//		+----------+
		
		assertTrue(officer.getDepartments().size() > 0);
		assertNotNull(officer.getDepartments());
		assertTrue(officer.getDepartments().size() == 1);

	}

}
