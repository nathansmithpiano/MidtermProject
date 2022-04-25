package com.skilldistillery.eleireportingapp.data;

import java.util.List;

import com.skilldistillery.eleireportingapp.entities.Address;
import com.skilldistillery.eleireportingapp.entities.Department;

public interface AddressDAO {
	
	public Address findById(int id);
	public Address findByDepartment(Department department);
	public List<Address> findByStreetOne(String street1);
	public List<Address> findByStreetTwo(String street2);
	public List<Address> findByCity(String city);
	public List<Address> findByStateCode(String stateCode);
	public List<Address> findByZip(Integer zip);
	public List<Address> findByFlag(boolean flag);
	public List<Address> findAll();
	public Address create(Address address);
	public Address update(int id, Address address);
	public Address delete(int id);
	
}
