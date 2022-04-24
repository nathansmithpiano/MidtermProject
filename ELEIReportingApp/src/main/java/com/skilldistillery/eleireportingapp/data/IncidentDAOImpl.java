package com.skilldistillery.eleireportingapp.data;

import java.time.LocalDateTime;
import java.util.List;

import com.skilldistillery.eleireportingapp.entities.Address;
import com.skilldistillery.eleireportingapp.entities.CaseFile;
import com.skilldistillery.eleireportingapp.entities.Incident;
import com.skilldistillery.eleireportingapp.entities.Note;
import com.skilldistillery.eleireportingapp.entities.Person;

public class IncidentDAOImpl implements IncidentDAO {

	@Override
	public List<Incident> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Incident findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Incident> findByAddress(Address address) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Incident> findByPerson(Person person) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Incident> findByReasonForContactContains(String reasonForContact) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Incident> findByLocationContains(String reasonForContact) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Incident findByCase(CaseFile caseFile) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Incident> findByDescriptionContains(String description) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Incident> findByIncidentDate(LocalDateTime incidentDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Incident> findByIncidentDateRange(LocalDateTime start, LocalDateTime end) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Incident> findByStatus(boolean flag) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Incident create(IncidentDAO caseFile) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Incident update(int id, IncidentDAO caseFile) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Incident archive(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
