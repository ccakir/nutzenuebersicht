package com.cakirilhan.domain.user;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.cakirilhan.domain.kunde.Ort;
import com.cakirilhan.user.validator.ValidEmail;
import com.cakirilhan.user.validator.ValidPassword;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "user")
@NamedQueries({
	@NamedQuery(name="User.findByUsernameReturnOptinalUser",query="SELECT u FROM User u WHERE u.username=:username")
	
})
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String vorname;
	private String nachname;
	@Column(unique = true, nullable = false)
	private String username;
	private String email;
	@Column(length = 60)
	private String password;
	@Transient
	private String newPassword;
	@Transient
	private String matchingNewPassword;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ort_id")
	
	private Ort ort;

	@Override
	public String toString() {
		return "User [id=" + id + ", vorname=" + vorname + ", nachname="
				+ nachname + ", username=" + username + ", email=" + email
				+ ", password=" + password + ", newPassword=" + newPassword
				+ ", matchingNewPassword=" + matchingNewPassword + ", ort="
				+ ort + ", role=" + role + "]";
	}

	@ManyToMany(fetch = FetchType.EAGER)
	@JsonIgnore
	private Set<Role> role;

	public User() {

	}
	
	

	public Ort getOrt() {
		return ort;
	}

	public void setOrt(Ort ort) {
		this.ort = ort;
	}

	public User(Long id, String vorname, String nachname, String username,
			String email, String password, String newPassword,
			String matchingNewPassword, Ort ort, Set<Role> role) {
		super();
		this.id = id;
		this.vorname = vorname;
		this.nachname = nachname;
		this.username = username;
		this.email = email;
		this.password = password;
		this.newPassword = newPassword;
		this.matchingNewPassword = matchingNewPassword;
		this.ort = ort;
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getMatchingNewPassword() {
		return matchingNewPassword;
	}

	public void setMatchingNewPassword(String matchingNewPassword) {
		this.matchingNewPassword = matchingNewPassword;
	}

	public Set<Role> getRole() {
		return role;
	}

	public void setRole(Set<Role> role) {
		this.role = role;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
