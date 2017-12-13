package com.daniele.mybackend.userProfile.repository;

import org.springframework.data.repository.CrudRepository;

import com.daniele.mydatabase.userProfile.model.UserProfileDetails;

public interface UserProfileRepository extends CrudRepository<UserProfileDetails, Long> {
    UserProfileDetails findByName(String name);
    UserProfileDetails findByEmail(String email);
}
