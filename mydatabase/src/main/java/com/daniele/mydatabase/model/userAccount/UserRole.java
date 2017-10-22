package com.daniele.mydatabase.model.userAccount;

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