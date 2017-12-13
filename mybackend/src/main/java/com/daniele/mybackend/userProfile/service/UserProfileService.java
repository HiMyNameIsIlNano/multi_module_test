package com.daniele.mybackend.userProfile.service;

import java.util.List;
import java.util.Optional;

import com.daniele.mydatabase.userProfile.model.Comment;

import com.daniele.mybackend.shared.service.BaseEntityService;
import com.daniele.mydatabase.userProfile.model.UserProfileDetails;

public interface UserProfileService extends BaseEntityService<UserProfileDetails> {
	List<UserProfileDetails> getAllUsers();
	List<UserProfileDetails> getAllUsersSortedBySurnameAsc();
	Optional<UserProfileDetails> getUserById(Long id);
	UserProfileDetails getUserByName(String name);
	UserProfileDetails getUserByEmail(String email);
	List<Comment> getCommentsByUser(String email);
	List<UserProfileDetails> findWithException();
}
