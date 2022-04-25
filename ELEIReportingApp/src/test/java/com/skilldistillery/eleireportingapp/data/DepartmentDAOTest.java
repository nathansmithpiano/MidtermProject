package com.skilldistillery.eleireportingapp.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;

import com.skilldistillery.eleireportingapp.entities.Department;

@SpringBootTest
@EntityScan(basePackages = { "com.skilldistillery.eleireportingapp" })
class DepartmentDAOTest {
	
	@Autowired
	private DepartmentDAOImpl dao;
	
	private Department entity;
	
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
	@DisplayName("Testing DAO find id 1")
	void test2() {
		
//		SELECT * FROM Department WHERE id=1;
//		+----+------------+---------------------+
//		| id | address_id | name                |
//		+----+------------+---------------------+
//		|  1 |          1 | SKILL DISTILLERY PD |
//		+----+------------+---------------------+
		
		assertNotNull(entity);
		assertEquals("SKILL DISTILLERY PD", entity.getName());
	}
	
	@Test
	@DisplayName("Testing DAO create and delete")
	void test3() {
		//create
		Department newEntity = new Department();
		newEntity.setName("Name");
		newEntity.setAddress(entity.getAddress());
		Department createdEntity = dao.create(newEntity);
		assertNotNull(dao.findById(createdEntity.getId()));
		assertEquals("Name", createdEntity.getName());
		assertNotNull(dao.findById(createdEntity.getId()).getAddress());
		assertEquals("9551 Civic Center Dr", dao.findById(createdEntity.getId()).getAddress().getStreetOne());
		
		//delete
		int size = dao.findAll().size();
		Department backup = dao.delete(createdEntity.getId());
		assertNotNull(backup);
		assertNull(dao.findById(createdEntity.getId()));
		assertEquals(size - 1, dao.findAll().size());
	}
	
	@Test
	@DisplayName("Testing DAO update and restore")
	void test4() {
		String oldName = entity.getName();
		entity.setName("Updated");
		Department updated = dao.update(1, entity);
		assertNotNull(updated);
		assertEquals(1, updated.getId());
		assertEquals("Updated", updated.getName());
		entity.setName(oldName);
		updated = dao.update(1, entity);
		assertNotNull(updated);
		assertEquals(1, updated.getId());
		assertEquals(oldName, updated.getName());
	}
	
	@Test
	@DisplayName("Testing DAO findByName")
	void test5() {
		Department test = dao.findByName("SKILL DISTILLERY PD");
		assertNotNull(test);
		assertEquals(1, test.getId());
		assertEquals("SKILL DISTILLERY PD", test.getName());
//		assertNotNull(test.getAddress());
//		assertTrue(test.getOfficers().size() > 0);
//		assertNotNull(test.getOfficers());
//		assertEquals("123", test.getOfficers().get(1).getBadge());
	}
	
}
