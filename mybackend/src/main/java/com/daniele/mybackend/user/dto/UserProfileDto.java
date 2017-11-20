package com.daniele.mybackend.user.dto;

import java.time.LocalDate;

import com.daniele.mydatabase.userProfile.model.UserProfileDetails;
import com.daniele.mydatabase.userProfile.model.UserProfileDetails.UserProfileBuilder;

public class UserProfileDto {

	Long id;
	String imgPath;
	String name;
	String surname;
	String email;
	String nickname;
	String password;
	LocalDate lastUpdate;
	String updatedBy;
	LocalDate validFrom;
	LocalDate validTo;
	
	public UserProfileDto() {
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public LocalDate getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(LocalDate lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public LocalDate getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(LocalDate validFrom) {
		this.validFrom = validFrom;
	}

	public LocalDate getValidTo() {
		return validTo;
	}

	public void setValidTo(LocalDate validTo) {
		this.validTo = validTo;
	}

	public static UserProfileDto ofUserProfile(UserProfileDetails userProfile) {
		UserProfileDto userProfileDto = new UserProfileDto();
		userProfileDto.setId(userProfile.getId());
		userProfileDto.setImgPath(userProfile.getImgPath());
		userProfileDto.setName(userProfile.getName());
		userProfileDto.setSurname(userProfile.getSurname());
		userProfileDto.setNickname(userProfile.getNickname());
		userProfileDto.setEmail(userProfile.getEmail());
		userProfileDto.setLastUpdate(userProfile.getLastUpdate());
		userProfileDto.setUpdatedBy(userProfile.getUpdatedBy());
		userProfileDto.setValidFrom(userProfile.getValidFrom());
		userProfileDto.setValidFrom(userProfile.getValidTo());
		
		return userProfileDto;
	}
	
	public static UserProfileDetails fromDto(UserProfileDto userProfileDto) {
		UserProfileDetails userProfile = UserProfileBuilder.forCreation()
				.withName(userProfileDto.getName())
				.withImgPath(userProfileDto.getImgPath())
				.withSurname(userProfileDto.getSurname())
				.withNickname(userProfileDto.getNickname())
				.withEmail(userProfileDto.getEmail())
				.withPassword(userProfileDto.getPassword())
				.build();
		return userProfile;
	}
	
	public static UserProfileDetails mergeEntityToDto(UserProfileDetails oldUserProfile, UserProfileDto userProfileDto) {
		UserProfileDetails updatedUserProfile = UserProfileBuilder.forUpdate(oldUserProfile)
				.withName(userProfileDto.getName())
				.withSurname(userProfileDto.getSurname())
				.withNickname(userProfileDto.getNickname())
				.withEmail(userProfileDto.getEmail())
				.withPassword(userProfileDto.getPassword())
				.build();
		return updatedUserProfile;
	}
}