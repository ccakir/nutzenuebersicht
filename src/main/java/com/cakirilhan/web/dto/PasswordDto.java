package com.cakirilhan.web.dto;



public class PasswordDto {
	
	private String password;
	private String newPassword;
	private String matchingNewPassword;
	
	
	
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
	
	

}
