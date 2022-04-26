package com.skilldistillery.eleireportingapp.data;

import java.util.List;

import com.skilldistillery.eleireportingapp.entities.User;

public interface UserDAO {
	
	public User findById(int id);
//	public User findByPersonId(int personId);
	public List<User> findByPermissionLevel(String level);
	public List<User> findByStatus(boolean active);
	public User findByUserName(String userName);
	public List<User> findAll();
	public User create(User user);
	public User update(int id, User user);
	public User delete(int id);
	public User validateLogin(User userLoggingIn);
}
