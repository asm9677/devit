package com.ssafy.devit.model.user;

public class UserResponse {
	private long userId;
	private String nickname;
	private String email;
	
	public UserResponse() {
		
	}

	public UserResponse(long userId, String nickname, String email) {
		this.userId = userId;
		this.nickname = nickname;
		this.email = email;
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

	@Override
	public String toString() {
		return "UserResponse [userId=" + userId + ", nickname=" + nickname + ", email=" + email + "]";
	}
	
	
}
