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

class UserTest {
	
	private static  EntityManagerFactory emf;
	private static EntityManager em;
	private User user;

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
		user = em.find(User.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		user = null;
	}

	@Test
	@DisplayName("test basic User mappings")
	void test1() {
		
//		SELECT * FROM user u WHERE u.id = 1;
//		+----+-----------+------------------+------------------+------------+--------+
//		| id | person_id | permission_level | username         | password   | active |
//		+----+-----------+------------------+------------------+------------+--------+
//		|  1 |         1 | supervisor       | policesupervisor | supervisor |      1 |
//		+----+-----------+------------------+------------------+------------+--------+		
		
		assertNotNull(user);
		assertEquals("supervisor", user.getPermissionLevel());
		assertEquals("policesupervisor", user.getUsername());
		assertEquals("supervisor", user.getPassword());
		assertEquals(true, user.isActive());
	}

}
