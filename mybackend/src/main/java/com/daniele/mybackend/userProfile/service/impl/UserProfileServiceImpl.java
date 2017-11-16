package com.daniele.mybackend.userProfile.service.impl;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import com.daniele.mybackend.shared.service.impl.BaseEntityServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daniele.mybackend.userProfile.service.UserProfileService;
import com.daniele.mydatabase.userProfile.dao.UserProfileDao;
import com.daniele.mydatabase.userProfile.model.Comment;
import com.daniele.mydatabase.userProfile.model.UserProfileDetails;

@Service
public class UserProfileServiceImpl extends BaseEntityServiceImpl<UserProfileDetails> implements UserProfileService {
	
	@Inject
	private UserProfileDao userProfileDao;
	
	@Override
	@Transactional
	public List<UserProfileDetails> getAllUsers() {
		return userProfileDao.findAll();
	}
	
	@Override
	@Transactional
	public List<UserProfileDetails> getAllUsersSortedBySurnameAsc() {
		return userProfileDao.findAllAndSortBySurnameAsc();
	}
	
	@Override
	@Transactional
	public UserProfileDetails getUserById(Long id) {
		return userProfileDao.findById(id);
	}
	
	@Override
	@Transactional
	public List<UserProfileDetails> getUsersByName(String name) {
		return userProfileDao.findByName(name);
	}
	
	@Override
	@Transactional 
	public UserProfileDetails getUserByEmail(String email) {
		return userProfileDao.findByEmail(email);
	}
	
	@Override
	@Transactional
	public List<Comment> getCommentsByUser(String email) {
		UserProfileDetails userProfileDetails = getUserByEmail(email);
		if (userProfileDetails != null) {
			return userProfileDao.findCommentsByUser(userProfileDetails);	
		} else {
			return Collections.emptyList();
		}
	}
}