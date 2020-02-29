package com.cakirilhan.domain.user;

import java.util.Date;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


@Entity
public class VerificationToken {
	
	@Transient
	final int EXPIRY_DATE = 60*24;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@OneToOne(fetch = FetchType.EAGER)
	private User user;
	
	private String token;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date expiryDate;
	
	public VerificationToken() {
		this.expiryDate = calcExpiryDate(EXPIRY_DATE);
	}
	
	

	public VerificationToken(User user, String token) {
		this.user = user;
		this.token = token;
		this.expiryDate = calcExpiryDate(EXPIRY_DATE);
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	public String getToken() {
		return token;
	}



	public void setToken(String token) {
		this.token = token;
	}



	public Date getExpiredDate() {
		return expiryDate;
	}



	public void setExpiredDate(Date expiredDate) {
		this.expiryDate = expiredDate;
	}



	public int getEXPIRY_DATE() {
		return EXPIRY_DATE;
	}



	private Date calcExpiryDate(int EXPIRY_DATE) {
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(new Date().getTime());
		calendar.add(Calendar.MINUTE, EXPIRY_DATE);
		
		return new Date(calendar.getTime().getTime());
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		VerificationToken other = (VerificationToken) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
