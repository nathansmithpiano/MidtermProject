package com.skilldistillery.eleireportingapp.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.skilldistillery.eleireportingapp.entities.Ethnicity;

@Service
@Transactional
public class EthnicityDAOImpl implements EthnicityDAO {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Ethnicity> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ethnicity findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ethnicity findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ethnicity create(Ethnicity ethnicity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ethnicity update(int id, Ethnicity ethnicity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ethnicity archive(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
