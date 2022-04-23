package com.skilldistillery.eleireportingapp.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
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

	public Officer() {
		super();
	}

	public Officer clone() {
		return this.clone();
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

	public void addDepartment(Department department) {
		if (departments == null) {
			departments = new ArrayList<>();
		}
		if (!departments.contains(department)) {
			departments.add(department);
		}
	}

	public Department removeDepartment(Department department) {
		Department backup = department.clone();
		if (departments != null && departments.contains(department)) {
			departments.remove(department);
		}
		return backup;
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
