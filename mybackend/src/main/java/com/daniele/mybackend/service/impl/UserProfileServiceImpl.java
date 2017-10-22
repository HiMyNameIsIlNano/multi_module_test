package com.daniele.mybackend.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.daniele.mybackend.service.UserProfileService;
import com.daniele.mydatabase.dao.UserProfileDao;
import com.daniele.mydatabase.model.userAccount.UserProfileDetails;

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
}