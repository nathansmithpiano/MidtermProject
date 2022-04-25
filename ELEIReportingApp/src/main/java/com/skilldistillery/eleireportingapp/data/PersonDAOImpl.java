package com.skilldistillery.eleireportingapp.data;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.skilldistillery.eleireportingapp.entities.Address;
import com.skilldistillery.eleireportingapp.entities.Ethnicity;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Person> findByFirstName(String firstName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Person> findByMiddleName(String middleName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Person> findByLastName(String lastName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Person> findByTitle(String title) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Person> findByBirthdate(LocalDateTime birthDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Person> findByBirthdateRange(LocalDateTime start, LocalDateTime end) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Person> findByGender(String gender) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Person> findByAddress(Address address) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Person> findByEthnicity(Ethnicity ethnicity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Person> findByFlag(boolean flag) {
		// TODO Auto-generated method stub
		return null;
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
