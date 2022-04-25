package com.skilldistillery.eleireportingapp.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;

import com.skilldistillery.eleireportingapp.entities.User;

@SpringBootTest
@EntityScan(basePackages = { "com.skilldistillery.eleireportingapp" })
class UserDAOTest {

	@Autowired
	private UserDAOImpl dao;

	private User entity;

	@BeforeEach
	void setUp() throws Exception {
		entity = dao.findById(1);
	}

	@AfterEach
	void tearDown() throws Exception {
		entity = null;
	}

	@Test
	@DisplayName("Testing DAO was autowired")
	void test1() {
		assertNotNull(dao);
	}

	@Test
	@DisplayName("Testing DAO findByPermissionLevel")
	void test2() {

//		SELECT * FROM user u WHERE u.permission_level = "supervisor";
//		+----+-----------+------------------+------------------+------------+--------+
//		| id | person_id | permission_level | username         | password   | active |
//		+----+-----------+------------------+------------------+------------+--------+
//		|  1 |         1 | supervisor       | policesupervisor | supervisor |      1 |
//		+----+-----------+------------------+------------------+------------+--------+----+-----------+------------------+------------------+------------+--------+----+--------------+------------+-------------+-----------+-------+------------+--------+-------------------+------+
		assertNotNull(dao);
		assertEquals("supervisor", entity.getPermissionLevel());
		assertEquals(entity, dao.findByPermissionLevel("supervisor").get(0));
	}
	
	@Test
	@DisplayName("Testing DAO findByUserName")
	void test3() {
		
//		SELECT * FROM user;
//		+----+-----------+------------------+------------------+------------+--------+
//		| id | person_id | permission_level | username         | password   | active |
//		+----+-----------+------------------+------------------+------------+--------+
//		|  1 |         1 | supervisor       | policesupervisor | supervisor |      1 |
//		|  2 |         2 | officer          | policeofficer    | officer    |      1 |
//		+----+-----------+------------------+------------------+------------+--------+

		assertNotNull(dao);
		assertEquals("policesupervisor", entity.getUsername());
		assertEquals(entity, dao.findByUserName("policesupervisor"));
	}
	
	@Test
	@DisplayName("Testing DAO findByStatus")
	void test4() {
		
//		SELECT * FROM user;
//		+----+-----------+------------------+------------------+------------+--------+
//		| id | person_id | permission_level | username         | password   | active |
//		+----+-----------+------------------+------------------+------------+--------+
//		|  1 |         1 | supervisor       | policesupervisor | supervisor |      1 |
//		|  2 |         2 | officer          | policeofficer    | officer    |      1 |
//		+----+-----------+------------------+------------------+------------+--------+

		assertNotNull(dao);
		assertEquals(1, entity.getId());
		assertEquals(entity, dao.findByStatus(true).get(0));
//		assertEquals(2, dao.findByStatus(true).get(1).getId());
//		assertTrue(dao.findByStatus(false).isEmpty());
	}

}
