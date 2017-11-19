package com.daniele.mybackend.userProfile.service;

import java.util.List;

import com.daniele.mybackend.shared.service.BaseEntityService;
import com.daniele.mydatabase.userProfile.model.Comment;
import com.daniele.mydatabase.userProfile.model.UserProfileDetails;

public interface UserProfileService extends BaseEntityService<UserProfileDetails> {
	List<UserProfileDetails> getAllUsers();
	List<UserProfileDetails> getAllUsersSortedBySurnameAsc();
	UserProfileDetails getUserById(Long id);
	List<UserProfileDetails> getUsersByName(String name);
	UserProfileDetails getUserByEmail(String email);
	List<Comment> getCommentsByUser(String email);
}