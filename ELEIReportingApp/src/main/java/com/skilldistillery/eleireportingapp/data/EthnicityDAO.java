package com.skilldistillery.eleireportingapp.data;

import java.util.List;

import com.skilldistillery.eleireportingapp.entities.Ethnicity;

public interface EthnicityDAO {
	
	public List<Ethnicity> findAll();
	public Ethnicity findById(int id);
	public Ethnicity findByName(String name);
	public Ethnicity create(Ethnicity ethnicity);
	public Ethnicity update(int id, Ethnicity ethnicity);
	public Ethnicity archive(int id);
	
}
