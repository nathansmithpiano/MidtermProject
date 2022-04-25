package com.skilldistillery.eleireportingapp.data;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.skilldistillery.eleireportingapp.entities.Note;

@Service
@Transactional
public class NoteDAOImpl implements NoteDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Note findById(int id) {
		return em.find(Note.class, id);
	}

	@Override
	public List<Note> findByCreatedRange(Timestamp start, Timestamp end) {
		String query = "SELECT n FROM Note n WHERE n.created BETWEEN :start AND :end";
		List<Note> results = em.createQuery(query, Note.class)
				.setParameter("start", start)
				.setParameter("end", end)
				.getResultList();
		return results;
	}

	@Override
	public List<Note> findByUpdatedRange(Timestamp start, Timestamp end) {
		String query = "SELECT n FROM Note n WHERE n.updated BETWEEN :start AND :end";
		List<Note> results = em.createQuery(query, Note.class)
				.setParameter("start", start)
				.setParameter("end", end)
				.getResultList();
		return results;
	}

	@Override
	public List<Note> findByUserId(int userId) {
		String query = "SELECT entity FROM Note entity JOIN User table ON entity.userId = table.id WHERE table.id = :userId";
		List<Note> results = em.createQuery(query, Note.class)
				.setParameter("userId", userId)
				.getResultList();
		return results;
	}

	@Override
	public List<Note> findAll() {
		String query = "SELECT entity FROM Note entity";
		List<Note> results = em.createQuery(query, Note.class).getResultList();
		return results;
	}

	@Override
	public Note create(Note Note) {
		em.persist(Note);

		if (!em.contains(Note)) {
			System.err.println("EntityDAOImpl create() error: id " + Note.getId() + " not found in db");
			return null;
		} else {
			return em.find(Note.class, Note.getId());
		}
	}

	@Override
	public Note update(int id, Note Note) {
		if (em.find(Note.class, id) == null) {
			System.err.println("EntityDAOImpl update() error: id " + id + " not found in db");
			return null;
		} else {
			Note managed = em.find(Note.class, id);
			em.merge(Note);
			return managed;
		}
	}

	@Override
	public Note delete(int id) {
		Note backup = em.find(Note.class, id);
		if (backup == null) {
			System.err.println("EntityDAOImpl delete() error: id " + id + " not found in db");
			return null;
		} else {
			em.remove(em.find(Note.class, id));
			if (em.contains(backup)) {
				System.err.println("EntityDAOImpl delete() error: id " + id + " still exists in db");
			}
			return backup;
		}
	}

}
