package com.daniele.mybackend.user.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.jooq.Record2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daniele.mybackend.shared.service.impl.BaseEntityServiceImpl;
import com.daniele.mybackend.user.service.UserProfileService;
import com.daniele.mydatabase.userProfile.dao.CommentDao;
import com.daniele.mydatabase.userProfile.dao.UserProfileDao;
import com.daniele.mydatabase.userProfile.model.UserProfileDetails;

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
	public UserProfileDetails getUserByName(String name) {
		return userProfileDao.findByName(name);
	}
	
	@Override
	@Transactional 
	public UserProfileDetails getUserByEmail(String email) {
		return userProfileDao.findByEmail(email);
	}
	
	@Override
	@Transactional
	public List<Record2<String,String>> getCommentsByUser(String name) {
		return commentDao.findCommentsWithJooq(name);
	}

	@Override
	@Transactional
	public List<UserProfileDetails> findWithException() {
		return userProfileDao.findWithException();
	}
}