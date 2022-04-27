package com.skilldistillery.eleireportingapp.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Officer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String badge;

	@Column(name = "image_url")
	private String imageUrl;

	@OneToOne
	@JoinColumn(name = "person_id")
	private Person person;

	@ManyToMany(mappedBy = "officers")
	private List<Department> departments;
	
	@ManyToOne(cascade= {CascadeType.ALL})
	@JoinColumn(name="supervisor_id")
	private Officer supervisor;
	
	@OneToMany(mappedBy = "supervisor")
	private List<Officer> subordinates;
	
	public Officer() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBadge() {
		return badge;
	}

	public void setBadge(String badge) {
		this.badge = badge;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public List<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}

	public Officer getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(Officer supervisor) {
		this.supervisor = supervisor;
	}

	public List<Officer> getSubordinates() {
		return subordinates;
	}

	public void setSubordinates(List<Officer> subordinates) {
		this.subordinates = subordinates;
	}

	public void addDepartment(Department department) {
		if (departments == null) {
			departments = new ArrayList<>();
		}
		if (!departments.contains(department)) {
			departments.add(department);
		}
	}

	public boolean removeDepartment(Department department) {
		if (departments != null && departments.contains(department)) {
			departments.remove(department);
		}
		return !departments.contains(department);
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
		Officer other = (Officer) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Officer [id=" + id + ", badge=" + badge + ", imageUrl=" + imageUrl + ", person=" + person + "]";
	}

}
