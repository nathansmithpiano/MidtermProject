package com.skilldistillery.eleireportingapp.data;

import java.time.LocalDate;
import java.util.List;

import com.skilldistillery.eleireportingapp.entities.Incident;
import com.skilldistillery.eleireportingapp.entities.Person;

public interface PersonDAO {
	
	public Person findById(int id);
	public List<Person> findByIncident(Incident incident);
	public List<Person> findByFirstName(String firstName);
	public List<Person> findByMiddleName(String middleName);
	public List<Person> findByLastName(String lastName);
	public List<Person> findByTitle(String title);
	public List<Person> findByBirthdate(LocalDate birthDate);
	public List<Person> findByBirthdateRange(LocalDate start, LocalDate end);
	public List<Person> findByGender(String gender);
//	public List<Person> findByAddress(Address address); Should be done in the address table
//	public List<Person> findByEthnicity(Ethnicity ethnicity); Should be done in Ethnicity
	public List<Person> findByFlag(boolean flag);
	public List<Person> findAll();
	public Person create(Person person);
	public Person update(int id, Person person);
	public Person delete(int id);
	
}
