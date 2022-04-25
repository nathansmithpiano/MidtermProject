package com.skilldistillery.eleireportingapp.data;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.skilldistillery.eleireportingapp.entities.Incident;
import com.skilldistillery.eleireportingapp.entities.Person;

@Service
@Transactional
public class PersonDAOImpl implements PersonDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Person findById(int id) {
		return em.find(Person.class, id);
	}

	@Override
	public List<Person> findByIncident(Incident incident) {
		// TODO probably don't need this method
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Person> findByFirstName(String firstName) {

		String query = "SELECT p FROM Person p WHERE p.firstName IS :firstName";
		List<Person> results = em.createQuery(query, Person.class).setParameter("firstName", firstName).getResultList();
		return results;
	}

	@Override
	public List<Person> findByMiddleName(String middleName) {
		String query = "SELECT p FROM Person p WHERE p.middleName IS :middleName";
		List<Person> results = em.createQuery(query, Person.class).setParameter("middleName", middleName)
				.getResultList();
		return results;
	}

	@Override
	public List<Person> findByLastName(String lastName) {
		String query = "SELECT p FROM Person p WHERE p.lastName IS :lastName";
		List<Person> results = em.createQuery(query, Person.class).setParameter("lastName", lastName).getResultList();
		return results;
	}

	@Override
	public List<Person> findByTitle(String title) {
		String query = "SELECT p FROM Person p WHERE p.title IS :title";
		List<Person> results = em.createQuery(query, Person.class).setParameter("title", title).getResultList();
		return results;
	}

	@Override
	public List<Person> findByBirthdate(LocalDate birthDate) {
		String query = "SELECT p FROM Person p WHERE p.birthDate IS :birthDate";
		List<Person> results = em.createQuery(query, Person.class).setParameter("birthDate", birthDate).getResultList();
		return results;
	}

	@Override
	public List<Person> findByBirthdateRange(LocalDate start, LocalDate end) {
		String query = "SELECT p FROM Person p WHERE p.birthDate BETWEEN :start AND :end";
		List<Person> results = em.createQuery(query, Person.class).setParameter("start", start).setParameter("end", end)
				.getResultList();
		return results;
	}

	@Override
	public List<Person> findByGender(String gender) {
		String query = "SELECT p FROM Person p WHERE p.gender IS :gender";
		List<Person> results = em.createQuery(query, Person.class).setParameter("gender", gender).getResultList();
		return results;
	}

// TODO should be done in the Ethnicity class
//	@Override
//	public List<Person> findByEthnicity(Ethnicity ethnicity) {
//		
//	}

	@Override
	public List<Person> findByFlag(boolean flag) {

		String query = "SELECT p FROM Person p WHERE p.flag IS :flag";
		List<Person> results = em.createQuery(query, Person.class).setParameter("flag", flag).getResultList();
		return results;
	}

	@Override
	public List<Person> findAll() {
		String query = "SELECT entity FROM Person entity";
		List<Person> results = em.createQuery(query, Person.class).getResultList();
		return results;
	}

	@Override
	public Person create(Person Person) {
		em.persist(Person);

		if (!em.contains(Person)) {
			System.err.println("EntityDAOImpl create() error: id " + Person.getId() + " not found in db");
			return null;
		} else {
			return em.find(Person.class, Person.getId());
		}
	}

	@Override
	public Person update(int id, Person Person) {
		if (em.find(Person.class, id) == null) {
			System.err.println("EntityDAOImpl update() error: id " + id + " not found in db");
			return null;
		} else {
			Person managed = em.find(Person.class, id);
			em.merge(Person);
			return managed;
		}
	}

	@Override
	public Person delete(int id) {
		Person backup = em.find(Person.class, id);
		if (backup == null) {
			System.err.println("EntityDAOImpl delete() error: id " + id + " not found in db");
			return null;
		} else {
			em.remove(em.find(Person.class, id));
			if (em.contains(backup)) {
				System.err.println("EntityDAOImpl delete() error: id " + id + " still exists in db");
			}
			return backup;
		}
	}

}
