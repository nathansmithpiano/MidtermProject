package com.skilldistillery.eleireportingapp.data;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.skilldistillery.eleireportingapp.entities.Address;
import com.skilldistillery.eleireportingapp.entities.CaseFile;
import com.skilldistillery.eleireportingapp.entities.Ethnicity;
import com.skilldistillery.eleireportingapp.entities.Incident;
import com.skilldistillery.eleireportingapp.entities.Note;
import com.skilldistillery.eleireportingapp.entities.Person;

@Service
@Transactional
public class IncidentDAOImpl implements IncidentDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Incident findById(int id) {
		return em.find(Incident.class, id);
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
	public Incident findByCase(CaseFile caseFile) {
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
	public List<Incident> findAll() {
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
	public Incident delete(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
