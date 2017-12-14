package com.daniele.mybackend.userProfile.service.impl;

import com.daniele.mybackend.shared.service.impl.BaseEntityServiceImpl;
import com.daniele.mybackend.userProfile.dao.CommentDao;
import com.daniele.mybackend.userProfile.dao.UserProfileDao;
import com.daniele.mybackend.userProfile.repository.CommentRepository;
import com.daniele.mybackend.userProfile.repository.UserProfileRepository;
import com.daniele.mybackend.userProfile.service.UserProfileService;
import com.daniele.mydatabase.userProfile.model.UserProfileDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@Service
public class UserProfileServiceImpl extends BaseEntityServiceImpl<UserProfileDetails> implements UserProfileService {
	
	@Inject
	private UserProfileDao userProfileDao;
	
	@Inject
	private UserProfileRepository userProfileRepository;

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
		return userProfileRepository.findByName(name);
	}
	
	@Override
	@Transactional 
	public UserProfileDetails getUserByEmail(String email) {
		return userProfileRepository.findByEmail(email);
	}

	@Override
	@Transactional
	public List<UserProfileDetails> findWithException() {
		return userProfileDao.findWithException();
	}
}