package com.skilldistillery.eleireportingapp.data;

import java.time.LocalDateTime;
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
	public List<Note> findByCreated(LocalDateTime created) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Note> findByCreatedRange(LocalDateTime start, LocalDateTime end) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Note> findByUpdated(LocalDateTime updated) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Note> findByUpdatedRange(LocalDateTime start, LocalDateTime end) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Note> findByUserId(int userId) {
		// TODO Auto-generated method stub
		return null;
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
