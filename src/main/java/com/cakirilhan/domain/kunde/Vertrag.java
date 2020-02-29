package com.cakirilhan.domain.kunde;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Entity
@NamedQueries({
		@NamedQuery(name = "Vertrag.findAllOrderById", query = "SELECT v FROM Vertrag v ORDER BY v.id DESC"),
		@NamedQuery(name = "Vertrag.findByKundeAndOrt", query = "SELECT COUNT(v.id) FROM Vertrag v WHERE v.kunde.kundeId=:kundeId AND v.ort.ortId=:ortId")

})
public class Vertrag {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne
	private Kunde kunde;

	@ManyToOne
	private Ort ort;

	@Transient
	private String file1;

	private String inhalt;

	public Vertrag() {

	}

	public Vertrag(Kunde kunde, Ort ort, String inhalt) {
		this.kunde = kunde;
		this.ort = ort;
		this.inhalt = inhalt;

	}

	public String getFile1() {
		return file1;
	}

	public void setFile1(String file1) {
		this.file1 = file1;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Kunde getKunde() {
		return kunde;
	}

	public void setKunde(Kunde kunde) {
		this.kunde = kunde;
	}

	public Ort getOrt() {
		return ort;
	}

	public void setOrt(Ort ort) {
		this.ort = ort;
	}

	public String getInhalt() {
		return inhalt;
	}

	public void setInhalt(String inhalt) {
		this.inhalt = inhalt;
	}

	@Override
	public String toString() {
		return "Vertrag [id=" + id + ", kunde=" + kunde + ", ort=" + ort
				+ ", inhalt=" + inhalt + "]";
	}

}
