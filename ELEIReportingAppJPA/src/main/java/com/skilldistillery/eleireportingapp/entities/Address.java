package com.skilldistillery.eleireportingapp.entities;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String description;

	@Column(name = "street1")
	private String streetOne;

	@Column(name = "street2")
	private String streetTwo;

	private String city;

	@Column(name = "state_code")
	private String stateCode;

	private Integer zip;

	private boolean flag;
	
	public Address() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStreetOne() {
		return streetOne;
	}

	public void setStreetOne(String streetOne) {
		this.streetOne = streetOne;
	}

	public String getStreetTwo() {
		return streetTwo;
	}

	public void setStreetTwo(String streetTwo) {
		this.streetTwo = streetTwo;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public Integer getZip() {
		return zip;
	}

	public void setZip(Integer zip) {
		this.zip = zip;
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
		Address other = (Address) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", description=" + description + ", streetOne=" + streetOne + ", streetTwo="
				+ streetTwo + ", city=" + city + ", stateCode=" + stateCode + ", zip=" + zip + ", flag=" + flag + "]";
	}

}
