package com.skilldistillery.eleireportingapp.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.skilldistillery.eleireportingapp.entities.Department;
import com.skilldistillery.eleireportingapp.entities.Officer;
import com.skilldistillery.eleireportingapp.entities.Person;

@Service
@Transactional
public class OfficerDAOImpl implements OfficerDAO {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public Officer findById(int id) {
		return em.find(Officer.class, id);
	}
	
	@Override
	public Officer findByPerson(Person person) {
		System.out.println("***OfficerDAOImpl.findByPerson " + person.getId());
		String query = "SELECT officer FROM Officer officer JOIN Person person ON officer.person.id = person.id WHERE person.id = :id";
		Officer result = em.createQuery(query, Officer.class)
				.setParameter("id", person.getId())
				.getResultList()
				.get(0);
		result.getDepartments().size();
		return result;
	}

	@Override
	public List<Officer> findBySupervisor(Officer officer) {
		String query = "SELECT entity FROM Officer entity JOIN Officer table ON entity.supervisor_id = table.id WHERE entity._supervisor_id = :id";
		List<Officer> results = em.createQuery(query, Officer.class)
				.setParameter("id", officer.getSupervisor().getId())
				.getResultList();
		return results;
	}
	
	@Override
	public Officer findByBadge(String badge) {
		String query = "SELECT entity FROM Department entity WHERE entity.badge IS :badge";
		Officer result = em.createQuery(query, Officer.class)
				.setParameter("badge", badge)
				.getSingleResult();
		return result;
	}

	@Override
	public List<Officer> findByImageUrl(String url) {
		String query = "SELECT entity FROM Department entity WHERE entity.image_url IS :url";
		List<Officer> results = em.createQuery(query, Officer.class)
				.setParameter("url", url)
				.getResultList();
		return results;
	}
	
	@Override
	public List<Officer> findAll() {
		String query = "SELECT officer FROM Officer officer";
		List<Officer> results = em.createQuery(query, Officer.class).getResultList();
		return results;
	}

	@Override
	public Officer create(Officer Officer) {
		em.persist(Officer);
		
		if (!em.contains(Officer)) {
			System.err.println("EntityDAOImpl create() error: id " + Officer.getId() + " not found in db");
			return null;
		} else {
			return em.find(Officer.class, Officer.getId());
		}
	}

	@Override
	public Officer update(int id, Officer Officer) {
		if (em.find(Officer.class, id) == null) {
			System.err.println("EntityDAOImpl update() error: id " + id + " not found in db");
			return null;
		} else {
			Officer managed = em.find(Officer.class, id);
			em.merge(Officer);
			return managed;
		}
	}

	@Override
	public Officer delete(int id) {
		Officer backup = em.find(Officer.class, id);
		if (backup == null) {
			System.err.println("EntityDAOImpl delete() error: id " + id + " not found in db");
			return null;
		} else {
			em.remove(em.find(Officer.class, id));
			if (em.contains(backup)) {
				System.err.println("EntityDAOImpl delete() error: id " + id + " still exists in db");
			}
			return backup;
		}
	}

}
