package com.daniele.mybackend.userProfile.dto;

public class AuthDto {
	
	private String user;
	private String password;
	private String token;
	
	public AuthDto() {
	}
	
	public AuthDto(String user, String password) {
		super();
		this.user = user;
		this.password = password;
	}

	public String getUser() {
		return user;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getToken() {
		return token;
	}

	@Override
	public String toString() {
		return "AuthDto [user=" + user + ", token=" + token + "]";
	}
	
}
