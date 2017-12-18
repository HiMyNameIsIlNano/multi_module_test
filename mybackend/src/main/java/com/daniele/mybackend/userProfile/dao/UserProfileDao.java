package com.daniele.mybackend.userProfile.dao;

import com.daniele.mybackend.userProfile.model.UserProfileDetailsFilter;
import com.daniele.mydatabase.userProfile.model.UserProfileDetails;
import org.jooq.generated.tables.records.UserProfileRecord;

import java.util.List;

public interface UserProfileDao {
	List<UserProfileRecord> findByFilter(UserProfileDetailsFilter filter);
	List<UserProfileDetails> findWithException();
}