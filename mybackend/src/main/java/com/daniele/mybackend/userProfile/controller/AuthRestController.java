package com.daniele.mybackend.userProfile.controller;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

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

import com.daniele.mybackend.userProfile.service.UserProfileService;
import com.daniele.mybackend.shared.TokenUtils;
import com.daniele.mybackend.userProfile.dto.AuthDto;
import com.daniele.mydatabase.userProfile.model.UserProfileDetails;
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
        String token = null;
        if (authDto.getUser() != null && authDto.getPassword() != null) {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    authDto.getUser(),
                    authDto.getPassword());

            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            Optional<UserProfileDetails> userProfile = userProfileService.getUserByName(authDto.getUser());
            if (userProfile.isPresent()) {
				token = tokenUtils.generateToken(userProfile.get());
			}
        }
        Map<String, String> authResponse = Collections.singletonMap("token", token);
		return ResponseEntity.ok(authResponse);
	}
	
	@LogMethodExecution
	@PostMapping(path = "/validate-token")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<Map<String, Boolean>> validateToken(@RequestBody AuthDto authDto) throws AuthenticationException {
        Optional<UserProfileDetails> userProfile = userProfileService.getUserByName(authDto.getUser());
        Boolean isValid = false;
		Map<String, Boolean> response;

		if (userProfile.isPresent()) {
			isValid = tokenUtils.validateToken(authDto.getToken(), userProfile.get());
		}

		response = Collections.singletonMap("valid", isValid);
		return ResponseEntity.ok(response);
	}
	
}