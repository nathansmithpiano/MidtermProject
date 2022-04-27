package com.skilldistillery.eleireportingapp.entities;

import java.sql.Timestamp;
import java.time.LocalDateTime;
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
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Incident {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name= "officer_id")
	private Officer officer;
	
	@ManyToOne
	@JoinColumn(name= "department_id")
	private Department department;

	@Column(name = "reason_for_contact")
	private String reasonForContact;

	private String location;

	private String description;

	private boolean flag;

	@Column(name = "incident_date")
	@CreationTimestamp
	private Timestamp incidentDate;

	@ManyToOne
	@JoinColumn(name = "address_id")
	private Address address;

	@ManyToOne
	@JoinColumn(name = "case_id")
	private CaseFile caseFile;

	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "incident_person", joinColumns = @JoinColumn(name = "incident_id"), inverseJoinColumns = @JoinColumn(name = "person_id"))
	private List<Person> persons;

	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "incident_note", joinColumns = @JoinColumn(name = "incident_id"), inverseJoinColumns = @JoinColumn(name = "note_id"))
	private List<Note> notes;

	public Incident() {
		super();
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

	public Officer getOfficer() {
		return officer;
	}

	public void setOfficer(Officer officer) {
		this.officer = officer;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public Timestamp getIncidentDate() {
		return incidentDate;
	}

	public void setIncidentDate(Timestamp incidentDate) {
		this.incidentDate = incidentDate;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public CaseFile getCaseFile() {
		return caseFile;
	}

	public void setCaseFile(CaseFile caseFile) {
		this.caseFile = caseFile;
	}

	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
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

	public void addNote(Note note) {
		if (notes == null) {
			notes = new ArrayList<>();
		}
		if (!notes.contains(note)) {
			notes.add(note);
		}
	}

	public boolean removeNote(Note note) {
		if (notes != null && notes.contains(note)) {
			notes.remove(note);
		}
		return !notes.contains(note);
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
		return "Incident [id=" + id + ", officer=" + officer + ", department=" + department + ", reasonForContact="
				+ reasonForContact + ", location=" + location + ", description=" + description + ", flag=" + flag
				+ ", incidentDate=" + incidentDate + ", address=" + address + ", caseFile=" + caseFile + ", persons="
				+ persons + ", notes=" + notes + "]";
	}

}
