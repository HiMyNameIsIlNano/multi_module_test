package com.daniele.mybackend.userProfile.dao;

import com.daniele.mybackend.userProfile.model.UserProfileDetailsFilter;
import com.daniele.mydatabase.userProfile.model.UserProfileDetails;
import org.jooq.Record;

import java.util.List;

public interface UserProfileDao {
	List<Record> findByFilter(UserProfileDetailsFilter filter);
	List<Record> findFriendsByUser(String user);
	List<UserProfileDetails> findWithException();
}