package com.cakirilhan.domain.kunde;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Taetigkeit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String name;
	
	private String beschreibung;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBeschreibung() {
		return beschreibung;
	}

	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	
	public Taetigkeit(String name, String beschreibung) {
		this.name = name;
		this.beschreibung = beschreibung;
	}

	public Taetigkeit() {
		}

	@Override
	public String toString() {
		return "Taetigkeit [id=" + id + ", name=" + name + ", beschreibung="
				+ beschreibung + "]";
	}
	

	
}
