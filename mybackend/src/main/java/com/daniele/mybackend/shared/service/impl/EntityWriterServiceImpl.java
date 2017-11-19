package com.daniele.mybackend.shared.service.impl;

import com.daniele.mybackend.userProfile.service.CommentService;
import com.daniele.mybackend.userProfile.service.UserProfileService;
import com.daniele.mydatabase.userProfile.model.Comment;
import com.daniele.mydatabase.userProfile.model.UserProfileDetails;
import com.daniele.mydatabase.userProfile.model.UserRole;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Arrays;

@Service
public class EntityWriterServiceImpl {

    @Inject
    private UserProfileService userProfileService;

    @Inject
    private CommentService commentService;

    @Inject
    private PasswordEncoder passwordEncoder;

    @Transactional
    public void loadUsers(int userNumber) {
        Comment commentShort, commentMedium, commentLong;

        for (int i = 0; i < userNumber; i++) {
            UserProfileDetails userProfile = UserProfileDetails.UserProfileBuilder.forCreation()
                    .withName("User_" + i)
                    .withSurname("Surname_" + i)
                    .withNickname("Nickname_" + i)
                    .withEmail("user_" + i + "@email.com")
                    .withPassword(passwordEncoder.encode("pwd_" + i))
                    //.withPassword("$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC") // password
                    .withUserRole(UserRole.USER)
                    .build();

            System.out.println("Saving profile for " + userProfile);
            userProfileService.save(userProfile);
        }

        for (int i = 0; i < userNumber; i++) {
            UserProfileDetails fromDb = userProfileService.getUserByEmail("user_" + i + "@email.com");

            commentShort = Comment.CommentBuilder.forCreation()
                    .withText("Comment Short! from U_" + i)
                    .withTopic("Geography")
                    .withUser(fromDb)
                    .build();

            System.out.println("Saving comment for " + fromDb);
            commentService.save(commentShort);

            commentMedium = Comment.CommentBuilder.forCreation()
                    .withText("Comment Medium! From Usr_" + i)
                    .withTopic("Science")
                    .withUser(fromDb)
                    .build();
            System.out.println("Saving comment for " + fromDb);
            commentService.save(commentMedium);

            commentLong = Comment.CommentBuilder.forCreation()
                    .withText("Comment Long! From User_" + i)
                    .withTopic("History")
                    .withUser(fromDb)
                    .build();
            System.out.println("Saving comment for " + fromDb);
            commentService.save(commentLong);
        }
    }
}
