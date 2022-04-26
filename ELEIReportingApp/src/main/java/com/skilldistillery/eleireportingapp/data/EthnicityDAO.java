package com.skilldistillery.eleireportingapp.data;

import java.util.List;

import com.skilldistillery.eleireportingapp.entities.Ethnicity;

public interface EthnicityDAO {
	
	public Ethnicity findById(int id);
	public Ethnicity convertToEthnicity(String ethnicity);
	public Ethnicity findByName(String name);
	public List<Ethnicity> findAll();
	public Ethnicity create(Ethnicity ethnicity);
	public Ethnicity update(int id, Ethnicity ethnicity);
	public Ethnicity delete(int id);
	
}
