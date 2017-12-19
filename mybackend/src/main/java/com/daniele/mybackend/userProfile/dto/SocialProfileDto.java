package com.daniele.mybackend.userProfile.dto;

public class SocialProfileDto {

    private String profileName;
    private String profileUrl;

    public SocialProfileDto(String profileName, String profileUrl) {
        this.profileName = profileName;
        this.profileUrl = profileUrl;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }
}

