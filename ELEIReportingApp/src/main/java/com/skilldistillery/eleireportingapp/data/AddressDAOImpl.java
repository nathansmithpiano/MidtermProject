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
public class AddressDAOImpl implements AddressDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Address findById(int id) {
		return em.find(Address.class, id);
	}

	@Override
	public Address findByDepartment(Department department) {
		String query = "SELECT entity FROM Address entity JOIN Department table ON entity.id = table._address_id WHERE entity.id = :addressId";
		Address result = em.createQuery(query, Address.class).setParameter("addressId", department.getAddress().getId())
				.getSingleResult();
		return result;
	}
	
//	public List<Address> findAllForDepartment(Department department) {
//		// all officers at department
//		// all persons for officers
//		String query = 
//	}
//	
//	public List<Address> findAllForOfficer(Officer officer) {
//		// all persons for officer's incidents
//		String query = 
//	}

	@Override
	public List<Address> findByStreetOne(String streetOne) {
		String query = "SELECT entity FROM Address entity WHERE entity.street1 IS :streetOne";
		List<Address> results = em.createQuery(query, Address.class).setParameter("streetOne", streetOne)
				.getResultList();
		return results;
	}

	@Override
	public List<Address> findByStreetTwo(String streetTwo) {
		String query = "SELECT entity FROM Address entity WHERE entity.street2 IS :streetTwo";
		List<Address> results = em.createQuery(query, Address.class).setParameter("streetOne", streetTwo)
				.getResultList();
		return results;
	}

	@Override
	public List<Address> findByCity(String city) {
		String query = "SELECT entity FROM Address entity WHERE entity.city IS :city";
		List<Address> results = em.createQuery(query, Address.class).setParameter("city", city).getResultList();
		return results;
	}

	@Override
	public List<Address> findByStateCode(String stateCode) {
		String query = "SELECT entity FROM Address entity WHERE entity.state_code IS :stateCode";
		List<Address> results = em.createQuery(query, Address.class).setParameter("stateCode", stateCode)
				.getResultList();
		return results;
	}

	@Override
	public List<Address> findByZip(Integer zip) {
		String query = "SELECT entity FROM Address entity WHERE entity.zip = :zip";
		List<Address> results = em.createQuery(query, Address.class).setParameter("zip", zip).getResultList();
		return results;
	}

	@Override
	public List<Address> findByFlag(boolean flag) {
		String query = "SELECT entity FROM Address entity WHERE entity.flag = :flag";
		List<Address> results = em.createQuery(query, Address.class).setParameter("flag", flag).getResultList();
		return results;
	}

	@Override
	public List<Address> findAll() {
		String query = "SELECT entity FROM Address entity";
		List<Address> results = em.createQuery(query, Address.class).getResultList();
		return results;
	}

	@Override
	public Address create(Address Address) {
		em.persist(Address);

		if (!em.contains(Address)) {
			System.err.println("EntityDAOImpl create() error: id " + Address.getId() + " not found in db");
			return null;
		} else {
			return em.find(Address.class, Address.getId());
		}
	}

	@Override
	public Address update(int id, Address Address) {
		if (em.find(Address.class, id) == null) {
			System.err.println("EntityDAOImpl update() error: id " + id + " not found in db");
			return null;
		} else {
			Address managed = em.find(Address.class, id);
			em.merge(Address);
			return managed;
		}
	}

	@Override
	public Address delete(int id) {
		Address backup = em.find(Address.class, id);
		if (backup == null) {
			System.err.println("EntityDAOImpl delete() error: id " + id + " not found in db");
			return null;
		} else {
			em.remove(em.find(Address.class, id));
			if (em.contains(backup)) {
				System.err.println("EntityDAOImpl delete() error: id " + id + " still exists in db");
			}
			return backup;
		}
	}

}
