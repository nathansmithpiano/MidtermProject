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

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findByPersonId(int personId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findByPermissionLevel(String level) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findByStatus(boolean active) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findByUserName(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User create(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User update(int id, User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User archive(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
