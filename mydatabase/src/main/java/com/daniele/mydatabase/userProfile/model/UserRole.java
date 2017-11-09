package com.daniele.mydatabase.userProfile.model;

public enum UserRole {
	
	USER("USER"), 
	ADMIN("ROOT");
	
	private String authority;
	
    public String getAuthority(){
        return this.authority;
    }
    
    private UserRole(String authority){
        this.authority = authority;
    }
}