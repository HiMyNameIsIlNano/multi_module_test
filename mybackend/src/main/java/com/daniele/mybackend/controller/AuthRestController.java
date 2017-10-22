package com.daniele.mybackend.controller;

import java.util.Collections;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.daniele.mybackend.dto.AuthDto;
import com.daniele.mybackend.service.UserProfileService;
import com.daniele.mybackend.shared.TokenUtils;
import com.daniele.mydatabase.model.userAccount.UserProfileDetails;
import com.daniele.mylogger.LogMethodExecution;

@RestController
@RequestMapping("auth-rest")
public class AuthRestController {

	@Inject
	private UserProfileService userProfileService;

	@Inject
	private AuthenticationManager authenticationManager;

	@Inject
	private TokenUtils tokenUtils;

	@LogMethodExecution
	@PostMapping(path = "/auth")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<Map<String, String>> authUser(@RequestBody AuthDto authDto) throws AuthenticationException {
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				authDto.getEmail(), 
				authDto.getPassword());
		
		Authentication authentication = authenticationManager.authenticate(authenticationToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		UserProfileDetails userProfile = userProfileService.getUserByEmail(authDto.getEmail());
		String token = tokenUtils.generateToken(userProfile);
		Map<String, String> authResponse = Collections.singletonMap("token", token);
		
		return ResponseEntity.ok(authResponse);
	}
	
	@LogMethodExecution
	@PostMapping(path = "/validate-token")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<Map<String, Boolean>> validateToken(@RequestBody AuthDto authDto) throws AuthenticationException {
		UserProfileDetails userProfile = userProfileService.getUserByEmail(authDto.getEmail());
		Boolean isValid = tokenUtils.validateToken(authDto.getToken(), userProfile);
		Map<String, Boolean> response = Collections.singletonMap("valid", isValid);
		return ResponseEntity.ok(response);
	}
	
}