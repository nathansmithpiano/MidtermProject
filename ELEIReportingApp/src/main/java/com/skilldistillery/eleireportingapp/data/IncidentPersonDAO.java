package com.skilldistillery.eleireportingapp.data;

import java.util.List;

import com.skilldistillery.eleireportingapp.entities.Incident;
import com.skilldistillery.eleireportingapp.entities.IncidentPerson;
import com.skilldistillery.eleireportingapp.entities.IncidentPersonId;
import com.skilldistillery.eleireportingapp.entities.Person;

public interface IncidentPersonDAO {

	public IncidentPerson findById(IncidentPersonId id);
	public List<IncidentPerson> findByAgeRange(int start, int end);
	public List<IncidentPerson> findAll();
	public IncidentPerson create(IncidentPerson IncidentPerson);
	public IncidentPerson update(IncidentPersonId id, IncidentPerson IncidentPerson);
	public IncidentPerson delete(IncidentPersonId id);
	
}
