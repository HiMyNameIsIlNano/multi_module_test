package com.daniele.mybackend.userProfile.service.impl;

import com.daniele.mybackend.userProfile.dao.UserProfileDao;
import com.daniele.mybackend.userProfile.model.UserProfileDetailsFilter;
import com.daniele.mybackend.userProfile.repository.UserProfileRepository;
import com.daniele.mybackend.userProfile.service.UserProfileService;
import com.daniele.mydatabase.userProfile.model.UserProfileDetails;
import org.jooq.Record;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserProfileServiceImpl implements UserProfileService {
	
	@Inject
	private UserProfileDao userProfileDao;
	
	@Inject
	private UserProfileRepository userProfileRepository;

	@Override
	@Transactional
	public List<Record> getByFilter(UserProfileDetailsFilter filter) {
		return userProfileDao.findByFilter(filter);
	}

	@Override
	@Transactional
	public List<UserProfileDetails> getAllValidUsers() {
		Iterable<UserProfileDetails> all = userProfileRepository.findAll();
		List<UserProfileDetails> list = new ArrayList<>();
		all.forEach(list::add);
		return list;
	}
	
	@Override
	@Transactional
	public Optional<UserProfileDetails> getUserById(Long id) {
		UserProfileDetails profileDetails = userProfileRepository.findOne(id);
		return profileDetails != null ? Optional.of(profileDetails) : Optional.empty();
	}

	@Override
	@Transactional
	public Optional<UserProfileDetails> getUserByEmail(String email) {
		UserProfileDetails result = userProfileRepository.findByEmail(email);
		return result == null ? Optional.empty() : Optional.of(result);
	}

	@Override
    @Transactional
    public UserProfileDetails store(UserProfileDetails userProfileDetails) {
        return userProfileRepository.save(userProfileDetails);
    }
}