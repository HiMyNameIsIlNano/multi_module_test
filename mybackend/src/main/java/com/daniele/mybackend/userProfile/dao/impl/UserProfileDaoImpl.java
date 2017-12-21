package com.daniele.mybackend.userProfile.dao.impl;

import com.daniele.mybackend.shared.JooqUtils;
import com.daniele.mybackend.userProfile.dao.UserProfileDao;
import com.daniele.mybackend.userProfile.model.UserProfileDetailsFilter;
import com.daniele.mydatabase.shared.model.SortType;
import com.daniele.mydatabase.userProfile.model.UserProfileDetails;
import com.daniele.mydatabase.userProfile.model.UserRole;
import com.daniele.mylogger.LogExecutionTime;
import com.daniele.mylogger.LogMethodException;
import org.jooq.*;
import org.jooq.generated.tables.SocialProfile;
import org.jooq.generated.tables.UserProfile;
import org.jooq.generated.tables.UserToFriend;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserProfileDaoImpl implements UserProfileDao {

	@Inject
	private DSLContext create;

	@Override
	@LogExecutionTime
	public List<Record> findByFilter(UserProfileDetailsFilter filter) {
        UserProfile p = UserProfile.USER_PROFILE.as("p");
        SocialProfile s = SocialProfile.SOCIAL_PROFILE.as("s");

        SelectOnConditionStep<Record> select = create
                .select()
                .from(p)
                .join(s).on(p.SOCIAL_ID_REF.eq(s.ID));

        List<Condition> conditions = new ArrayList<>();

        if (filter.getName() != null) {
            Condition nameCondition = p.NAME.eq(filter.getName());
            conditions.add(nameCondition);
        }

        if (filter.getSurname() != null) {
            Condition surnameCondition = p.SURNAME.eq(filter.getSurname());
            conditions.add(surnameCondition);
        }

        if (filter.getEmail() != null) {
            Condition emailCondition = p.EMAIL.eq(filter.getEmail());
            conditions.add(emailCondition);
        }

        if (filter.getNickname() != null) {
            Condition nicknameCondition = p.NICKNAME.eq(filter.getNickname());
            conditions.add(nicknameCondition);
        }

        if (filter.getUserRole() != null) {
            Condition roleCondition = p.ROLE.eq(UserRole.ordinalOf(filter.getUserRole()));
            conditions.add(roleCondition);
        }

        if (filter.getValidFrom() != null) {
            Date dateFrom = Date.valueOf(filter.getValidFrom());
            Condition validFromCondition = p.VALID_FROM.greaterOrEqual(dateFrom);
            conditions.add(validFromCondition);
        }

        if (filter.getValidTo() != null) {
            Date dateTo = Date.valueOf(filter.getValidTo());
            Condition validToCondition = p.VALID_TO.lessOrEqual(dateTo);
            conditions.add(validToCondition);
        }

        if (filter.isActive()){
            Condition validToCondition = p.IS_ACTIVE.eq(filter.isActive());
            conditions.add(validToCondition);
        }

        JooqUtils.buildConditions(select, conditions);

        if (SortType.isAscending(filter.getSort())) {
            select.orderBy(p.EMAIL.asc());
        }

        if (SortType.isDescending(filter.getSort())) {
            select.orderBy(p.EMAIL.desc());
        }

        return select.fetch();
	}

    @Override
    public List<Record> findFriendsByUser(String user) {
        UserProfile p1 = UserProfile.USER_PROFILE.as("p1");
        UserProfile p2 = UserProfile.USER_PROFILE.as("p2");
        UserToFriend u2f = UserToFriend.USER_TO_FRIEND.as("u2f");

        SelectConditionStep<Record> select = create
                .select()
                .from(p1)
                .join(u2f).on(u2f.USER_ID.eq(p1.ID))
                .join(p2).on(u2f.FRIEND_ID.eq(p2.ID))
                .where(p1.NAME.eq(user));

        return select.fetch();
    }

	@Override
	@LogMethodException
	public List<UserProfileDetails> findWithException() {
		throw new RuntimeException("Just a test");
	}

}
