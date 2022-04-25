package com.skilldistillery.eleireportingapp.data;

import java.util.List;

import com.skilldistillery.eleireportingapp.entities.Incident;
import com.skilldistillery.eleireportingapp.entities.IncidentPerson;
import com.skilldistillery.eleireportingapp.entities.Person;

public interface IncidentPersonDAO {

	public IncidentPerson findById(int id);
	public List<IncidentPerson> findByPerson(Person person);
	public List<IncidentPerson> findByIncident(Incident incident);
	public List<IncidentPerson> findByAge(int age);
	public List<IncidentPerson> findByAgeRange(int start, int end);
	public List<IncidentPerson> findAll();
	public IncidentPerson create(IncidentPerson IncidentPerson);
	public IncidentPerson update(int id, IncidentPerson IncidentPerson);
	public IncidentPerson delete(int id);
	
}
