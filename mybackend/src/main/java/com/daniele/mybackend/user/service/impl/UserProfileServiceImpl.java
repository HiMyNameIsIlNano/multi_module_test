package com.daniele.mybackend.user.service.impl;

import com.daniele.mybackend.shared.service.impl.BaseEntityServiceImpl;
import com.daniele.mybackend.user.service.UserProfileService;
import com.daniele.mydatabase.userProfile.dao.CommentDao;
import com.daniele.mydatabase.userProfile.dao.UserProfileDao;
import com.daniele.mydatabase.userProfile.model.Comment;
import com.daniele.mydatabase.userProfile.model.UserProfileDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

@Service
public class UserProfileServiceImpl extends BaseEntityServiceImpl<UserProfileDetails> implements UserProfileService {
	
	@Inject
	private UserProfileDao userProfileDao;

	@Inject
	private CommentDao commentDao;
	
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
		return commentDao.findCommentsByUser(email);
	}
}