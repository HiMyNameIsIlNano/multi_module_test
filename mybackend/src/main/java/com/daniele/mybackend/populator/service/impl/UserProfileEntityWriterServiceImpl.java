package com.daniele.mybackend.populator.service.impl;

import com.daniele.mybackend.populator.service.UserProfileEntityWriterService;
import com.daniele.mybackend.populator.model.UserProfileWriterData;
import com.daniele.mybackend.user.service.CommentService;
import com.daniele.mybackend.user.service.UserProfileService;
import com.daniele.mydatabase.userProfile.model.Comment;
import com.daniele.mydatabase.userProfile.model.UserProfileDetails;
import com.daniele.mydatabase.userProfile.model.UserRole;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

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
            UserProfileDetails userProfile = UserProfileDetails.UserProfileBuilder.forCreation()
                    .withImgPath("http://vignette4.wikia.nocookie.net/scribblenauts/images/4/42/Crash_Test_Dummy.png/revision/latest?cb=20130309213400")
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
        
        for (int i = 0; i < userNumber; i++) {
            UserProfileDetails fromDb = userProfileService.getUserByEmail("user_" + i + "@email.com");

            commentShort = Comment.CommentBuilder.forCreation()
                    .withText(textShort)
                    .withTopic("Geography")
                    .withUser(fromDb)
                    .build();

            System.out.println("Saving comment for " + fromDb);
            commentService.save(commentShort);

            commentMedium = Comment.CommentBuilder.forCreation()
                    .withText(textMedium)
                    .withTopic("Science")
                    .withUser(fromDb)
                    .build();
            System.out.println("Saving comment for " + fromDb);
            commentService.save(commentMedium);

            commentLong = Comment.CommentBuilder.forCreation()
                    .withText(textLong)
                    .withTopic("History")
                    .withUser(fromDb)
                    .build();
            System.out.println("Saving comment for " + fromDb);
            commentService.save(commentLong);
        }
    }
}
