package com.skilldistillery.eleireportingapp.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.skilldistillery.eleireportingapp.entities.Incident;
import com.skilldistillery.eleireportingapp.entities.IncidentWithPerson;
import com.skilldistillery.eleireportingapp.entities.Person;

@Service
@Transactional
public class IncidentWithPersonDAOImpl implements IncidentWithPersonDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public IncidentWithPerson findById(int id) {
		return em.find(IncidentWithPerson.class, id);
	}

	@Override
	public List<IncidentWithPerson> findByPerson(Person person) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IncidentWithPerson> findByIncident(Incident incident) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IncidentWithPerson> findByAge(int age) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IncidentWithPerson> findByAgeRange(int start, int end) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IncidentWithPerson> findAll() {
		String query = "SELECT entity FROM IncidentWithPerson entity";
		List<IncidentWithPerson> results = em.createQuery(query, IncidentWithPerson.class).getResultList();
		return results;
	}

	@Override
	public IncidentWithPerson create(IncidentWithPerson IncidentWithPerson) {
		em.persist(IncidentWithPerson);

		if (!em.contains(IncidentWithPerson)) {
			System.err.println("EntityDAOImpl create() error: id " + IncidentWithPerson.getIncidentWithPersonId()+ " not found in db");
			return null;
		} else {
			return em.find(IncidentWithPerson.class, IncidentWithPerson.getIncidentWithPersonId());
		}
	}

	@Override
	public IncidentWithPerson update(int id, IncidentWithPerson IncidentWithPerson) {
		if (em.find(IncidentWithPerson.class, id) == null) {
			System.err.println("EntityDAOImpl update() error: id " + id + " not found in db");
			return null;
		} else {
			IncidentWithPerson managed = em.find(IncidentWithPerson.class, id);
			em.merge(IncidentWithPerson);
			return managed;
		}
	}

	@Override
	public IncidentWithPerson delete(int id) {
		IncidentWithPerson backup = em.find(IncidentWithPerson.class, id);
		if (backup == null) {
			System.err.println("EntityDAOImpl delete() error: id " + id + " not found in db");
			return null;
		} else {
			em.remove(em.find(IncidentWithPerson.class, id));
			if (em.contains(backup)) {
				System.err.println("EntityDAOImpl delete() error: id " + id + " still exists in db");
			}
			return backup;
		}
	}

}
