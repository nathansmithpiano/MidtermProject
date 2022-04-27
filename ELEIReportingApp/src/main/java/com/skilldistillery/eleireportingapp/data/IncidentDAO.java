package com.skilldistillery.eleireportingapp.data;

import java.sql.Timestamp;
import java.util.List;

import com.skilldistillery.eleireportingapp.entities.Address;
import com.skilldistillery.eleireportingapp.entities.CaseFile;
import com.skilldistillery.eleireportingapp.entities.Incident;
import com.skilldistillery.eleireportingapp.entities.IncidentPerson;
import com.skilldistillery.eleireportingapp.entities.Person;

public interface IncidentDAO {
	
	public Incident findById(int id);
	public List<Incident> findByAddress(Address address);
	public List<Incident> findByPerson(Person person);
	public List<Incident> findByDepartmentId(int id);
	public Incident findByCase(CaseFile caseFile);
	public List<Incident> findByIncidentDate(Timestamp incidentDate);
	public List<Incident> findByIncidentDateRange(Timestamp start, Timestamp end);
	public List<Incident> findByStatus(boolean flag);
	public List<Incident> findAll();
	public Incident create(Incident incident);
	public Incident update(int id, Incident incident);
	public Incident delete(int id);

}
