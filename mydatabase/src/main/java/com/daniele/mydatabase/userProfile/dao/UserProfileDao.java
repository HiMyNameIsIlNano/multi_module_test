package com.daniele.mydatabase.userProfile.dao;

import java.util.List;

import com.daniele.mydatabase.shared.dao.BaseEntityDao;
import com.daniele.mydatabase.userProfile.model.UserProfileDetails;

public interface UserProfileDao extends BaseEntityDao<UserProfileDetails> {
	UserProfileDetails findByName(String name);
	List<UserProfileDetails> findAllAndSortBySurnameAsc();
	UserProfileDetails findByEmail(String email);
	List<UserProfileDetails> findByNameLike(String name);
}