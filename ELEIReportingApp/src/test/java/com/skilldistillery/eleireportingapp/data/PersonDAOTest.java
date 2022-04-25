package com.skilldistillery.eleireportingapp.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;

import com.skilldistillery.eleireportingapp.entities.Person;

@SpringBootTest
@EntityScan(basePackages = { "com.skilldistillery.eleireportingapp" })
class PersonDAOTest {

	@Autowired
	private PersonDAOImpl dao;

	private Person entity;

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
	@DisplayName("Testing DAO findByFirstName")
	void test2() {

//		SELECT * FROM person p WHERE p.id = 1;
//		+----+--------------+------------+-------------+-----------+-------+------------+--------+-------------------+------+
//		| id | ethnicity_id | first_name | middle_name | last_name | title | birthdate  | gender | description       | flag |
//		+----+--------------+------------+-------------+-----------+-------+------------+--------+-------------------+------+
//		|  1 |            1 | William    | Aaron       | Padget    | NULL  | 1988-06-27 | Male   | Police Supervisor |    0 |
//		+----+--------------+------------+-------------+-----------+-------+------------+--------+-------------------+------+
		
		assertNotNull(dao);
		assertEquals(entity, dao.findByFirstName("William").get(0));
//		assertEquals(dao.findById(5), dao.findByFirstName("Morty").get(0));
	}
	
	@Test
	@DisplayName("Testing DAO findByMiddleName")
	void test3() {

//		SELECT * FROM person p WHERE p.id = 1;
//		+----+--------------+------------+-------------+-----------+-------+------------+--------+-------------------+------+
//		| id | ethnicity_id | first_name | middle_name | last_name | title | birthdate  | gender | description       | flag |
//		+----+--------------+------------+-------------+-----------+-------+------------+--------+-------------------+------+
//		|  1 |            1 | William    | Aaron       | Padget    | NULL  | 1988-06-27 | Male   | Police Supervisor |    0 |
//		+----+--------------+------------+-------------+-----------+-------+------------+--------+-------------------+------+
		
		assertNotNull(dao);
		assertEquals(entity, dao.findByMiddleName("Aaron").get(0));
	}
	
	@Test
	@DisplayName("Testing DAO findByLastName")
	void test4() {

//		SELECT * FROM person p WHERE p.id = 1;
//		+----+--------------+------------+-------------+-----------+-------+------------+--------+-------------------+------+
//		| id | ethnicity_id | first_name | middle_name | last_name | title | birthdate  | gender | description       | flag |
//		+----+--------------+------------+-------------+-----------+-------+------------+--------+-------------------+------+
//		|  1 |            1 | William    | Aaron       | Padget    | NULL  | 1988-06-27 | Male   | Police Supervisor |    0 |
//		+----+--------------+------------+-------------+-----------+-------+------------+--------+-------------------+------+
		
		assertNotNull(dao);
		assertEquals(entity, dao.findByLastName("Padget").get(0));
	}
	
	@Test
	@DisplayName("Testing DAO findByTitle")
	void test5() {

//		SELECT * FROM person p WHERE p.title = "jr";
//		+----+--------------+------------+-------------+-----------+-------+------------+--------+------------------+------+
//		| id | ethnicity_id | first_name | middle_name | last_name | title | birthdate  | gender | description      | flag |
//		+----+--------------+------------+-------------+-----------+-------+------------+--------+------------------+------+
//		|  2 |            3 | Omar       | NULL        | Hernandez | jr    | 1995-03-15 | Male   | Police Officer   |    0 |
//		| 11 |            1 | Mark       | NULL        | Thronson  | jr    | 1990-09-10 | Male   | Contacted Person |    0 |
//		+----+--------------+------------+-------------+-----------+-------+------------+--------+------------------+------+--+--------------+------------+-------------+-----------+-------+------------+--------+----------------+------+
		
		assertNotNull(dao);
		entity = dao.findById(2);
		assertEquals(entity, dao.findByTitle("jr").get(0));
		entity = dao.findById(11);
		assertEquals(entity, dao.findByTitle("jr").get(1));
	}
	
