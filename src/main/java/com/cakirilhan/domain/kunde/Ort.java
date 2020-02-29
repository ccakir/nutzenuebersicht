package com.cakirilhan.domain.kunde;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;


@Entity
@NamedQueries({
	@NamedQuery(name="Ort.bringAlleOrtsnamen", query ="SELECT o.ortname,o.id FROM Ort o")
	
})
public class Ort {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ortId;
	
	private String ortname;
	
	private String adresse;
	
	private String plz;
	
	private String land;
	
	
	
	

	public Ort() {
		
	}

	public Ort(String ortname, String adresse, String plz, String land) {
		this.ortname = ortname;
		this.adresse = adresse;
		this.plz = plz;
		this.land = land;
	}

	public Long getOrtId() {
		return ortId;
	}

	public void setOrtId(Long ortId) {
		this.ortId = ortId;
	}

	public String getOrtname() {
		return ortname;
	}

	public void setOrtname(String ortname) {
		this.ortname = ortname;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getPlz() {
		return plz;
	}

	public void setPlz(String plz) {
		this.plz = plz;
	}

	public String getLand() {
		return land;
	}

	public void setLand(String land) {
		this.land = land;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adresse == null) ? 0 : adresse.hashCode());
		result = prime * result + ((land == null) ? 0 : land.hashCode());
		result = prime * result + ((ortId == null) ? 0 : ortId.hashCode());
		result = prime * result + ((ortname == null) ? 0 : ortname.hashCode());
		result = prime * result + ((plz == null) ? 0 : plz.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ort other = (Ort) obj;
		if (adresse == null) {
			if (other.adresse != null)
				return false;
		} else if (!adresse.equals(other.adresse))
			return false;
		if (land == null) {
			if (other.land != null)
				return false;
		} else if (!land.equals(other.land))
			return false;
		if (ortId == null) {
			if (other.ortId != null)
				return false;
		} else if (!ortId.equals(other.ortId))
			return false;
		if (ortname == null) {
			if (other.ortname != null)
				return false;
		} else if (!ortname.equals(other.ortname))
			return false;
		if (plz == null) {
			if (other.plz != null)
				return false;
		} else if (!plz.equals(other.plz))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Ort [ortId=" + ortId + ", ortname=" + ortname + ", adresse="
				+ adresse + ", plz=" + plz + ", land=" + land + "]";
	}




	

}
