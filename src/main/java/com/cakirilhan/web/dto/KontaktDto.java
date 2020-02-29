package com.cakirilhan.web.dto;

import com.cakirilhan.domain.kunde.Kunde;

public class KontaktDto {

	private String email;

	private Long kontaktId;

	private String anrede;

	private String titel;

	private String vorname;

	private String nachname;

	private String telefon;

	private String mobil;

	private String fax;

	private Kunde kunde;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public Kunde getKunde() {
		return kunde;
	}

	public void setKunde(Kunde kunde) {
		this.kunde = kunde;
	}

	@Override
	public String toString() {
		return "KontaktDto [email=" + email + ", kontaktId=" + kontaktId
				+ ", anrede=" + anrede + ", titel=" + titel + ", vorname="
				+ vorname + ", nachname=" + nachname + ", telefon=" + telefon
				+ ", mobil=" + mobil + ", fax=" + fax + ", kunde=" + kunde
				+ "]";
	}
	

}
