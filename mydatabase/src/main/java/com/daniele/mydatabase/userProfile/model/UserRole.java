package com.daniele.mydatabase.userProfile.model;

public enum UserRole {
	
	USER("USER", 1),
	ADMIN("ROOT", 2);
	
	private String authority;
	private Integer ordinal;

    public String getAuthority(){
        return this.authority;
    }

    public Integer getOrdinal() {
        return ordinal;
    }

    public static Integer ordinalOf(UserRole role) {
        return role.getOrdinal();
    }

    private UserRole(String authority, Integer ordinal){
        this.authority = authority;
        this.ordinal = ordinal;
    }
}