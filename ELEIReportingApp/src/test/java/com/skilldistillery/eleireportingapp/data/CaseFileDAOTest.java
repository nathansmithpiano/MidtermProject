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

import com.skilldistillery.eleireportingapp.entities.CaseFile;

@SpringBootTest
@EntityScan(basePackages = {"com.skilldistillery.eleireportingapp"})
class CaseFileDAOTest {

	
	@Autowired
	private CaseFileDAOImpl dao;
	
	private CaseFile entity;
	
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
	@DisplayName("test case_file find by ID")
	void test2() {
		
//		SELECT * FROM case_file WHERE case_file.id =1;
//		+----+-----------------+-------------+------+
//		| id | suspected_crime | description | flag |
//		+----+-----------------+-------------+------+
//		|  1 | Shoplifting     | Shoplifing  |    0 |
//		+----+-----------------+-------------+------+
		
		assertNotNull(dao);
		assertEquals("Shoplifing", entity.getDescription());
	}
	
	@Test
	@DisplayName("test case_file find case by case number range")
	void test3() {
		
//		SELECT * FROM case_file WHERE case_file.id BETWEEN 0 AND 2;
//		+----+-----------------+-------------+------+
//		| id | suspected_crime | description | flag |
//		+----+-----------------+-------------+------+
//		|  1 | Shoplifting     | Shoplifing  |    0 |
//		+----+-----------------+-------------+------+
		
		assertNotNull(dao);
		assertTrue(dao.findByCaseNumberRange(0, 2).size() > 0);
		
	}

}
