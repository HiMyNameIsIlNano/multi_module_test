package com.daniele.mybackend.dto;

public class AuthDto {
	
	private String email;
	private String password;
	private String token;
	
	public AuthDto() {
	}
	
	public AuthDto(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getToken() {
		return token;
	}

	@Override
	public String toString() {
		return "AuthDto [email=" + email + ", token=" + token + "]";
	}
	
}
