package com.daniele.mybackend.populator.service.impl;

import com.daniele.mybackend.populator.service.UserProfileEntityWriterService;
import com.daniele.mybackend.populator.model.UserProfileWriterData;
import com.daniele.mybackend.userProfile.repository.UserProfileRepository;
import com.daniele.mybackend.userProfile.service.CommentService;
import com.daniele.mybackend.userProfile.service.UserProfileService;
import com.daniele.mydatabase.userProfile.model.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.*;

@Service
public class UserProfileEntityWriterServiceImpl implements UserProfileEntityWriterService {

    @Inject
    private UserProfileService userProfileService;

    @Inject
    private CommentService commentService;

    @Inject
    private PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public void populate(UserProfileWriterData data) {

        Comment commentShort, commentMedium, commentLong;
        int userNumber = data.getUserNumber();

        for (int i = 0; i < userNumber; i++) {
            Address address = Address.AddressBuilder.forCreation()
                    .withCity("City_" + i)
                    .withStreetName("Street_" + i)
                    .withStreetNumber(i)
                    .build();

            SocialProfileDetails profileDetails = SocialProfileDetails.SocialProfileDetailsBuilder.forCreation()
                    .withProfileName("MySocialAccount" + i)
                    .withProfileUrl("www.mysocialaccount" + i + ".nogo")
                    .build();

            UserProfileDetails userProfile = UserProfileDetails.UserProfileBuilder.forCreation()
                    .withImgPath("http://vignette4.wikia.nocookie.net/scribblenauts/images/4/42/Crash_Test_Dummy.png/revision/latest?cb=20130309213400")
                    .withName("User_" + i)
                    .withSurname("Surname_" + i)
                    .withNickname("Nickname_" + i)
                    .withEmail("user_" + i + "@email.com")
                    .withPassword(passwordEncoder.encode("pwd_" + i))
                    //.withPassword("$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC") // password
                    .withAddress(address)
                    .withUserRole(UserRole.USER)
                    .withSocialProfile(profileDetails)
                    .build();

            System.out.println("Saving profile for " + userProfile);
            userProfileService.store(userProfile);
        }

        String textShort = "Mussum ipsum cacilds, vidis litro abertis. Consetis adipiscings elitis. "
                + "Pra lá, depois divoltis porris, paradis. Paisis, filhis, espiritis santis. ";

        String textMedium = "Mussum ipsum cacilds, vidis litro abertis. Consetis adipiscings elitis. "
                + "Pra lá, depois divoltis porris, paradis. Paisis, filhis, espiritis santis. "
                + "Mé faiz elementum girarzis, nisi eros vermeio, in elementis mé pra quem é amistosis quis leo. "
                + "Manduma pindureta quium dia nois paga. ";

        String textLong = "Mussum ipsum cacilds, vidis litro abertis. Consetis adipiscings elitis. "
                + "Pra lá, depois divoltis porris, paradis. Paisis, filhis, espiritis santis. "
                + "Mé faiz elementum girarzis, nisi eros vermeio, in elementis mé pra quem é amistosis quis leo. "
                + "Manduma pindureta quium dia nois paga. "
                + "Sapien in monti palavris qui num significa nadis i pareci latim. "
                + "Interessantiss quisso pudia ce receita de bolis, mais bolis eu num gostis.";

        List<UserProfileDetails> allValidUsers = userProfileService.getAllValidUsers();
        int size = allValidUsers.size();

        for (int i = 0; i < size; i++) {
            UserProfileDetails userProfile = allValidUsers.get(i);

            commentShort = Comment.CommentBuilder.forCreation()
                    .withText(textShort)
                    .withTopic("Geography")
                    .withUser(userProfile)
                    .build();
            System.out.println("Saving comment for " + userProfile);
            commentService.store(commentShort);

            commentMedium = Comment.CommentBuilder.forCreation()
                    .withText(textMedium)
                    .withTopic("Science")
                    .withUser(userProfile)
                    .build();
            System.out.println("Saving comment for " + userProfile);
            commentService.store(commentMedium);

            commentLong = Comment.CommentBuilder.forCreation()
                    .withText(textLong)
                    .withTopic("History")
                    .withUser(userProfile)
                    .build();
            System.out.println("Saving comment for " + userProfile);
            commentService.store(commentLong);
        }

        for (int i = 0; i < size; i++) {
            UserProfileDetails userProfile = allValidUsers.get(i);
            HashSet<UserProfileDetails> friendsSet = new HashSet<>(allValidUsers.subList(i + 1, size));

            UserProfileDetails profileWithFriends = UserProfileDetails.UserProfileBuilder.forUpdate(userProfile)
                    .withFriends(friendsSet)
                    .build();

            userProfileService.store(profileWithFriends);
        }
    }

}
