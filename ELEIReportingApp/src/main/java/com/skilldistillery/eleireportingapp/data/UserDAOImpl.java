package com.skilldistillery.eleireportingapp.data;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
	
//	private Map<Integer, User> users;
	
	
	
//	public UserDAOImpl () {
//    users = new LinkedHashMap<>();
//    
//    users.put(1, new User(1, "policesupervisor", "supervisor"));
//    users.put(2, new User(2, "policeofficer", "officer"));
//	
//	}
//    
//    // Passwords below are hashed with MD5 algorithm
//    // id: testuser, cleartext password: password
//    users.put(3, new User(3, "testuser", "5f4dcc3b5aa765d61d8327deb882cf99", "Ronald", "McD"));
//    // id: topper, cleartext password: topgun
//    users.put(4, new User(4, "topper", "46a46b0a267201c0c742cee54685d62c", "Topper", "Harley"));
//  }
//  
//  @Override
//  public User getUserByUserNameAndPassword(String userName, String password) {
//    User u = null;
//    Set<Integer> keys = users.keySet();
//    for (Integer key : keys) {
//      User user = users.get(key);
//      if(user.getUserName().equals(userName) && user.getPassword().equals(password)) {
//        u = user;
//        break;
//      }
//    }
//    return u;
//  }

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

	@Override
	public User validateLogin(User userLoggingIn) {
		String query = "SELECT u FROM User u WHERE u.username = :username AND u.password = :password";
		User loggedInUser = em.createQuery(query, User.class)
				.setParameter("username", userLoggingIn.getUsername())
				.setParameter("password", userLoggingIn.getPassword())
				.getSingleResult();
		
		return loggedInUser;
	}

}
