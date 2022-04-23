package com.skilldistillery.eleireportingapp.entities;

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

@Entity
public class Note implements Cloneable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private LocalDateTime created;

	private LocalDateTime updated;

	private String content;

	@Column(name = "user_id")
	private int userId;

	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "case_file_note", joinColumns = @JoinColumn(name = "case_id"), inverseJoinColumns = @JoinColumn(name = "note_id"))
	private List<CaseFile> caseFiles;

	@ManyToMany(mappedBy = "notes")
	private List<Incident> incidents;

	@ManyToMany(mappedBy = "notes")
	private List<Person> persons;

	public Note() {
		super();
	}

	public Note clone() {
		return this.clone();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	public LocalDateTime getUpdated() {
		return updated;
	}

	public void setUpdated(LocalDateTime updated) {
		this.updated = updated;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public List<CaseFile> getCaseFiles() {
		return caseFiles;
	}

	public void setCaseFiles(List<CaseFile> caseFiles) {
		this.caseFiles = caseFiles;
	}

	public List<Incident> getIncidents() {
		return incidents;
	}

	public void setIncidents(List<Incident> incidents) {
		this.incidents = incidents;
	}

	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

	public void addCaseFile(CaseFile caseFile) {
		if (caseFiles == null) {
			caseFiles = new ArrayList<>();
		}
		if (!caseFiles.contains(caseFile)) {
			caseFiles.add(caseFile);
			caseFile.addNote(this);
		}
	}

	public CaseFile removeCaseFile(CaseFile caseFile) {
		CaseFile backup = caseFile.clone();
		if (caseFiles != null && caseFiles.contains(caseFile)) {
			caseFiles.remove(caseFile);
			caseFile.removeNote(this);
		}
		return backup;
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

	public void addPerson(Person person) {
		if (persons == null) {
			persons = new ArrayList<>();
		}
		if (!persons.contains(person)) {
			persons.add(person);
		}
	}

	public Person removePerson(Person person) {
		Person backup = person.clone();
		if (persons != null && persons.contains(person)) {
			persons.remove(person);
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
		Note other = (Note) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Note [id=" + id + ", created=" + created + ", updated=" + updated + ", content=" + content + ", userId="
				+ userId + "]";
	}

}
