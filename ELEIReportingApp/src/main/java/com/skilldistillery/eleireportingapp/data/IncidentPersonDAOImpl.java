package com.skilldistillery.eleireportingapp.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.skilldistillery.eleireportingapp.entities.Incident;
import com.skilldistillery.eleireportingapp.entities.IncidentPerson;
import com.skilldistillery.eleireportingapp.entities.IncidentPersonId;
import com.skilldistillery.eleireportingapp.entities.Person;

@Service
@Transactional
public class IncidentPersonDAOImpl implements IncidentPersonDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public IncidentPerson findById(IncidentPersonId id) {
		return em.find(IncidentPerson.class, id);
	}

	@Override
	public List<IncidentPerson> findByAgeRange(int start, int end) {
		String query = "SELECT ip FROM IncidentPerson ip WHERE ip.ageMinimum >= :start AND ip.ageMaximum <= :end";
		List<IncidentPerson> results = em.createQuery(query, IncidentPerson.class)
				.setParameter("start", start)
				.setParameter("end", end)
				.getResultList();
		return results;
	}
	
	@Override
	public List<IncidentPerson> findAll() {
		String query = "SELECT entity FROM IncidentPerson entity";
		List<IncidentPerson> results = em.createQuery(query, IncidentPerson.class).getResultList();
		return results;
	}

	@Override
	public IncidentPerson create(IncidentPerson IncidentPerson) {
		em.persist(IncidentPerson);

		if (!em.contains(IncidentPerson)) {
			System.err.println("EntityDAOImpl create() error: id " + IncidentPerson.getIncidentPersonId()+ " not found in db");
			return null;
		} else {
			return em.find(IncidentPerson.class, IncidentPerson.getIncidentPersonId());
		}
	}

	@Override
	public IncidentPerson update(IncidentPersonId id, IncidentPerson IncidentPerson) {
		if (em.find(IncidentPerson.class, id) == null) {
			System.err.println("EntityDAOImpl update() error: id " + id + " not found in db");
			return null;
		} else {
			IncidentPerson managed = em.find(IncidentPerson.class, id);
			em.merge(IncidentPerson);
			return managed;
		}
	}

	@Override
	public IncidentPerson delete(IncidentPersonId id) {
		IncidentPerson backup = em.find(IncidentPerson.class, id);
		if (backup == null) {
			System.err.println("EntityDAOImpl delete() error: id " + id + " not found in db");
			return null;
		} else {
			em.remove(em.find(IncidentPerson.class, id));
			if (em.contains(backup)) {
				System.err.println("EntityDAOImpl delete() error: id " + id + " still exists in db");
			}
			return backup;
		}
	}

}
