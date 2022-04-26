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
	public Ethnicity convertToEthnicity(String ethnicity) {

		int ethnicityID = 7;

		switch (ethnicity) {

		case "White":
			ethnicityID = 1;
			break;

		case "Black":
			ethnicityID = 2;
			break;

		case "Hispanic":
			ethnicityID = 3;
			break;

		case "Asian":
			ethnicityID = 4;
			break;

		case "American Indian":
			ethnicityID = 5;
			break;

		case "Pacific Islander":
			ethnicityID = 6;
			break;

		case "Other":
		default:
			ethnicityID = 7;
			break;

		}
		
		Ethnicity ethnicityType = findById(ethnicityID);

		return ethnicityType;
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
