package com.skilldistillery.eleireportingapp.data;

import java.util.List;

import com.skilldistillery.eleireportingapp.entities.Incident;
import com.skilldistillery.eleireportingapp.entities.IncidentWithPerson;
import com.skilldistillery.eleireportingapp.entities.Person;

public interface IncidentWithPersonDAO {

	public List<IncidentWithPerson> findAll();
	public IncidentWithPerson findById(int id);
	public List<IncidentWithPerson> findByPerson(Person person);
	public List<IncidentWithPerson> findByIncident(Incident incident);
	public List<IncidentWithPerson> findBySuspectedCrimeContains(String suspectedCrime);
	public List<IncidentWithPerson> findByAge(int age);
	public List<IncidentWithPerson> findByAgeRange(int start, int end);
	public List<IncidentWithPerson> findByDescriptionContains(String description);
	public IncidentWithPerson create(IncidentWithPerson incidentWithPerson);
	public IncidentWithPerson update(int id, IncidentWithPerson incidentWithPerson);
	public IncidentWithPerson archive(int id);
	
}
