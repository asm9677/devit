package com.ssafy.devit.model.user;

import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class User implements UserDetails {

	private long userId;
	private String nickname;
	private String email;
	private String emailConfirm;
	private String password;
	private String active;
	private String profile;
	private Date created;
	private Date deleted;

	private Collection<? extends GrantedAuthority> authorities;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public User() {

	}

	public User(String email, String nickname, String password) {
		super();
		this.nickname = nickname;
		this.email = email;
		this.password = password;
	}

	public User(long userId, String nickname, String email, String emailConfirm, String password, String active,
			String profile, Date created, Date deleted, Collection<? extends GrantedAuthority> authorities) {
		super();
		this.userId = userId;
		this.nickname = nickname;
		this.email = email;
		this.emailConfirm = emailConfirm;
		this.password = password;
		this.active = active;
		this.profile = profile;
		this.created = created;
		this.deleted = deleted;
		this.authorities = authorities;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getDeleted() {
		return deleted;
	}

	public void setDeleted(Date deleted) {
		this.deleted = deleted;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getEmailConfirm() {
		return emailConfirm;
	}

	public void setEmailConfirm(String emailConfirm) {
		this.emailConfirm = emailConfirm;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", nickname=" + nickname + ", email=" + email + ", emailConfirm="
				+ emailConfirm + ", password=" + password + ", active=" + active + ", profile=" + profile + ", created="
				+ created + ", deleted=" + deleted + ", authorities=" + authorities + "]";
	}

}
