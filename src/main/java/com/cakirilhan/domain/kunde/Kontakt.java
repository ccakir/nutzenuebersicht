package com.cakirilhan.domain.kunde;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({
	@NamedQuery(name="Kontakt.findContactByLastname", query="SELECT k FROM Kontakt k WHERE k.nachname LIKE :letter")
})

public class Kontakt {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long kontaktId;

	private String anrede;

	private String titel;

	private String vorname;

	private String nachname;

	private String telefon;

	private String mobil;

	private String fax;

	private String email;

	@ManyToOne
	@JoinColumn(name = "kundeId")
	private Kunde kunde;

	public Kontakt() {

	}

	public Kontakt(String anrede, String titel, String vorname,
			String nachname, String telefon, String mobil, String fax,
			String email, Kunde kunde) {
		this.anrede = anrede;
		this.titel = titel;
		this.vorname = vorname;
		this.nachname = nachname;
		this.telefon = telefon;
		this.mobil = mobil;
		this.fax = fax;
		this.email = email;
		this.kunde = kunde;
	}

	@Override
	public String toString() {
		return "Kontakt [kontaktId=" + kontaktId + ", anrede=" + anrede
				+ ", titel=" + titel + ", vorname=" + vorname + ", nachname="
				+ nachname + ", telefon=" + telefon + ", mobil=" + mobil
				+ ", fax=" + fax + ", email=" + email + ", kunde=" + kunde
				+ "]";
	}

	public Long getKontaktId() {
		return kontaktId;
	}

	public void setKontaktId(Long kontaktId) {
		this.kontaktId = kontaktId;
	}

	public String getAnrede() {
		return anrede;
	}

	public void setAnrede(String anrede) {
		this.anrede = anrede;
	}

	public String getTitel() {
		return titel;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getMobil() {
		return mobil;
	}

	public void setMobil(String mobil) {
		this.mobil = mobil;
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

	public Kunde getKunde() {
		return kunde;
	}

	public void setKunde(Kunde kunde) {
		this.kunde = kunde;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((anrede == null) ? 0 : anrede.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((fax == null) ? 0 : fax.hashCode());
		result = prime * result
				+ ((kontaktId == null) ? 0 : kontaktId.hashCode());
		result = prime * result + ((kunde == null) ? 0 : kunde.hashCode());
		result = prime * result + ((mobil == null) ? 0 : mobil.hashCode());
		result = prime * result
				+ ((nachname == null) ? 0 : nachname.hashCode());
		result = prime * result + ((telefon == null) ? 0 : telefon.hashCode());
		result = prime * result + ((titel == null) ? 0 : titel.hashCode());
		result = prime * result + ((vorname == null) ? 0 : vorname.hashCode());
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
		Kontakt other = (Kontakt) obj;
		if (anrede == null) {
			if (other.anrede != null)
				return false;
		} else if (!anrede.equals(other.anrede))
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
		if (kontaktId == null) {
			if (other.kontaktId != null)
				return false;
		} else if (!kontaktId.equals(other.kontaktId))
			return false;
		if (kunde == null) {
			if (other.kunde != null)
				return false;
		} else if (!kunde.equals(other.kunde))
			return false;
		if (mobil == null) {
			if (other.mobil != null)
				return false;
		} else if (!mobil.equals(other.mobil))
			return false;
		if (nachname == null) {
			if (other.nachname != null)
				return false;
		} else if (!nachname.equals(other.nachname))
			return false;
		if (telefon == null) {
			if (other.telefon != null)
				return false;
		} else if (!telefon.equals(other.telefon))
			return false;
		if (titel == null) {
			if (other.titel != null)
				return false;
		} else if (!titel.equals(other.titel))
			return false;
		if (vorname == null) {
			if (other.vorname != null)
				return false;
		} else if (!vorname.equals(other.vorname))
			return false;
		return true;
	}

}
