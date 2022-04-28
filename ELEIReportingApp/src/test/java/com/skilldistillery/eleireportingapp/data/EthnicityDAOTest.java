package com.skilldistillery.eleireportingapp.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;

import com.skilldistillery.eleireportingapp.entities.Ethnicity;

@SpringBootTest
@EntityScan(basePackages = { "com.skilldistillery.eleireportingapp" })
class EthnicityDAOTest {
	
	@Autowired
	private EthnicityDAOImpl dao;
	
	private Ethnicity entity;
	
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
		
//		SELECT * FROM ethnicity WHERE id=1;
//		+----+-------+
//		| id | name  |
//		+----+-------+
//		|  1 | White |
//		+----+-------+
		
		assertNotNull(entity);
		assertEquals("White", entity.getName());
	}
	
	@Test
	@DisplayName("Testing DAO create and delete")
	void test3() {
		//create
		Ethnicity newEntity = new Ethnicity();
		newEntity.setName("Name");
		Ethnicity createdEntity = dao.create(newEntity);
		assertNotNull(dao.findById(createdEntity.getId()));
		assertEquals("Name", createdEntity.getName());
		
		//delete
		int size = dao.findAll().size();
		Ethnicity backup = dao.delete(createdEntity.getId());
		assertNotNull(backup);
		assertNull(dao.findById(createdEntity.getId()));
		assertEquals(size - 1, dao.findAll().size());
	}
	
	@Test
	@DisplayName("Testing DAO update and restore")
	void test4() {
		String oldName = entity.getName();
		entity.setName("Updated");
		Ethnicity updated = dao.update(1, entity);
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
	@DisplayName("Testing DAO Person stuff")
	void test5() {
//		assertTrue(entity.getPersons().size() > 0);
//		assertNotNull(entity.getPersons());
	}
	
}
