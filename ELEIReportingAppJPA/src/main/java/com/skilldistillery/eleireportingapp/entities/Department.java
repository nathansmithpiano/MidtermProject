package com.skilldistillery.eleireportingapp.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Department {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	@OneToOne
	@JoinColumn(name = "address_id")
	private Address address;

	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinTable(name = "department_employee", joinColumns = @JoinColumn(name = "department_id"), inverseJoinColumns = @JoinColumn(name = "officer_id"))
	private List<Officer> officers;
	
	@OneToMany(mappedBy = "department")
	private List<Incident> incidents;

	public Department() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addOfficer(Officer officer) {
		if (officers == null) {
			officers = new ArrayList<>();
		}
		if (!officers.contains(officer)) {
			officers.add(officer);
		}
	}

	public boolean removeOfficer(Officer officer) {
		if (officers != null && officers.contains(officer)) {
			officers.remove(officer);
		}
		return officers.contains(officer);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Department other = (Department) obj;
		return id == other.id;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Officer> getOfficers() {
		return officers;
	}

	public void setOfficers(List<Officer> officers) {
		this.officers = officers;
	}

	public List<Incident> getIncidents() {
		return incidents;
	}

	public void setIncidents(List<Incident> incidents) {
		this.incidents = incidents;
	}

	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + ", address=" + address + "]";
	}

}
