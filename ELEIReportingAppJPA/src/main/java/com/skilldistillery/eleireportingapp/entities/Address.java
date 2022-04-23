package com.skilldistillery.eleireportingapp.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String description;

	@Column(name = "street1")
	private String streetOne;

	@Column(name = "street2")
	private String streetTwo;

	private String city;

	@Column(name = "state_code")
	private String stateCode;

	private Integer zip;

	private boolean flag;

	@OneToMany(mappedBy = "address")
	private List<Incident> incidents;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "person_address", 
		joinColumns = @JoinColumn(name = "address_id"), 
		inverseJoinColumns = @JoinColumn(name = "person_id"))
	private List<Person> persons;

	public Address() {
	}

	public Address clone() {
		return this.clone();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStreetOne() {
		return streetOne;
	}

	public void setStreetOne(String streetOne) {
		this.streetOne = streetOne;
	}

	public String getStreetTwo() {
		return streetTwo;
	}

	public void setStreetTwo(String streetTwo) {
		this.streetTwo = streetTwo;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public Integer getZip() {
		return zip;
	}

	public void setZip(Integer zip) {
		this.zip = zip;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public List<Incident> getIncidents() {
		return incidents;
	}

	public void setIncidents(List<Incident> incidents) {
		this.incidents = incidents;
	}

//	TODO Check the add /remove methods

	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

	public void addIncident(Incident incident) {
		if (incidents == null) {
			incidents = new ArrayList<>();
		}
		if (!incidents.contains(incident)) {
			incidents.add(incident);
		}
	}

	public Incident removeIncident(Incident incident) {
		Incident backup = incident.clone();
		if (incidents != null && incidents.contains(incident)) {
			incidents.remove(incident);
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
		Address other = (Address) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", description=" + description + ", streetOne=" + streetOne + ", streetTwo="
				+ streetTwo + ", city=" + city + ", stateCode=" + stateCode + ", zip=" + zip + ", flag=" + flag + "]";
	}

}
