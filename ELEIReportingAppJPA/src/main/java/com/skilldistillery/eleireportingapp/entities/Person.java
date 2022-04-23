package com.skilldistillery.eleireportingapp.entities;

import java.time.LocalDate;
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

@Entity
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "middle_name")
	private String middleName;

	@Column(name = "last_name")
	private String lastName;

	private String title;

	@Column(name = "birthdate")
	private LocalDate birthDate;

	private String gender;

	private Integer address_id;

	private boolean flag;

	private String description;

	@ManyToMany(mappedBy = "persons")
	private List<Address> addresses;

	@ManyToMany(mappedBy = "persons")
	private List<Incident> incidents;

	@ManyToOne
	@JoinColumn(name = "ethnicity_id")
	private Ethnicity ethnicity;

	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "person_note", joinColumns = @JoinColumn(name = "person_id"), inverseJoinColumns = @JoinColumn(name = "note_id"))
	private List<Note> notes;

	public Person() {
		super();
	}

	public Person clone() {
		return this.clone();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getAddress_id() {
		return address_id;
	}

	public void setAddress_id(Integer address_id) {
		this.address_id = address_id;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public List<Incident> getIncidents() {
		return incidents;
	}

	public void setIncidents(List<Incident> incidents) {
		this.incidents = incidents;
	}

	public Ethnicity getEthnicity() {
		return ethnicity;
	}

	public void setEthnicity(Ethnicity ethnicity) {
		this.ethnicity = ethnicity;
	}

	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}

	public void addAddress(Address address) {
		if (addresses == null) {
			addresses = new ArrayList<>();
		}
		if (!addresses.contains(address)) {
			addresses.add(address);
		}
	}

	public Address removeAddress(Address address) {
		Address backup = address.clone();
		if (addresses != null && addresses.contains(address)) {
			addresses.remove(address);
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

	public void addNote(Note note) {
		if (notes == null) {
			notes = new ArrayList<>();
		}
		if (!notes.contains(note)) {
			notes.add(note);
		}
	}

	public Note removeNote(Note note) {
		Note backup = note.clone();
		if (notes != null && notes.contains(note)) {
			notes.remove(note);
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
		Person other = (Person) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", firstName=" + firstName + ", middleName=" + middleName + ", lastName=" + lastName
				+ ", title=" + title + ", birthDate=" + birthDate + ", gender=" + gender + ", address_id=" + address_id
				+ ", flag=" + flag + ", description=" + description + ", ethnicity=" + ethnicity + "]";
	}

}
