package com.skilldistillery.eleireportingapp.data;

import java.util.List;

import com.skilldistillery.eleireportingapp.entities.Officer;
import com.skilldistillery.eleireportingapp.entities.Person;

public interface OfficerDAO {
	
	public Officer findById(int id);
	public List<Officer> findByPerson(Person person);
	public List<Officer> findBySupervisor(Officer officer);
	public Officer findByBadge(String badge);
	public List<Officer> findByImageUrl(String url);
	public List<Officer> findAll();
	public Officer create(Officer officer);
	public Officer update(int id, Officer officer);
	public Officer delete(int id);
	
}
