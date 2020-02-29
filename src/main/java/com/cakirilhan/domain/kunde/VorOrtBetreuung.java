package com.cakirilhan.domain.kunde;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;



import javax.validation.constraints.NotNull;

import com.cakirilhan.domain.user.User;


@Entity
@NamedQueries({
	@NamedQuery(name="VorOB.findAllGroupByLocationAndCustomer", query="SELECT v FROM VorOrtBetreuung v GROUP BY v.kunde, v.ort"),
	@NamedQuery(name="VorOB.findAllOrderById", query="SELECT v FROM VorOrtBetreuung v ORDER BY v.id DESC"),
	@NamedQuery(name="VorOB.doppelKontrolle", query = "SELECT COUNT(v.id) FROM VorOrtBetreuung v WHERE v.user.id=:user AND v.ort.ortId=:ort AND v.kunde.kundeId=:kunde"),
	@NamedQuery(name="Kunde.findCustomerLocation", query="SELECT v.kunde, o.ortname, o.adresse, o.land, o.plz, v.user FROM VorOrtBetreuung v INNER JOIN Ort o ON v.ort.ortId=o.ortId WHERE v.kunde.kundeId=:kundeId ")
	
})
public class VorOrtBetreuung {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@NotNull
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="user_id",nullable=false)
	private User user;
	
	@NotNull
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="ort_id",nullable=false)
	private Ort ort;
	
	
	@NotNull
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="kunde_id",nullable=false)
	private Kunde kunde;
	
	

	public VorOrtBetreuung(User user, Ort ort, Kunde kunde) {
		this.user = user;
		this.ort = ort;
		this.kunde = kunde;
	}

	public VorOrtBetreuung() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Ort getOrt() {
		return ort;
	}

	public void setOrt(Ort ort) {
		this.ort = ort;
	}

	public Kunde getKunde() {
		return kunde;
	}

	public void setKunde(Kunde kunde) {
		this.kunde = kunde;
	}

	@Override
	public String toString() {
		return "VorOrtBetreuung [id=" + id + ", user=" + user + ", ort=" + ort
				+ ", kunde=" + kunde + "]";
	}

	
}
