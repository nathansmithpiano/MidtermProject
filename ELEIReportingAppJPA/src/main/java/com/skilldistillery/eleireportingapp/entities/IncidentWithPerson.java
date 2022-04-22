package com.skilldistillery.eleireportingapp.entities;

import javax.persistence.Column;

public class IncidentWithPerson {
	
	@Column(name = "suspected_crime")
	private String suspectedCrime;
	
	@Column(name = "age_minimum")
	private int ageMinimum;
	
	@Column(name = "age_maximum")
	private int ageMaximum;
	
	private String notes;
	
	private String description;
	
	public IncidentWithPerson() {}

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
	
	//TODO Hashcode and .Equals
	
	//TODO toString

}
