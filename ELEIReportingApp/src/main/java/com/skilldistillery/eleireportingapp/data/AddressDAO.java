package com.skilldistillery.eleireportingapp.data;

import java.util.List;

import com.skilldistillery.eleireportingapp.entities.Address;

public interface AddressDAO {
	
	public List<Address> findAll();
	public Address findById(int id);
	public List<Address> findByDescriptionContains(String description);
	public List<Address> findByStreet1(String street1);
	public List<Address> findByStreet1Contains(String street1);
	public List<Address> findByStreet2(String street2);
	public List<Address> findByStreet2Contains(String street2);
	public List<Address> findByCity(String city);
	public List<Address> findByCityContains(String city);
	public List<Address> findByStateCode(String stateCode);
	public List<Address> findByStateCodeContains(String stateCode);
	public List<Address> findByZIP(String stateCode);
	public List<Address> findByFlag(boolean flag);
	public Address create(Address address);
	public Address update(int id, Address address);
	public Address archive(int id);
	
}
