package com.skilldistillery.eleireportingapp.entities;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "incident_with_person")
public class IncidentWithPerson {

	@EmbeddedId
	private IncidentWithPersonId incidentWithPersonId;

	@Column(name = "suspected_crime")
	private String suspectedCrime;

	@Column(name = "age_minimum")
	private int ageMinimum;

	@Column(name = "age_maximum")
	private int ageMaximum;

	private String notes;

	private String description;

	public IncidentWithPerson() {
		super();
	}

	public IncidentWithPersonId getIncidentWithPersonId() {
		return incidentWithPersonId;
	}

	public void setIncidentWithPersonId(IncidentWithPersonId incidentWithPersonId) {
		this.incidentWithPersonId = incidentWithPersonId;
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

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		return Objects.hash(incidentWithPersonId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IncidentWithPerson other = (IncidentWithPerson) obj;
		return Objects.equals(incidentWithPersonId, other.incidentWithPersonId);
	}

	@Override
	public String toString() {
		return "IncidentWithPerson [suspectedCrime=" + suspectedCrime + ", ageMinimum=" + ageMinimum + ", ageMaximum="
				+ ageMaximum + ", notes=" + notes + ", description=" + description + "]";
	}

}