	@Test
	@DisplayName("Testing DAO findByBirthDate")
	void test6() {

//		SELECT * FROM person p WHERE p.id = 1;
//		+----+--------------+------------+-------------+-----------+-------+------------+--------+-------------------+------+
//		| id | ethnicity_id | first_name | middle_name | last_name | title | birthdate  | gender | description       | flag |
//		+----+--------------+------------+-------------+-----------+-------+------------+--------+-------------------+------+
//		|  1 |            1 | William    | Aaron       | Padget    | NULL  | 1988-06-27 | Male   | Police Supervisor |    0 |
//		+----+--------------+------------+-------------+-----------+-------+------------+--------+-------------------+------+
		
		assertNotNull(dao);
		assertEquals(entity, dao.findByBirthdate(LocalDate.of(1988, 6, 27)).get(0));
	}
	
	@Test
	@DisplayName("Testing DAO findByBirthdateRange")
	void test7() {

//		SELECT * FROM person p WHERE p.birthdate BETWEEN '1995-01-01' AND '1995-12-31';
//		+----+--------------+------------+-------------+-----------+-------+------------+--------+------------------+------+
//		| id | ethnicity_id | first_name | middle_name | last_name | title | birthdate  | gender | description      | flag |
//		+----+--------------+------------+-------------+-----------+-------+------------+--------+------------------+------+
//		|  2 |            3 | Omar       | NULL        | Hernandez | jr    | 1995-03-15 | Male   | Police Officer   |    0 |
//		|  7 |            1 | Summer     | NULL        | Smith     | NULL  | 1995-03-22 | Female | Contacted Person |    0 |
//		+----+--------------+------------+-------------+-----------+-------+------------+--------+------------------+------+
		
		assertNotNull(dao);
		assertEquals(dao.findById(2), dao.findByBirthdateRange(LocalDate.of(1995, 1, 1), LocalDate.of(1995, 12, 31)).get(0));
		assertEquals(dao.findById(7), dao.findByBirthdateRange(LocalDate.of(1995, 1, 1), LocalDate.of(1995, 12, 31)).get(1));
	}
	
	@Test
	@DisplayName("Testing DAO findByGender")
	void test8() {

//		SELECT * FROM person p WHERE p.gender = "female";
//		+----+--------------+------------+-------------+------------+-------+------------+--------+------------------+------+
//		| id | ethnicity_id | first_name | middle_name | last_name  | title | birthdate  | gender | description      | flag |
//		+----+--------------+------------+-------------+------------+-------+------------+--------+------------------+------+
//		|  7 |            1 | Summer     | NULL        | Smith      | NULL  | 1995-03-22 | Female | Contacted Person |    0 |
//		|  8 |            1 | Tammy      | NULL        | Guetermann | NULL  | 1982-02-01 | Female | Contacted Person |    0 |
//		+----+--------------+------------+-------------+------------+-------+------------+--------+------------------+------+----+--------------+------------+-------------+-----------+-------+------------+--------+------------------+------+--+--------------+------------+-------------+-----------+-------+------------+--------+----------------+------+
		
		assertNotNull(dao);
		assertEquals(dao.findById(7), dao.findByGender("female").get(0));
		assertEquals(dao.findById(8), dao.findByGender("female").get(1));
	}
	
	@Test
	@DisplayName("Testing DAO findByFlag")
	void test9() {
		
//		SELECT * FROM person p WHERE p.id = 1;
//		+----+--------------+------------+-------------+-----------+-------+------------+--------+-------------------+------+
//		| id | ethnicity_id | first_name | middle_name | last_name | title | birthdate  | gender | description       | flag |
//		+----+--------------+------------+-------------+-----------+-------+------------+--------+-------------------+------+
//		|  1 |            1 | William    | Aaron       | Padget    | NULL  | 1988-06-27 | Male   | Police Supervisor |    0 |
//		+----+--------------+------------+-------------+-----------+-------+------------+--------+-------------------+------+

		assertNotNull(dao);
		assertTrue(dao.findByFlag(false).size() > 0);
		assertEquals(entity, dao.findByFlag(false).get(0));
//		assertTrue(dao.findByFlag(false).size() == 11);
	}
	

}
