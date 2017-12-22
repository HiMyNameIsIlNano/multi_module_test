package com.daniele.mybackend.userProfile.service;

import com.daniele.mybackend.userProfile.model.UserProfileDetailsFilter;
import com.daniele.mydatabase.userProfile.model.UserProfileDetails;
import org.jooq.Record;

import java.util.List;
import java.util.Optional;

public interface UserProfileService {
	List<Record> getByFilter(UserProfileDetailsFilter filter);
	List<Record> getFriendsByUser(String name);
	List<UserProfileDetails> getAllValidUsers();
	Optional<UserProfileDetails> getUserById(Long id);
	Optional<UserProfileDetails> getUserByEmail(String email);
	Optional<UserProfileDetails> getUserByName(String name);
	UserProfileDetails store(UserProfileDetails userProfileDetails);
}
