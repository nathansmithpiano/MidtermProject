package com.skilldistillery.eleireportingapp.data;

import java.util.List;

import com.skilldistillery.eleireportingapp.entities.Department;

public interface DepartmentDAO {
	
	public List<Department> findAll();
	public Department findById(int id);
	public Department findByAddressId(int addressId);
	public Department findByName(String name);
	public List<Department> findByNameContains(String name);
	public Department create(Department department);
	public Department update(int id, Department department);
	public Department archive(int id);
	
}
