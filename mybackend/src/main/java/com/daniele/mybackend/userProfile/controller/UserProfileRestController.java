package com.daniele.mybackend.userProfile.controller;

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

import com.daniele.mybackend.userProfile.dto.CommentDto;
import com.daniele.mybackend.userProfile.dto.UserProfileDto;
import com.daniele.mybackend.userProfile.service.UserProfileService;
import com.daniele.mydatabase.userProfile.model.UserProfileDetails;

@RestController
@RequestMapping("user-rest")
public class UserProfileRestController {

	@Inject
	private UserProfileService userProfileService;
	
	@GetMapping(path = "/user/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public UserProfileDto getUser(@PathVariable("id") Long id) {
		UserProfileDetails userProfile = userProfileService
				.getUserById(id)
				.orElseThrow(IllegalArgumentException::new);
		
		return UserProfileDto.ofUserProfile(userProfile);  	 	
	}
	
	@GetMapping(path = "/users")
	@ResponseStatus(code = HttpStatus.OK)
	public List<UserProfileDto> getUsers() {
		List<UserProfileDetails> userProfiles = userProfileService.getAllUsersSortedBySurnameAsc();
		return userProfiles.stream()
				.map(UserProfileDto::ofUserProfile)
				.collect(Collectors.toList());
	}

	// https://blog.georgovassilis.com/2015/10/29/spring-mvc-rest-controller-says-406-when-emails-are-part-url-path/
	// http://www.baeldung.com/spring-mvc-content-negotiation-json-xml
    @GetMapping(path = "/comments/{name}", produces = "application/json")
	@ResponseStatus(code = HttpStatus.OK)
	public List<CommentDto> getUserComments(@PathVariable("name") String name) {
		//userProfileService.findWithException();

		return userProfileService.getCommentsByUser(name).stream()
				.map(CommentDto::ofResult)
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