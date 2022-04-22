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
import javax.persistence.Table;



@Entity
@Table(name = "case_file")
public class CaseFile implements Cloneable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "suspected_crime")
	private String suspectedCrime;

	private String description;

	private boolean flag;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "case_file_note", 
		joinColumns = @JoinColumn(name = "case_id"), 
		inverseJoinColumns = @JoinColumn(name = "note_id"))
	private List<Note> notes;
	
	public CaseFile() {
		super();
	}
	
	public CaseFile clone() {
		return this.clone();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSuspectedCrime() {
		return suspectedCrime;
	}

	public void setSuspectedCrime(String suspectedCrime) {
		this.suspectedCrime = suspectedCrime;
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
	
	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}
	
	public void addNote(Note note) {
		if (notes == null) {
			notes = new ArrayList<>();
		}
		if (!notes.contains(note)) {
			notes.add(note);
			note.addCaseFile(this);
		}
	}

	public Note removeNote(Note note) {
		Note backup = (Note) note.clone();
		if (notes != null && notes.contains(note)) {
			notes.remove(note);
			note.removeCaseFile(this);
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
		CaseFile other = (CaseFile) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "CaseFile [id=" + id + ", suspectedCrime=" + suspectedCrime + ", description=" + description + ", flag="
				+ flag + "]";
	}

}
