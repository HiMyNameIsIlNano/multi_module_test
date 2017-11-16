package com.daniele.mybackend.shared.service.impl;

import java.util.Arrays;

import javax.inject.Inject;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.daniele.mybackend.userProfile.service.UserProfileService;
import com.daniele.mydatabase.userProfile.model.Comment;
import com.daniele.mydatabase.userProfile.model.UserProfileDetails;
import com.daniele.mydatabase.userProfile.model.UserRole;
import com.daniele.mydatabase.userProfile.model.Comment.CommentBuilder;
import com.daniele.mydatabase.userProfile.model.UserProfileDetails.UserProfileBuilder;

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
    	Comment commentShort, commentMedium, commentLong = null;
    	
    	for (int i = 0; i < 5; i++) {
    		commentShort = CommentBuilder.forCreation()
        			.withText("Comment Short! from U_" + i)
        			.withTopic("Geography")
        			.build();

        	commentMedium = CommentBuilder.forCreation()
        			.withText("Comment Medium! From Usr_" + i)
        			.withTopic("Science")
        			.build();

        	commentLong = CommentBuilder.forCreation()
        			.withText("Comment Long! From User_" + i)
        			.withTopic("History")
        			.build();
    		
    		UserProfileDetails userProfile = UserProfileBuilder.forCreation()
    				.withName("User_" + i)
    				.withSurname("Surname_" + i)
    				.withNickname("Nickname_" + i)
    				.withEmail("user_" + i + "@email.com")
    				.withPassword(passwordEncoder.encode("pwd_" + i)) 
    				//.withPassword("$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC") // password
    				.withUserRole(UserRole.USER)
    				.withComments(Arrays.asList(commentShort, commentMedium, commentLong))
    				.build();
    		
    		System.out.println("Saving " + userProfile);
    		userProfileService.save(userProfile);	
    	}
    }
}
