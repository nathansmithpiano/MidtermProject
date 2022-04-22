package com.skilldistillery.eleireportingapp.entities;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Officer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String badge;

	@Column(name = "image_url")
	private String imageUrl;

	public Officer() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBadge() {
		return badge;
	}

	public void setBadge(String badge) {
		this.badge = badge;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
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
		Officer other = (Officer) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Officer [id=" + id + ", badge=" + badge + ", imageUrl=" + imageUrl + "]";
	}

}
