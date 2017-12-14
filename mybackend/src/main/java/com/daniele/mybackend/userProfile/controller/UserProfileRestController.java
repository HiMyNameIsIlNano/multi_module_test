package com.daniele.mybackend.userProfile.controller;

import com.daniele.mybackend.userProfile.dto.CommentDto;
import com.daniele.mybackend.userProfile.dto.UserProfileDto;
import com.daniele.mybackend.userProfile.model.CommentFilter;
import com.daniele.mybackend.userProfile.service.CommentService;
import com.daniele.mybackend.userProfile.service.UserProfileService;
import com.daniele.mydatabase.userProfile.model.UserProfileDetails;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("user-rest")
public class UserProfileRestController {

	@Inject
	private UserProfileService userProfileService;

	@Inject
	private CommentService commentService;
	
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
		CommentFilter filter = new CommentFilter(LocalDate.now(),
				null,
				true,
				name);

		return commentService.getByFilter(filter).stream()
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