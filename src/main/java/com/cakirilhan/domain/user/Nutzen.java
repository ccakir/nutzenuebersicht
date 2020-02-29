package com.cakirilhan.domain.user;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import org.springframework.format.annotation.DateTimeFormat;

import com.cakirilhan.domain.kunde.Kunde;
import com.cakirilhan.domain.kunde.Ort;
import com.cakirilhan.domain.kunde.Taetigkeit;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@NamedQueries({
	@NamedQuery(name="Nutzen.sucheMitKunde", query="SELECT n FROM Nutzen n WHERE n.kunde.kundeId=:kundeId ORDER BY n.id DESC"),
	@NamedQuery(name="Nutzen.sucheMitKundeundUser", query="SELECT n FROM Nutzen n WHERE n.kunde.kundeId=:kundeId AND n.user.id=:userId ORDER BY n.id DESC"),
	@NamedQuery(name="Nutzen.sucheMitUser", query="SELECT n FROM Nutzen n WHERE n.user.id=:mitarbeiterId ORDER BY n.id DESC"),
	@NamedQuery(name="Nutzen.sucheMitDatum", query="SELECT n FROM Nutzen n WHERE n.datum BETWEEN :vonDatum AND :bisDatum ORDER BY n.id DESC"),
	@NamedQuery(name="Nutzen.allOrderById", query="SELECT n FROM Nutzen n ORDER BY n.id DESC")
})
public class Nutzen {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(nullable = false)
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate datum;
	
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="user_id",nullable = false)
	@JsonIgnore
	private User user;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="kunde_id",nullable = false)
	private Kunde kunde;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="ort_id",nullable = false)
	private Ort ort;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="taetigkeit_id",nullable = false)
	private Taetigkeit taetigkeit;
	
	@Lob
	private String details;
	@Lob
	private String sondernleistung;
	
	@Column(nullable = false)
	private double stunde;
	
	@Column(columnDefinition="tinyint(1) default 1")
	private boolean freigabe;
	
	

	public Nutzen() {
		
	}
	

	@Override
	public String toString() {
		return "Nutzen [id=" + id + ", datum=" + datum + ", user=" + user
				+ ", kunde=" + kunde + ", ort=" + ort + ", taetigkeit="
				+ taetigkeit + ", details=" + details + ", sondernleistung="
				+ sondernleistung + ", stunde=" + stunde + ", freigabe="
				+ freigabe + "]";
	}


	public Nutzen(LocalDate datum, User user, Kunde kunde, Ort ort,
			Taetigkeit taetigkeit, String details, String sondernleistung,
			double stunde, boolean freigabe) {
		this.datum = datum;
		this.user = user;
		this.kunde = kunde;
		this.ort = ort;
		this.taetigkeit = taetigkeit;
		this.details = details;
		this.sondernleistung = sondernleistung;
		this.stunde = stunde;
		this.freigabe = freigabe;
	}


	


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	

	


	




	


	public LocalDate getDatum() {
		return datum;
	}


	public void setDatum(LocalDate datum) {
		this.datum = datum;
	}


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public Taetigkeit getTaetigkeit() {
		return taetigkeit;
	}

	public void setTaetigkeit(Taetigkeit taetigkeit) {
		this.taetigkeit = taetigkeit;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getSondernleistung() {
		return sondernleistung;
	}

	public void setSondernleistung(String sondernleistung) {
		this.sondernleistung = sondernleistung;
	}

	public double getStunde() {
		return stunde;
	}

	public void setStunde(double stunde) {
		this.stunde = stunde;
	}

	public boolean isFreigabe() {
		return freigabe;
	}

	public void setFreigabe(boolean freigabe) {
		this.freigabe = freigabe;
	}
	
	
	

}
