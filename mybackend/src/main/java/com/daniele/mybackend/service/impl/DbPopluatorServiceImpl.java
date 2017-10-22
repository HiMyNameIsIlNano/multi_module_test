package com.daniele.mybackend.service.impl;

import javax.inject.Inject;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.daniele.mybackend.service.UserProfileService;
import com.daniele.mydatabase.model.userAccount.UserProfileDetails;
import com.daniele.mydatabase.model.userAccount.UserProfileDetails.UserProfileBuilder;
import com.daniele.mydatabase.model.userAccount.UserRole;

@Service
public class DbPopluatorServiceImpl {

	private UserProfileService userProfileService;
	
	private PasswordEncoder passwordEncoder;

    @Inject
    public DbPopluatorServiceImpl(UserProfileService userProfileService, PasswordEncoder passwordEncoder) {
        this.userProfileService = userProfileService;
        this.passwordEncoder = passwordEncoder;
    	loadUsers();
    }

    private void loadUsers() {
    	for (int i = 0; i < 5; i++) {
    		UserProfileDetails userProfile = UserProfileBuilder.forCreation()
    				.withName("User_" + i)
    				.withSurname("Surname_" + i)
    				.withNickname("Nickname_" + i)
    				.withEmail("user_" + i + "@email.com")
    				.withPassword(passwordEncoder.encode("pwd_" + i)) 
    				//.withPassword("$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC") // password
    				.withUserRole(UserRole.USER)
    				.build();
    		
    		System.out.println("Saving " + userProfile);
    		userProfileService.save(userProfile);	
    	}
    }
}
