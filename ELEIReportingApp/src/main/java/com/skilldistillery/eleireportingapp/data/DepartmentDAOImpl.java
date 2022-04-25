package com.skilldistillery.eleireportingapp.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.skilldistillery.eleireportingapp.entities.Address;
import com.skilldistillery.eleireportingapp.entities.Department;

@Service
@Transactional
public class DepartmentDAOImpl implements DepartmentDAO {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public Department findById(int id) {
		return em.find(Department.class, id);
	}
	
	@Override
	public Department findByAddress(Address address) {
		String query = "SELECT entity FROM Department entity JOIN Address table ON entity.address_id = table.id WHERE entity.address_id = :addressId";
		Department result = em.createQuery(query, Department.class)
				.setParameter("addressId", address.getId())
				.getSingleResult();
		return result;
	}

	@Override
	public Department findByName(String name) {
		String query = "SELECT entity FROM Department entity WHERE entity.name IS :name";
		Department result = em.createQuery(query, Department.class)
				.setParameter("name", name)
				.getSingleResult();
		return result;
	}
	
	@Override
	public List<Department> findAll() {
		String query = "SELECT entity FROM Department entity";
		List<Department> results = em.createQuery(query, Department.class).getResultList();
		return results;
	}
	
	@Override
	public Department create(Department Department) {
		em.persist(Department);
		
		if (!em.contains(Department)) {
			System.err.println("EntityDAOImpl create() error: id " + Department.getId() + " not found in db");
			return null;
		} else {
			return em.find(Department.class, Department.getId());
		}
	}

	@Override
	public Department update(int id, Department Department) {
		if (em.find(Department.class, id) == null) {
			System.err.println("EntityDAOImpl update() error: id " + id + " not found in db");
			return null;
		} else {
			Department managed = em.find(Department.class, id);
			em.merge(Department);
			return managed;
		}
	}

	@Override
	public Department delete(int id) {
		Department backup = em.find(Department.class, id);
		if (backup == null) {
			System.err.println("EntityDAOImpl delete() error: id " + id + " not found in db");
			return null;
		} else {
			em.remove(em.find(Department.class, id));
			if (em.contains(backup)) {
				System.err.println("EntityDAOImpl delete() error: id " + id + " still exists in db");
			}
			return backup;
		}
	}

}
