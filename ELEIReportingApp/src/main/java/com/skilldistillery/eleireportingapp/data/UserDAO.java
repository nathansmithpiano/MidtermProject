package com.skilldistillery.eleireportingapp.data;

import com.skilldistillery.eleireportingapp.entities.User;

public interface UserDAO {
	
	User findById(int userId);
	
}
