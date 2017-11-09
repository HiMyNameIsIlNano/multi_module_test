package com.daniele.mybackend.service.impl;

import javax.inject.Inject;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.daniele.mybackend.service.LoginService;
import com.daniele.mybackend.service.UserProfileService;
import com.daniele.mydatabase.userProfile.model.UserProfileDetails;

@Service
public class LoginServiceImpl implements LoginService {

	@Inject
	private UserProfileService userProfileService;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserProfileDetails userProfile = userProfileService.getUserByEmail(email);

		if (userProfile == null) {
			throw new UsernameNotFoundException(String.format("No profile found with email '%s'.", email));
		}
		
		return userProfile;
	}

}
