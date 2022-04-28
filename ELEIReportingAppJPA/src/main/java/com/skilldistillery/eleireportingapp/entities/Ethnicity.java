package com.skilldistillery.eleireportingapp.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Ethnicity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

//	@OneToMany(mappedBy = "ethnicity", fetch = FetchType.EAGER)
	@OneToMany(mappedBy = "ethnicity")
	private List<Person> persons;
	
	@OneToMany(mappedBy = "ethnicity")
	private List<IncidentPerson> incident_person;

	public Ethnicity() {
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

	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

	public void addPerson(Person person) {
		if (persons == null) {
			persons = new ArrayList<>();
		}
		if (!persons.contains(person)) {
			persons.add(person);
		}
	}

	public boolean removePerson(Person person) {
		if (persons != null && persons.contains(person)) {
			persons.remove(person);
		}
		return !persons.contains(person);
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
		Ethnicity other = (Ethnicity) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Ethnicity [id=" + id + ", name=" + name + "]";
	}

}
