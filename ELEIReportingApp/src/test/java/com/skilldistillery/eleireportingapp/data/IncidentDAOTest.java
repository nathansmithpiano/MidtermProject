package com.skilldistillery.eleireportingapp.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Timestamp;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;

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
	
	@Test
	@DisplayName("Testing DAO update and restore")
	void test4() {
		String oldReasonForContact = entity.getReasonForContact();
		entity.setReasonForContact("Updated");
		Incident updated = dao.update(1, entity);
		assertNotNull(updated);
		assertEquals(1, updated.getId());
		assertEquals("Updated", updated.getReasonForContact());
		entity.setReasonForContact(oldReasonForContact);
		updated = dao.update(1, entity);
		assertNotNull(updated);
		assertEquals(1, updated.getId());
		assertEquals(oldReasonForContact, updated.getReasonForContact());
	}
	
	@Test
	@DisplayName("Testing DAO findByIncidentDate")
	void test5() {
		assertNotNull(dao.findByIncidentDate(entity.getIncidentDate()));
		assertTrue(dao.findByIncidentDate(entity.getIncidentDate()).size() > 0);
		assertEquals(entity.getIncidentDate(), dao.findByIncidentDate(entity.getIncidentDate()).get(0).getIncidentDate());
	}
	
	@Test
	@DisplayName("Testing DAO findByIncidentDateRange")
	void test6() {
		Timestamp start = Timestamp.valueOf("1990-01-01 00:00:00");
		Timestamp end = Timestamp.valueOf("2100-01-01 00:00:00");
		assertNotNull(dao.findByIncidentDateRange(start, end));
		assertTrue(dao.findByIncidentDateRange(start, end).size() > 0);
	}
	
	@Test
	@DisplayName("Testing DAO findByStatus")
	void test7() {
		assertNotNull(dao.findByStatus(false));
		assertTrue(dao.findByStatus(false).size() > 0);
	}
	
	@Test
	@DisplayName("Testing DAO findAll")
	void test8() {
		assertNotNull(dao.findAll());
		assertTrue(dao.findAll().size() > 0);
	}
}
