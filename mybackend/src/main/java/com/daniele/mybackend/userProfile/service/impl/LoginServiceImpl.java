package com.daniele.mybackend.userProfile.service.impl;

import javax.inject.Inject;

import com.daniele.mybackend.userProfile.repository.UserProfileRepository;
import org.jooq.generated.tables.records.UserProfileRecord;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.daniele.mybackend.userProfile.service.LoginService;
import com.daniele.mybackend.userProfile.service.UserProfileService;
import com.daniele.mydatabase.userProfile.model.UserProfileDetails;

import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService {

	@Inject
	private UserProfileService userProfileService;
	
	@Override
	public UserDetails loadUserByUsername(String user) throws UsernameNotFoundException {
		Optional<UserProfileDetails> userProfile = userProfileService.getUserByName(user);

		if (!userProfile.isPresent()) {
			throw new UsernameNotFoundException(String.format("No profile found with user '%s'.", user));
		}
		
		return userProfile.get();
	}

}
