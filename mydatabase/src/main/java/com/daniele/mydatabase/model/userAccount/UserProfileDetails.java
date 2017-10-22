package com.daniele.mydatabase.model.userAccount;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "USER_PROFILE")
public class UserProfileDetails extends SlicedEntity implements UserDetails {

	@Transient
	private static final long serialVersionUID = -2470335388023065464L;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "SURNAME")
	private String surname;
	
	@Column(name = "EMAIL")
	private String email;

	@Column(name = "NICKNAME")
	private String nickname;
	
	@Column(name = "PASSWORD")
	private String password;
	
	@Column(name = "ROLE")
	private UserRole userRole;
	
	public UserProfileDetails() {
	}
	
	private UserProfileDetails(UserProfileBuilder builder) {
		this.id = builder.userProfile.id;
		this.name = builder.userProfile.name;
		this.surname = builder.userProfile.surname;
		this.email = builder.userProfile.email;
		this.nickname = builder.userProfile.nickname;
		this.password = builder.userProfile.password;
		this.userRole = builder.userProfile.userRole;
	}

	public String getName() {
		return name;
	}
	
	public String getSurname() {
		return surname;
	}

	public String getEmail() {
		return email;
	}

	public String getNickname() {
		return nickname;
	}

	public String getPassword() {
		return password;
	}
	
	public UserRole getUserRole() {
		return userRole;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return AuthorityUtils.commaSeparatedStringToAuthorityList(getUserRole().getAuthority());
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String toString() {
		return "UserProfile [name=" + name 
				+ ", surname=" + surname 
				+ ", email=" + email 
				+ ", nickname=" + nickname
				+ ", password=" + password
				+ ", role=" + userRole
				+ "]";
	}
	
	public static class UserProfileBuilder {
		
		protected UserProfileDetails userProfile;
		
		public static UserProfileBuilder forCreation() {
			return new UserProfileBuilder(new UserProfileDetails());
		}
		
		public static UserProfileBuilder forUpdate(UserProfileDetails userProfile) {
			return new UserProfileBuilder(userProfile);
		}
		
		private UserProfileBuilder(UserProfileDetails userProfile) {
			this.userProfile = userProfile;
		}
		
		public UserProfileBuilder withName(String name) {
			this.userProfile.name = name;
			return this;
		}

		public UserProfileBuilder withSurname(String surname) {
			this.userProfile.surname = surname;
			return this;
		}

		public UserProfileBuilder withEmail(String email) {
			this.userProfile.email = email;
			return this;
		}

		public UserProfileBuilder withNickname(String nickname) {
			this.userProfile.nickname = nickname;
			return this;
		}

		public UserProfileBuilder withPassword(String password) {
			this.userProfile.password = password;
			return this;
		}
		
		public UserProfileBuilder withUserRole(UserRole userRole) {
			this.userProfile.userRole = userRole;
			return this;
		}
		
		public UserProfileDetails build() {
			return new UserProfileDetails(this);
		}
	}
}