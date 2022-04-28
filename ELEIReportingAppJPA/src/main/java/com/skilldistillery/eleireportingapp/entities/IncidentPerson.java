package com.skilldistillery.eleireportingapp.entities;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "incident_person")
public class IncidentPerson {

	@EmbeddedId
	private IncidentPersonId IncidentPersonId;

	@Column(name = "suspected_crime")
	private String suspectedCrime;

	@Column(name = "age_minimum")
	private int ageMinimum;

	@Column(name = "age_maximum")
	private int ageMaximum;

	private String description;
	
	@ManyToOne
	@JoinColumn(name = "incident_id")
	@MapsId(value = "incidentId")
	private Incident incident;
	
	@ManyToOne
	@JoinColumn(name = "ethinicity_id")
	@MapsId(value = "incidentId")
	private Ethnicity ethnicity;
	
	@ManyToOne
	@JoinColumn(name = "person_id")
	@MapsId(value = "personId")
	private Person person;

	public IncidentPerson() {
		super();
	}

	public IncidentPersonId getIncidentPersonId() {
		return IncidentPersonId;
	}

	public void setIncidentPersonId(IncidentPersonId IncidentPersonId) {
		this.IncidentPersonId = IncidentPersonId;
	}

	public String getSuspectedCrime() {
		return suspectedCrime;
	}

	public void setSuspectedCrime(String suspectedCrime) {
		this.suspectedCrime = suspectedCrime;
	}

	public int getAgeMinimum() {
		return ageMinimum;
	}

	public void setAgeMinimum(int ageMinimum) {
		this.ageMinimum = ageMinimum;
	}

	public int getAgeMaximum() {
		return ageMaximum;
	}

	public void setAgeMaximum(int ageMaximum) {
		this.ageMaximum = ageMaximum;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Incident getIncident() {
		return incident;
	}

	public void setIncident(Incident incident) {
		this.incident = incident;
	}

	public Ethnicity getEthnicity() {
		return ethnicity;
	}

	public void setEthnicity(Ethnicity ethnicity) {
		this.ethnicity = ethnicity;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	@Override
	public int hashCode() {
		return Objects.hash(IncidentPersonId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IncidentPerson other = (IncidentPerson) obj;
		return Objects.equals(IncidentPersonId, other.IncidentPersonId);
	}

	@Override
	public String toString() {
		return "IncidentPerson [suspectedCrime=" + suspectedCrime + ", ageMinimum=" + ageMinimum + ", ageMaximum="
				+ ageMaximum + ", description=" + description + "]";
	}

}
