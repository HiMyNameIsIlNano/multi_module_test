package com.daniele.mybackend.userProfile.service.impl;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.jooq.Record2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daniele.mybackend.shared.service.impl.BaseEntityServiceImpl;
import com.daniele.mybackend.userProfile.service.UserProfileService;
import com.daniele.mybackend.userProfile.dao.CommentDao;
import com.daniele.mybackend.userProfile.dao.UserProfileDao;
import com.daniele.mydatabase.userProfile.model.UserProfileDetails;
import com.daniele.mybackend.userProfile.repository.UserProfileRepository;

@Service
public class UserProfileServiceImpl extends BaseEntityServiceImpl<UserProfileDetails> implements UserProfileService {
	
	@Inject
	private UserProfileDao userProfileDao;
	
	@Inject
	private UserProfileRepository userProfileRepository;

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
	public Optional<UserProfileDetails> getUserById(Long id) {
		UserProfileDetails profileDetails = userProfileRepository.findOne(id);
		return profileDetails != null ? Optional.of(profileDetails) : Optional.empty();
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