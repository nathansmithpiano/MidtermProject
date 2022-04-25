package com.skilldistillery.eleireportingapp.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NoteTest {

	private static EntityManagerFactory emf;
	private static EntityManager em;
	private Note note;

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
		note = em.find(Note.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		note = null;
	}

	@Test
	@DisplayName("test basic Note mappings")
	void test1() {

//		FROM Note WHERE id=1;
//		+----+---------+---------+---------+----------+
//		| id | user_id | created | updated | content  |
//		+----+---------+---------+---------+----------+
//		|  1 |       1 | NULL    | NULL    | CONTENT1 |
//		+----+---------+---------+---------+----------+

		assertNotNull(note);
		assertNull(note.getCreated());
		assertNull(note.getUpdated());
		assertEquals("CONTENT1", note.getContent());
		assertEquals(1, note.getUserId());
	}

	@Test
	@DisplayName("test Note m:m Note mappings")
	void test2() {
		assertNotNull(note);
		assertNotNull(note.getCaseFiles());
		assertEquals(1, note.getCaseFiles().size());
		assertEquals(1, note.getCaseFiles().get(0).getId());
	}

	@Test
	@DisplayName("Testing Note m:m Incident mapping")
	void test3() {

		assertNotNull(note);

//		SELECT COUNT(*) FROM incident i JOIN incident_note inc ON inc.incident_id = i.id JOIN note n ON inc.note_id = n.id WHERE n.id = 1;
//		+----------+
//		| COUNT(*) |
//		+----------+
//		|        1 |
//		+----------+

		assertTrue(note.getIncidents().size() > 0);
		assertNotNull(note.getIncidents());
//		assertTrue(note.getIncidents().size() == 1);

	}
	
	@Test
	@DisplayName("Testing Note m:m Person mapping")
	void test4() {

		assertNotNull(note);

//		SELECT COUNT(*) FROM person p JOIN person_note pn ON pn.person_id = p.id JOIN note n ON pn.note_id = n.id WHERE p.id = 4;
//		+----------+
//		| COUNT(*) |
//		+----------+
//		|        1 |
//		+----------+

		assertTrue(note.getPersons().size() > 0);
		assertNotNull(note.getPersons());
//		assertTrue(note.getPersons().size() == 1);

	}

}
