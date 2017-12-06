package com.daniele.mybackend.user.service;

import java.util.List;

import org.jooq.Record2;

import com.daniele.mybackend.shared.service.BaseEntityService;
import com.daniele.mydatabase.userProfile.model.UserProfileDetails;

public interface UserProfileService extends BaseEntityService<UserProfileDetails> {
	List<UserProfileDetails> getAllUsers();
	List<UserProfileDetails> getAllUsersSortedBySurnameAsc();
	UserProfileDetails getUserById(Long id);
	UserProfileDetails getUserByName(String name);
	UserProfileDetails getUserByEmail(String email);
	List<Record2<String, String>> getCommentsByUser(String email);
	List<UserProfileDetails> findWithException();
}
