package com.skilldistillery.eleireportingapp.data;

import java.util.List;

import com.skilldistillery.eleireportingapp.entities.Address;
import com.skilldistillery.eleireportingapp.entities.Department;

public interface DepartmentDAO {
	
	public List<Department> findAll();
	public Department findById(int id);
	public Department findByAddress(Address address);
	public Department findByName(String name);
	public Department create(Department department);
	public Department update(int id, Department department);
	public Department delete(int id);
	
}
