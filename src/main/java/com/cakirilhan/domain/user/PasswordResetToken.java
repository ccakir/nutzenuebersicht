package com.cakirilhan.domain.user;

import java.util.Calendar;
import java.util.Date;

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
public class PasswordResetToken {
	
	@Transient
	final int EXPIRY_DATE = 60*24;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String token;
	
	@OneToOne(fetch = FetchType.EAGER)
	private User user;
	@Temporal(TemporalType.TIMESTAMP)
	private Date expiryDate;
	
	
	public PasswordResetToken() {
		this.expiryDate = calcExpiryDate(EXPIRY_DATE);
	}

     
	public PasswordResetToken(String token, User user) {
		this.token = token;
		this.user = user;
		this.expiryDate = calcExpiryDate(EXPIRY_DATE);
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Date getExpiryDate() {
		return expiryDate;
	}


	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
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
		PasswordResetToken other = (PasswordResetToken) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
