package com.daniele.mybackend.service;

import java.util.List;

import com.daniele.mydatabase.model.userAccount.UserProfileDetails;

public interface UserProfileService extends BaseEntityService<UserProfileDetails> {
	public List<UserProfileDetails> getAllUsers();
	public List<UserProfileDetails> getAllUsersSortedBySurnameAsc();
	public UserProfileDetails getUserById(Long id);
	public List<UserProfileDetails> getUsersByName(String name);
	public UserProfileDetails getUserByEmail(String email);
}
