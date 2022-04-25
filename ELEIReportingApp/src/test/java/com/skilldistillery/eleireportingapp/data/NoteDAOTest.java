package com.skilldistillery.eleireportingapp.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Timestamp;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;

import com.skilldistillery.eleireportingapp.entities.Note;


@SpringBootTest
@EntityScan(basePackages = {"com.skilldistillery.eleireportingapp"})
class NoteDAOTest {

	
	@Autowired
	private NoteDAOImpl dao;
	
	private Note entity;
	
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
		
//		SELECT * FROM note WHERE id=1;
//		+----+---------+---------+---------+----------+
//		| id | user_id | created | updated | content  |
//		+----+---------+---------+---------+----------+
//		|  1 |       1 | NULL    | NULL    | CONTENT1 |
//		+----+---------+---------+---------+----------+
		
		assertNotNull(entity);
		assertEquals("CONTENT1", entity.getContent());
	}
	
	@Test
	@DisplayName("Testing DAO range of notes created")
	void test3() {
		/*
		 * SELECT * FROM note WHERE note.created BETWEEN '2022-04-22 00:00:01' AND '2022-04-22 23:59:59';
+----+---------+---------------------+---------+----------+
| id | user_id | created             | updated | content  |
+----+---------+---------------------+---------+----------+
|  1 |       1 | 2022-04-22 12:00:01 | NULL    | CONTENT1 |
+----+---------+---------------------+---------+----------+
		 */
		Timestamp start = Timestamp.valueOf("2022-04-21 00:00:00");
		Timestamp end = Timestamp.valueOf("2022-04-23 00:00:00");
		assertNotNull(dao.findByCreatedRange(start, end));
		assertTrue(dao.findByCreatedRange(start, end).size() > 0);
		
	}
	
	@Test
	@DisplayName("Testing DAO range of notes updated")
	void test4() {
		/*
		 * SELECT * FROM note WHERE note.updated BETWEEN '2022-04-22 00:00:01' AND '2022-04-22 23:59:59';
+----+---------+---------------------+---------------------+----------+
| id | user_id | created             | updated             | content  |
+----+---------+---------------------+---------------------+----------+
|  1 |       1 | 2022-04-22 12:00:01 | 2022-04-22 12:27:00 | CONTENT1 |
+----+---------+---------------------+---------------------+----------+
		 */
		Timestamp start = Timestamp.valueOf("2022-04-21 00:00:00");
		Timestamp end = Timestamp.valueOf("2022-04-23 00:00:00");
		assertNotNull(dao.findByUpdatedRange(start, end));
		assertTrue(dao.findByUpdatedRange(start, end).size() > 0);
	}
	
	@Test
	@DisplayName("Testing DAO range of notes created")
	void test5() {
		/*
		 * SELECT * FROM note JOIN user ON note.user_id = 1;
+----+---------+---------------------+---------------------+----------+----+-----------+------------------+------------------+------------+--------+
| id | user_id | created             | updated             | content  | id | person_id | permission_level | username         | password   | active |
+----+---------+---------------------+---------------------+----------+----+-----------+------------------+------------------+------------+--------+
|  1 |       1 | 2022-04-22 12:00:01 | 2022-04-22 12:27:00 | CONTENT1 |  1 |         1 | supervisor       | policesupervisor | supervisor |      1 |
|  1 |       1 | 2022-04-22 12:00:01 | 2022-04-22 12:27:00 | CONTENT1 |  2 |         2 | officer          | policeofficer    | officer    |      1 |
+----+---------+---------------------+---------------------+----------+----+-----------+------------------+------------------+------------+--------+
		 */
		assertNotNull(dao);
		assertTrue(dao.findAll().size() > 0);
	}
}
