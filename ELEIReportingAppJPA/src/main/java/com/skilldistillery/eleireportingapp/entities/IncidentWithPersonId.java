package com.skilldistillery.eleireportingapp.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;

@Embeddable
public class IncidentWithPersonId implements Serializable {

	@Column(name = "person_id")
	private int personId;

	@Column(name = "incident_id")
	private int incidentId;

	public IncidentWithPersonId() {
		super();
	}

	public IncidentWithPersonId(int personId, int incidentId) {
		this.personId = personId;
		this.incidentId = incidentId;
	}

	public int getPersonId() {
		return personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}

	public int getIncidentId() {
		return incidentId;
	}

	public void setIncidentId(int incidentId) {
		this.incidentId = incidentId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(incidentId, personId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IncidentWithPersonId other = (IncidentWithPersonId) obj;
		return incidentId == other.incidentId && personId == other.personId;
	}

	@Override
	public String toString() {
		return "IncidentWithPersonId [personId=" + personId + ", incidentId=" + incidentId + "]";
	}

}
