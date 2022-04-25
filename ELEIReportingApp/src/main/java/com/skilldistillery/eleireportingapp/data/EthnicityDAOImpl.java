package com.skilldistillery.eleireportingapp.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.skilldistillery.eleireportingapp.entities.Ethnicity;

@Service
@Transactional
public class EthnicityDAOImpl implements EthnicityDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Ethnicity findById(int id) {
		return em.find(Ethnicity.class, id);
	}

	@Override
	public Ethnicity findByName(String name) {
		String query = "SELECT entity FROM Ethnicity entity WHERE entity.name LIKE :name";
		Ethnicity result = em.createQuery(query, Ethnicity.class).setParameter("name", name).getSingleResult();
		return result;
	}
	
	@Override
	public List<Ethnicity> findAll() {
		String query = "SELECT entity FROM Ethnicity entity";
		List<Ethnicity> results = em.createQuery(query, Ethnicity.class).getResultList();
		return results;
	}

	@Override
	public Ethnicity create(Ethnicity ethnicity) {
		em.persist(ethnicity);

		if (!em.contains(ethnicity)) {
			System.err.println("EntityDAOImpl create() error: id " + ethnicity.getId() + " not found in db");
			return null;
		} else {
			return em.find(Ethnicity.class, ethnicity.getId());
		}
	}

	@Override
	public Ethnicity update(int id, Ethnicity ethnicity) {
		if (em.find(Ethnicity.class, id) == null) {
			System.err.println("EntityDAOImpl update() error: id " + id + " not found in db");
			return null;
		} else {
			Ethnicity managed = em.find(Ethnicity.class, id);
			em.merge(ethnicity);
			return managed;
		}
	}

	@Override
	public Ethnicity delete(int id) {
		Ethnicity backup = em.find(Ethnicity.class, id);
		if (backup == null) {
			System.err.println("EntityDAOImpl delete() error: id " + id + " not found in db");
			return null;
		} else {
			em.remove(em.find(Ethnicity.class, id));
			if (em.contains(backup)) {
				System.err.println("EntityDAOImpl delete() error: id " + id + " still exists in db");
			}
			return backup;
		}
	}

}
