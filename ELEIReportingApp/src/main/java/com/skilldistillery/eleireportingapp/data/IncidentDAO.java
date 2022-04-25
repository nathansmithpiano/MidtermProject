package com.skilldistillery.eleireportingapp.data;

import java.time.LocalDateTime;
import java.util.List;

import com.skilldistillery.eleireportingapp.entities.Address;
import com.skilldistillery.eleireportingapp.entities.Incident;
import com.skilldistillery.eleireportingapp.entities.Person;
import com.skilldistillery.eleireportingapp.entities.CaseFile;

public interface IncidentDAO {
	
	public Incident findById(int id);
	public List<Incident> findByAddress(Address address);
	public List<Incident> findByPerson(Person person);
	public Incident findByCase(CaseFile caseFile);
	public List<Incident> findByIncidentDate(LocalDateTime incidentDate);
	public List<Incident> findByIncidentDateRange(LocalDateTime start, LocalDateTime end);
	public List<Incident> findByStatus(boolean flag);
	public List<Incident> findAll();
	public Incident create(IncidentDAO caseFile);
	public Incident update(int id, IncidentDAO caseFile);
	public Incident delete(int id);

}
