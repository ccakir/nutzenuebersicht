package com.cakirilhan.web.dto;

import java.util.Set;

import javax.validation.constraints.NotNull;

import com.cakirilhan.domain.kunde.Ort;
import com.cakirilhan.domain.user.Role;
import com.cakirilhan.user.validator.ValidEmail;
import com.cakirilhan.user.validator.ValidPassword;

public class UserDto {
	
	private Long id;
	@NotNull
	private String vorname;
	@NotNull
	private String nachname;
	@NotNull
	private String username;
	
	@NotNull
	@ValidEmail
	private String email;
	
	private String password;
	private String passwordConfirm;
	
	private Set<Role> role;
	
	private Ort ort;
	
	
	public Ort getOrt() {
		return ort;
	}




	public void setOrt(Ort ort) {
		this.ort = ort;
	}




	public String getPasswordConfirm() {
		return passwordConfirm;
	}




	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}




	public UserDto() {
	
	}
	
	


	public String getPassword() {
		return password;
	}




	public void setPassword(String password) {
		this.password = password;
	}




	public Long getId() {
		return id;
	}




	public void setId(Long id) {
		this.id = id;
	}




	public UserDto(String vorname, String nachname, String username,
			String email, String password, boolean enabled,
			boolean accountExpired, boolean credentialsNonExpired,
			boolean accountNonLocked, Set<Role> role) {
		this.vorname = vorname;
		this.nachname = nachname;
		this.username = username;
		this.email = email;
		
		
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}



	public Set<Role> getRole() {
		return role;
	}


	public void setRole(Set<Role> role) {
		this.role = role;
	}




	@Override
	public String toString() {
		return "UserDto [id=" + id + ", vorname=" + vorname + ", nachname="
				+ nachname + ", username=" + username + ", email=" + email
				+ ", role=" + role + ", getId()=" + getId() + ", getVorname()="
				+ getVorname() + ", getNachname()=" + getNachname()
				+ ", getUsername()=" + getUsername() + ", getEmail()="
				+ getEmail() + ", getRole()=" + getRole() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	
	
	

}
