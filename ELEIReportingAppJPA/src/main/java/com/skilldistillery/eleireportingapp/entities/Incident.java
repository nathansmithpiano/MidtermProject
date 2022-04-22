package com.skilldistillery.eleireportingapp.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Incident {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "reason_for_contact")
	private String reasonForContact;

	private String location;

	private String description;

	private boolean flag;

	@Column(name = "incident_date")
	private LocalDateTime incidentDate;

	public Incident() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getReasonForContact() {
		return reasonForContact;
	}

	public void setReasonForContact(String reasonForContact) {
		this.reasonForContact = reasonForContact;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public LocalDateTime getIncidentDate() {
		return incidentDate;
	}

	public void setIncidentDate(LocalDateTime incidentDate) {
		this.incidentDate = incidentDate;
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
		Incident other = (Incident) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Incident [id=" + id + ", reasonForContact=" + reasonForContact + ", location=" + location
				+ ", description=" + description + ", flag=" + flag + ", incidentDate=" + incidentDate + "]";
	}

}
