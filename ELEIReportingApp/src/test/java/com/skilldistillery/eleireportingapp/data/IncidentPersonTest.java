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

import com.skilldistillery.eleireportingapp.entities.IncidentPerson;
import com.skilldistillery.eleireportingapp.entities.IncidentPersonId;
import com.skilldistillery.eleireportingapp.entities.Person;

@SpringBootTest
@EntityScan(basePackages = { "com.skilldistillery.eleireportingapp" })
class IncidentPersonTest {

	@Autowired
	private IncidentPersonDAOImpl dao;
	
	@Autowired
	private PersonDAOImpl personDao;
	
	@Autowired
	private IncidentDAOImpl incidentDao;
	
	private IncidentPerson entity;

	@BeforeEach
	void setUp() throws Exception {
	    entity = dao.findById(new IncidentPersonId(4, 1));
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
	@DisplayName("Testing DAO find id (4,1)")
	void test2() {
		
//		SELECT * FROM incident_person;
//		+-----------+-------------+---------------------+-------------+-------------+-------------------------------------------------+
//		| person_id | incident_id | suspected_crime     | age_minimum | age_maximum | description                                     |
//		+-----------+-------------+---------------------+-------------+-------------+-------------------------------------------------+
//		|         2 |           1 |                     |           1 |          50 | NULL                                            |
//		|         2 |           5 | NULL                |           1 |          50 | NULL                                            |
//		|         3 |           2 | Suspicious person   |           1 |          50 |                                                 |
//		|         3 |           3 | NULL                |           1 |          50 | NULL                                            |
//		|         3 |           4 | NULL                |           1 |          50 | NULL                                            |
//		|         4 |           1 | Shoplift            |          20 |          25 | Suspect of shoplifting power tools from Walmart |
//		|         4 |           3 | Shoplift            |          20 |          25 | Suspect of shoplifting power tools              |
//		|         6 |           2 | Suspicious person   |          35 |          45 | person digging in trash                         |
//		|         7 |           5 | Suspicous person    |          25 |          35 | matched description of suspect                  |
//		|         8 |           4 | Stop Sign vioaltion |          30 |          40 | person ran a stop sign                          |
//		+-----------+-------------+---------------------+-------------+-------------+-------------------------------------------------+
		
		assertNotNull(entity);
		assertEquals("Shoplift", entity.getSuspectedCrime());
		
	}
	
	@Test
	@DisplayName("Testing DAO create and delete")
	void test3() {
		
		//create
		IncidentPerson newEntity = new IncidentPerson();
		IncidentPersonId newIncidentPersonId = new IncidentPersonId(1, 1);
		newEntity.setIncidentPersonId(newIncidentPersonId);
		newEntity.setSuspectedCrime("Test");
		IncidentPerson createdEntity = dao.create(newEntity);
		assertNotNull(dao.findById(createdEntity.getIncidentPersonId()));
		assertEquals("Test", createdEntity.getSuspectedCrime());
		assertEquals(1, createdEntity.getIncidentPersonId().getPersonId());
		assertEquals(1, createdEntity.getIncidentPersonId().getIncidentId());
		
		//delete
		int size = dao.findAll().size();
		IncidentPerson backup = dao.delete(createdEntity.getIncidentPersonId());
		assertNotNull(backup);
		assertNull(dao.findById(createdEntity.getIncidentPersonId()));
		assertEquals(size - 1, dao.findAll().size());
	}
	
	@Test
	@DisplayName("Testing DAO update and restore")
	void test4() {
		String oldSuspectedCrime = entity.getSuspectedCrime();
		entity.setSuspectedCrime("Updated");
		IncidentPerson updated = dao.update(entity.getIncidentPersonId(), entity);
		assertNotNull(updated);
		assertEquals(4, updated.getIncidentPersonId().getPersonId());
		assertEquals(1, updated.getIncidentPersonId().getIncidentId());
		assertEquals("Updated", updated.getSuspectedCrime());
		entity.setSuspectedCrime(oldSuspectedCrime);
		updated = dao.update(entity.getIncidentPersonId(), entity);
		assertNotNull(updated);
		assertEquals(4, updated.getIncidentPersonId().getPersonId());
		assertEquals(1, updated.getIncidentPersonId().getIncidentId());
		assertEquals(oldSuspectedCrime, updated.getSuspectedCrime());
	}
	
	@Test
	@DisplayName("Testing DAO findByAgeRange")
	void test5() {
		
//		SELECT * FROM incident_person WHERE age_minimum >= 1 AND age_maximum <= 100;
//		+-----------+-------------+---------------------+-------------+-------------+-------------------------------------------------+
//		| person_id | incident_id | suspected_crime     | age_minimum | age_maximum | description                                     |
//		+-----------+-------------+---------------------+-------------+-------------+-------------------------------------------------+
//		|         2 |           1 |                     |           1 |          50 | NULL                                            |
//		|         2 |           5 | NULL                |           1 |          50 | NULL                                            |
//		|         3 |           2 | Suspicious person   |           1 |          50 |                                                 |
//		|         3 |           3 | NULL                |           1 |          50 | NULL                                            |
//		|         3 |           4 | NULL                |           1 |          50 | NULL                                            |
//		|         4 |           1 | Shoplift            |          20 |          25 | Suspect of shoplifting power tools from Walmart |
//		|         4 |           3 | Shoplift            |          20 |          25 | Suspect of shoplifting power tools              |
//		|         6 |           2 | Suspicious person   |          35 |          45 | person digging in trash                         |
//		|         7 |           5 | Suspicous person    |          25 |          35 | matched description of suspect                  |
//		|         8 |           4 | Stop Sign vioaltion |          30 |          40 | person ran a stop sign                          |
//		+-----------+-------------+---------------------+-------------+-------------+-------------------------------------------------+
		assertNotNull(dao.findByAgeRange(1, 100));
		assertTrue(dao.findByAgeRange(1, 100).size() > 0);
	}
	
}
