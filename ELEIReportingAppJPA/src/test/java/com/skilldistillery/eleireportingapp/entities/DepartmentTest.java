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

class DepartmentTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Department department;

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
	    department = em.find(Department.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
	    em.close();
	    department = null;
	}
	
	@Test
	@DisplayName("test basic Department mappings")
	void test1() {
		
//		SELECT * FROM department d WHERE d.id = 1;
//		+----+------------+---------------------+
//		| id | address_id | name                |
//		+----+------------+---------------------+
//		|  1 |          1 | SKILL DISTILLERY PD |
//		+----+------------+---------------------+
		
		assertNotNull(department);
		assertEquals("SKILL DISTILLERY PD", department.getName());
		
	}
	
	@Test
	@DisplayName("testing Department 1:1 Address mapping")
	void test2 (){
		
		assertNotNull(department);
		
//		SELECT a.street1 FROM address a JOIN department d ON d.address_id = a.id WHERE d.id = 1;
//		+----------------------+
//		| street1              |
//		+----------------------+
//		| 9551 Civic Center Dr |
//		+----------------------+
		
		assertEquals("9551 Civic Center Dr", department.getAddress().getStreetOne());
		
		
	}

}
