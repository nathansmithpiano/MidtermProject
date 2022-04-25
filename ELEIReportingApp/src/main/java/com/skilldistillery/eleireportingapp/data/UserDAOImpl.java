package com.skilldistillery.eleireportingapp.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.skilldistillery.eleireportingapp.entities.User;

@Service
@Transactional
public class UserDAOImpl implements UserDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public User findById(int id) {
		return em.find(User.class, id);
	}

//	@Override
//	public User findByPersonId(int personId) {
//		return null;
//	}

	@Override
	public List<User> findByPermissionLevel(String level) {

		String query = "SELECT u FROM User u WHERE u.permissionLevel IS :level";
		List<User> results = em.createQuery(query, User.class).setParameter("level", level).getResultList();
		return results;
	}

	@Override
	public List<User> findByStatus(boolean active) {

		String query = "SELECT entity FROM User entity WHERE entity.active = :active";
		List<User> results = em.createQuery(query, User.class).setParameter("active", active).getResultList();
		return results;

	}

	@Override
	public User findByUserName(String userName) {

		String query = "SELECT u FROM User u WHERE u.username IS :userName";
		User results = em.createQuery(query, User.class).setParameter("userName", userName).getSingleResult();
		return results;
	}

	@Override
	public List<User> findAll() {
		String query = "SELECT entity FROM User entity";
		List<User> results = em.createQuery(query, User.class).getResultList();
		return results;
	}

	@Override
	public User create(User User) {
		em.persist(User);

		if (!em.contains(User)) {
			System.err.println("EntityDAOImpl create() error: id " + User.getId() + " not found in db");
			return null;
		} else {
			return em.find(User.class, User.getId());
		}
	}

	@Override
	public User update(int id, User User) {
		if (em.find(User.class, id) == null) {
			System.err.println("EntityDAOImpl update() error: id " + id + " not found in db");
			return null;
		} else {
			User managed = em.find(User.class, id);
			em.merge(User);
			return managed;
		}
	}

	@Override
	public User delete(int id) {
		User backup = em.find(User.class, id);
		if (backup == null) {
			System.err.println("EntityDAOImpl delete() error: id " + id + " not found in db");
			return null;
		} else {
			em.remove(em.find(User.class, id));
			if (em.contains(backup)) {
				System.err.println("EntityDAOImpl delete() error: id " + id + " still exists in db");
			}
			return backup;
		}
	}

}
