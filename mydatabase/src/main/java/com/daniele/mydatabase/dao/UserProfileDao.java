package com.daniele.mydatabase.dao;

import java.util.List;

import com.daniele.mydatabase.model.userAccount.UserProfileDetails;

public interface UserProfileDao extends BaseEntityDao<UserProfileDetails> {
	public List<UserProfileDetails> findByName(String name);
	public List<UserProfileDetails> findAllAndSortBySurnameAsc();
	public UserProfileDetails findByEmail(String email);
}