
package com.cakirilhan.domain.kunde;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;


@Entity

public class Kunde {
	
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long kundeId;
	
	private String name;
	
	private String adresse;
	
	private String plz;
	
	private String ortsname;
	
	private String land;
	
	private String telefon;
	
	private String web;
	
	private String fax;
	
	private String email;
	
	

	public Kunde() {
		}

	@Override
	public String toString() {
		return "Kunde [kundeId=" + kundeId + ", name=" + name + ", adresse="
				+ adresse + ", plz=" + plz + ", ortsname=" + ortsname
				+ ", land=" + land + ", telefon=" + telefon + ", web=" + web
				+ ", fax=" + fax + ", email=" + email + "]";
	}

	
	public Kunde( String name, String adresse, String plz,
			String ortsname, String land, String telefon, String web,
			String fax, String email) {
		
		this.name = name;
		this.adresse = adresse;
		this.plz = plz;
		this.ortsname = ortsname;
		this.land = land;
		this.telefon = telefon;
		this.web = web;
		this.fax = fax;
		this.email = email;
	}


	public Long getKundeId() {
		return kundeId;
	}

	public void setKundeId(Long kundeId) {
		this.kundeId = kundeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getOrtsname() {
		return ortsname;
	}

	public void setOrtsname(String ortsname) {
		this.ortsname = ortsname;
	}

	public String getLand() {
		return land;
	}

	public void setLand(String land) {
		this.land = land;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adresse == null) ? 0 : adresse.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((fax == null) ? 0 : fax.hashCode());
		result = prime * result + ((kundeId == null) ? 0 : kundeId.hashCode());
		result = prime * result + ((land == null) ? 0 : land.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((ortsname == null) ? 0 : ortsname.hashCode());
		result = prime * result + ((plz == null) ? 0 : plz.hashCode());
		result = prime * result + ((telefon == null) ? 0 : telefon.hashCode());
		result = prime * result + ((web == null) ? 0 : web.hashCode());
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
		Kunde other = (Kunde) obj;
		if (adresse == null) {
			if (other.adresse != null)
				return false;
		} else if (!adresse.equals(other.adresse))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (fax == null) {
			if (other.fax != null)
				return false;
		} else if (!fax.equals(other.fax))
			return false;
		if (kundeId == null) {
			if (other.kundeId != null)
				return false;
		} else if (!kundeId.equals(other.kundeId))
			return false;
		if (land == null) {
			if (other.land != null)
				return false;
		} else if (!land.equals(other.land))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (ortsname == null) {
			if (other.ortsname != null)
				return false;
		} else if (!ortsname.equals(other.ortsname))
			return false;
		if (plz == null) {
			if (other.plz != null)
				return false;
		} else if (!plz.equals(other.plz))
			return false;
		if (telefon == null) {
			if (other.telefon != null)
				return false;
		} else if (!telefon.equals(other.telefon))
			return false;
		if (web == null) {
			if (other.web != null)
				return false;
		} else if (!web.equals(other.web))
			return false;
		return true;
	}



}