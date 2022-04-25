package com.skilldistillery.eleireportingapp.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;

import com.skilldistillery.eleireportingapp.entities.Incident;
import com.skilldistillery.eleireportingapp.entities.Incident;

@SpringBootTest
@EntityScan(basePackages = { "com.skilldistillery.eleireportingapp" })
class IncidentDAOTest {
	
	@Autowired
	private IncidentDAOImpl dao;
	
	private Incident entity;

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
		
//		 SELECT * FROM Incident WHERE id=1;
//		+----+------------+---------+-----------------------------+----------+---------------------+-------------+------+
//		| id | address_id | case_id | reason_for_contact          | location | incident_date       | description | flag |
//		+----+------------+---------+-----------------------------+----------+---------------------+-------------+------+
//		|  1 |          3 |       1 | Dispatched call for service | Walmart  | 2022-04-22 00:00:00 | Shoplift    |    0 |
//		+----+------------+---------+-----------------------------+----------+---------------------+-------------+------+
		
		assertNotNull(entity);
		assertEquals("Dispatched call for service", entity.getReasonForContact());
	}
	
	@Test
	@DisplayName("Testing DAO create and delete")
	void test3() {
		
//		SELECT * FROM Incident incident JOIN Address address ON incident.address_id = address.id WHERE incident.id = 1;
//		+----+------------+---------+-----------------------------+----------+---------------------+-------------+------+----+-------------+---------------+---------+-----------+------------+-------+------+
//		| id | address_id | case_id | reason_for_contact          | location | incident_date       | description | flag | id | description | street1       | street2 | city      | state_code | zip   | flag |
//		+----+------------+---------+-----------------------------+----------+---------------------+-------------+------+----+-------------+---------------+---------+-----------+------------+-------+------+
//		|  1 |          3 |       1 | Dispatched call for service | Walmart  | 2022-04-22 00:00:00 | Shoplift    |    0 |  3 | Business    | 9901 Grant St | NULL    | Thornton  | CO         | 80229 |    0 |
//		+----+------------+---------+-----------------------------+----------+---------------------+-------------+------+----+-------------+---------------+---------+-----------+------------+-------+------+
		
		//create
		Incident newEntity = new Incident();
		newEntity.setReasonForContact("Test");
		newEntity.setAddress(entity.getAddress());
		Incident createdEntity = dao.create(newEntity);
		assertNotNull(dao.findById(createdEntity.getId()));
		assertEquals("Test", createdEntity.getReasonForContact());
		assertNotNull(dao.findById(createdEntity.getId()).getAddress());
		assertEquals("9901 Grant St", dao.findById(createdEntity.getId()).getAddress().getStreetOne());
		
		//delete
		int size = dao.findAll().size();
		Incident backup = dao.delete(createdEntity.getId());
		assertNotNull(backup);
		assertNull(dao.findById(createdEntity.getId()));
		assertEquals(size - 1, dao.findAll().size());
	}

}
