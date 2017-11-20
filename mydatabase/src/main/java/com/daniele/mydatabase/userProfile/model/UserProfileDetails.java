package com.daniele.mydatabase.userProfile.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import com.daniele.mydatabase.shared.model.SlicedEntity;

@Entity
@Table(name = "USER_PROFILE")
public class UserProfileDetails extends SlicedEntity implements UserDetails {

	@Transient
	private static final long serialVersionUID = -2470335388023065464L;
	
	@Column(name = "IMG_PATH")
	private String imgPath;
	
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
	
	@OneToMany(mappedBy="user", cascade = CascadeType.ALL)
	private List<Comment> comments = new ArrayList<>();
	
	public UserProfileDetails() {
	}
	
	private UserProfileDetails(UserProfileBuilder builder) {
		this.id = builder.userProfile.id;
		this.name = builder.userProfile.name;
		this.imgPath = builder.userProfile.imgPath;
		this.surname = builder.userProfile.surname;
		this.email = builder.userProfile.email;
		this.nickname = builder.userProfile.nickname;
		this.password = builder.userProfile.password;
		this.userRole = builder.userProfile.userRole;
		this.comments = builder.userProfile.comments;
	}

	public String getImgPath() {
		return imgPath;
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
	
	public List<Comment> getComments() {
		return comments;
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
				+ ", comments=" + comments
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
		
		public UserProfileBuilder withImgPath(String imgPath) {
			this.userProfile.imgPath = imgPath;
			return this;
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
		
		public UserProfileBuilder withComment(Comment comment) {
			this.userProfile.comments.add(comment);
			Comment.CommentBuilder
				.forUpdate(comment)
				.withUser(this.userProfile);
			
			return this;
		}
		
		public UserProfileBuilder withComments(List<Comment> comments) {
			for (Comment comment : comments) {
				this.userProfile.comments.add(comment);
				Comment.CommentBuilder
					.forUpdate(comment)
					.withUser(this.userProfile);
			}
			return this;
		}
		
		public UserProfileDetails build() {
			return new UserProfileDetails(this);
		}
	}
}