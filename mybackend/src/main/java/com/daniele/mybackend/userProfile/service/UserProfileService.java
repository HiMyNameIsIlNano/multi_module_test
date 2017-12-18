package com.daniele.mybackend.userProfile.service;

import com.daniele.mybackend.userProfile.model.UserProfileDetailsFilter;
import com.daniele.mydatabase.userProfile.model.UserProfileDetails;
import org.jooq.generated.tables.records.UserProfileRecord;

import java.util.List;
import java.util.Optional;

public interface UserProfileService {
	List<UserProfileRecord> getByFilter(UserProfileDetailsFilter filter);
	Optional<UserProfileDetails> getUserById(Long id);
	Optional<UserProfileDetails> getUserByEmail(String email);
	UserProfileDetails store(UserProfileDetails userProfileDetails);
}
