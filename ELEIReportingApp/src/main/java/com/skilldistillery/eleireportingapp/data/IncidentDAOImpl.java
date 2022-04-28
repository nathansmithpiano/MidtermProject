package com.skilldistillery.eleireportingapp.data;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.skilldistillery.eleireportingapp.entities.Address;
import com.skilldistillery.eleireportingapp.entities.CaseFile;
import com.skilldistillery.eleireportingapp.entities.Incident;
import com.skilldistillery.eleireportingapp.entities.Person;

@Service
@Transactional
public class IncidentDAOImpl implements IncidentDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Incident findById(int id) {
		return em.find(Incident.class, id);
	}

	@Override
	public List<Incident> findByAddress(Address address) {
		String query = "SELECT incident FROM Incident incident JOIN Address address ON incident.address_id = address.id WHERE address.id = :id";
		List<Incident> results = em.createQuery(query, Incident.class)
				.setParameter("id", address.getId())
				.getResultList();
		return results;
	}

	@Override
	public List<Incident> findByPerson(Person person) {
		String query = "SELECT incident FROM Incident incident JOIN incident_person ip ON incident.id = ip.incident_id JOIN person ON person.id = ip.person_id WHERE ip.person_id = :id";
		List<Incident> results = em.createQuery(query, Incident.class)
				.setParameter("id", person.getId())
				.getResultList();
		return results;
	}
	
	@Override
	public List<Incident> findByOfficerId(int id) {
		String query = "SELECT incident FROM Incident incident WHERE incident.officer.id = :id";
		List<Incident> results = em.createQuery(query, Incident.class)
				.setParameter("id", id)
				.getResultList();
		return results;
	}
	
	public List<Incident> findByDepartmentId(int id) {
		String query = "SELECT incident FROM Incident incident JOIN department.incidents incident WHERE department.id = :id";
		List<Incident> results = em.createQuery(query, Incident.class)
				.setParameter("id", id)
				.getResultList();
		return results;
	}

	@Override
	public Incident findByCase(CaseFile caseFile) {
		String query = "SELECT incident FROM Incident incident JOIN Case_file casefile ON incident.case_id = casefile.id WHERE casefile.id = :id";
		Incident result = em.createQuery(query, Incident.class)
				.setParameter("id", caseFile.getId())
				.getSingleResult();
		return result;
	}

	@Override
	public List<Incident> findByIncidentDate(Timestamp incidentDate) {
		String query = "SELECT incident FROM Incident incident WHERE incident.incidentDate = :date";
		List<Incident> results = em.createQuery(query, Incident.class)
				.setParameter("date", incidentDate)
				.getResultList();
		return results;
	}

	@Override
	public List<Incident> findByIncidentDateRange(Timestamp start, Timestamp end) {
		String query = "SELECT incident FROM Incident incident WHERE incident.incidentDate BETWEEN :start AND :end";
		List<Incident> results = em.createQuery(query, Incident.class)
				.setParameter("start", start)
				.setParameter("end", end)
				.getResultList();
		return results;
	}

	@Override
	public List<Incident> findByStatus(boolean flag) {
		String query = "SELECT entity FROM Incident entity WHERE entity.flag = :flag";
		List<Incident> results = em.createQuery(query, Incident.class)
				.setParameter("flag", flag)
				.getResultList();
		return results;
	}

	@Override
	public List<Incident> findAll() {
		String query = "SELECT entity FROM Incident entity";
		List<Incident> results = em.createQuery(query, Incident.class).getResultList();
		return results;
	}

	@Override
	public Incident create(Incident Incident) {
		em.persist(Incident);
		
		if (!em.contains(Incident)) {
			System.err.println("EntityDAOImpl create() error: id " + Incident.getId() + " not found in db");
			return null;
		} else {
			return em.find(Incident.class, Incident.getId());
		}
	}

	@Override
	public Incident update(int id, Incident Incident) {
		if (em.find(Incident.class, id) == null) {
			System.err.println("EntityDAOImpl update() error: id " + id + " not found in db");
			return null;
		} else {
			Incident managed = em.find(Incident.class, id);
			em.merge(Incident);
			return managed;
		}
	}

	@Override
	public Incident delete(int id) {
		Incident backup = em.find(Incident.class, id);
		if (backup == null) {
			System.err.println("EntityDAOImpl delete() error: id " + id + " not found in db");
			return null;
		} else {
			em.remove(em.find(Incident.class, id));
			if (em.contains(backup)) {
				System.err.println("EntityDAOImpl delete() error: id " + id + " still exists in db");
			}
			return backup;
		}
	}

}
