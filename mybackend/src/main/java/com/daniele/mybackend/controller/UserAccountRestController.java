package com.daniele.mybackend.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.daniele.mybackend.dto.UserProfileDto;
import com.daniele.mybackend.service.UserProfileService;
import com.daniele.mydatabase.model.userAccount.UserProfileDetails;

@RestController
@RequestMapping("user-rest")
public class UserAccountRestController  {

	@Inject
	private UserProfileService userProfileService;
	
	@GetMapping(path = "/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public UserProfileDto getUser(@PathVariable("id") Long id) {
		UserProfileDetails userProfile = userProfileService.getUserById(id);
		return UserProfileDto.ofUserProfile(userProfile);
	}
	
	@GetMapping(path = "/list")
	@ResponseStatus(code = HttpStatus.OK)
	public List<UserProfileDto> getUsers() {
		List<UserProfileDetails> userProfiles = userProfileService.getAllUsersSortedBySurnameAsc();
		return userProfiles.stream()
				.map(UserProfileDto::ofUserProfile)
				.collect(Collectors.toList());
	}
	
	@PostMapping(path = "/save")
	@ResponseStatus(code = HttpStatus.OK)
	public UserProfileDto saveUser(@RequestBody UserProfileDetails userProfile) {
		UserProfileDetails user = userProfileService.getUserByEmail(userProfile.getEmail());
		if (user == null) {
			userProfileService.save(userProfile);	
		} else {
			// TODO: return already existing user message 
		}
		return UserProfileDto.ofUserProfile(userProfile);
	}
	
}