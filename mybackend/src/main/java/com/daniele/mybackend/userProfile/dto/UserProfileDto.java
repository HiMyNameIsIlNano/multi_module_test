package com.daniele.mybackend.userProfile.dto;

import java.sql.Date;
import java.time.LocalDate;

import com.daniele.mydatabase.DateUtils;
import com.daniele.mydatabase.userProfile.model.UserProfileDetails;
import com.daniele.mydatabase.userProfile.model.UserProfileDetails.UserProfileBuilder;
import org.jooq.generated.tables.UserComment;
import org.jooq.generated.tables.UserProfile;
import org.jooq.generated.tables.records.UserProfileRecord;

public class UserProfileDto {

	private Long id;
	private String imgPath;
	private String name;
	private String surname;
	private AddressDto address;
	private String email;
	private String nickname;
	private LocalDate lastUpdate;
	private String updatedBy;
	private LocalDate validFrom;
	private LocalDate validTo;
	
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

	public AddressDto getAddress() {
		return address;
	}

	public void setAddress(AddressDto address) {
		this.address = address;
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
		AddressDto addressDto = new AddressDto(
				userProfile.getAddress().getStreetName(),
				userProfile.getAddress().getStreetNumber(),
				userProfile.getAddress().getCity());

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

		userProfileDto.setAddress(addressDto);

		return userProfileDto;
	}

	public static UserProfileDto ofUserProfileRecord(UserProfileRecord userProfile) {
        UserProfileDto userProfileDto = new UserProfileDto();
		AddressDto addressDto = new AddressDto(
				userProfile.get(UserProfile.USER_PROFILE.STREET_NAME),
				userProfile.get(UserProfile.USER_PROFILE.STREET_NUMBER),
				userProfile.get(UserProfile.USER_PROFILE.CITY));

        userProfileDto.setId(userProfile.get(UserProfile.USER_PROFILE.ID));
        userProfileDto.setImgPath(userProfile.get(UserProfile.USER_PROFILE.IMG_PATH));
        userProfileDto.setName(userProfile.get(UserProfile.USER_PROFILE.NAME));
        userProfileDto.setSurname(userProfile.get(UserProfile.USER_PROFILE.SURNAME));
        userProfileDto.setNickname(userProfile.get(UserProfile.USER_PROFILE.NICKNAME));
        userProfileDto.setEmail(userProfile.get(UserProfile.USER_PROFILE.EMAIL));
		userProfileDto.setUpdatedBy(userProfile.get(UserProfile.USER_PROFILE.UPDATED_BY));

		userProfileDto.setAddress(addressDto);

        Date validFrom = userProfile.get(UserProfile.USER_PROFILE.VALID_FROM);
        userProfileDto.setValidFrom(DateUtils.fromLocalDate(validFrom));

        Date validTo = userProfile.get(UserProfile.USER_PROFILE.VALID_TO);
        userProfileDto.setValidTo(DateUtils.fromLocalDate(validTo));

        Date lastUpdate = userProfile.get(UserProfile.USER_PROFILE.LAST_UPDATE);
        userProfileDto.setLastUpdate(lastUpdate != null ?
                DateUtils.fromLocalDate(lastUpdate) :
                null);

        return userProfileDto;
	}
}