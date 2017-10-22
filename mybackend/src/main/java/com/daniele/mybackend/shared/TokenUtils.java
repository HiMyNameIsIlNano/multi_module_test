package com.daniele.mybackend.shared;

import java.io.UnsupportedEncodingException;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.daniele.mydatabase.model.userAccount.UserProfileDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenUtils {

	@Inject
	private Environment env;

	public String getTokenHeader() {
		return env.getProperty("application.token.header");
	}

	public String getUsernameFromToken(String token) {
		String username;
		try {
			final Claims claims = this.getClaimsFromToken(token);
			username = claims.getSubject();
		} catch (Exception e) {
			username = null;
		}
		return username;
	}

	public Date getCreatedDateFromToken(String token) {
		Date created;
		try {
			final Claims claims = this.getClaimsFromToken(token);
			created = new Date((Long) claims.get("created"));
		} catch (Exception e) {
			created = null;
		}
		return created;
	}

	public Date getExpirationDateFromToken(String token) {
		Date expiration;
		try {
			final Claims claims = this.getClaimsFromToken(token);
			expiration = claims.getExpiration();
		} catch (Exception e) {
			expiration = null;
		}
		return expiration;
	}

	private Claims getClaimsFromToken(String token) {
		Claims claims;
		try {
			String secret = env.getProperty("application.token.secret");
			claims = Jwts.parser().setSigningKey(secret.getBytes("UTF-8")).parseClaimsJws(token).getBody();
		} catch (Exception e) {
			claims = null;
		}
		return claims;
	}

	private Date generateExpirationDate() {
		Long expiration = Long.valueOf(env.getProperty("application.token.expiration"));
		Long tokenExpiration = System.currentTimeMillis() + expiration * 1000;
		return Date.from(Instant.ofEpochMilli(tokenExpiration));
	}

	private Date generateCurrentDate() {
		return Date.from(Instant.now());
	}

	public String generateToken(UserProfileDetails userProfile) {
		Map<String, Object> claims = new HashMap<String, Object>();
		claims.put("sub", userProfile.getEmail());
		claims.put("audience", "web");
		claims.put("created", this.generateCurrentDate());
		return this.generateToken(claims);
	}

	// TODO: add AOP for exceptions
	private String generateToken(Map<String, Object> claims) {
		String secret = env.getProperty("application.token.secret");
		try {
			return Jwts.builder().setClaims(claims).setExpiration(this.generateExpirationDate())
					.signWith(SignatureAlgorithm.HS512, secret.getBytes("UTF-8")).compact();
		} catch (UnsupportedEncodingException ex) {
			return Jwts.builder().setClaims(claims).setExpiration(this.generateExpirationDate())
					.signWith(SignatureAlgorithm.HS512, secret).compact();
		}
	}

	public Boolean validateToken(String token, UserDetails userDetails) {
		UserProfileDetails user = (UserProfileDetails) userDetails;
		final String username = getUsernameFromToken(token);
		return (username.equals(user.getUsername()) && !(isTokenExpired(token)));
	}

	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(generateCurrentDate());
	}

}