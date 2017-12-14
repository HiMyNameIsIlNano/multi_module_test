package com.daniele.mybackend.userProfile.service;

import com.daniele.mybackend.shared.service.BaseEntityService;
import com.daniele.mydatabase.userProfile.model.UserProfileDetails;

import java.util.List;
import java.util.Optional;

public interface UserProfileService extends BaseEntityService<UserProfileDetails> {
	List<UserProfileDetails> getAllUsers();
	List<UserProfileDetails> getAllUsersSortedBySurnameAsc();
	Optional<UserProfileDetails> getUserById(Long id);
	UserProfileDetails getUserByName(String name);
	UserProfileDetails getUserByEmail(String email);
	List<UserProfileDetails> findWithException();
}
