package com.cakirilhan.web.dto;

import javax.validation.constraints.NotNull;

public class OrtDto {
	
	private Long ortId;
	

	@NotNull
	private String ortname;

	private String adresse;
	
	private String plz;
	
	private String land;

	
	

	public Long getOrtId() {
		return ortId;
	}



	public void setOrtId(Long ortId) {
		this.ortId = ortId;
	}



	public OrtDto() {
		
	}



	public OrtDto(@NotNull String ortname, String adresse, String plz,
			String land) {
		this.ortname = ortname;
		this.adresse = adresse;
		this.plz = plz;
		this.land = land;
	}

	

	public String getOrtname() {
		return ortname;
	}

	


	public void setOrtname(String ortname) {
		this.ortname = ortname;
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

	public String getLand() {
		return land;
	}

	public void setLand(String land) {
		this.land = land;
	}
	
	
}
