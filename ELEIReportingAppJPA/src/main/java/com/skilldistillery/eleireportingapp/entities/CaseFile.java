package com.skilldistillery.eleireportingapp.entities;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "case_file")
public class CaseFile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "suspected_crime")
	private String suspectedCrime;

	private String description;

	private boolean flag;
	
	public CaseFile() {
		super();
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
