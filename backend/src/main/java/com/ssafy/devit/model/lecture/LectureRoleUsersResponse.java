package com.ssafy.devit.model.lecture;

import java.util.Date;

public class LectureRoleUsersResponse {
	private String nickname;
	private String profile;
	private String role;
	private Date created;
	private Date modified;

	public LectureRoleUsersResponse() {

	}

	public LectureRoleUsersResponse(String nickname, String profile, String role, Date created, Date modified) {
		super();
		this.nickname = nickname;
		this.profile = profile;
		this.role = role;
		this.created = created;
		this.modified = modified;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Date getCreate() {
		return created;
	}

	public void setCreate(Date create) {
		this.created = create;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	@Override
	public String toString() {
		return "LectureRoleUsersResponse [nickname=" + nickname + ", profile=" + profile + ", role=" + role
				+ ", create=" + created + ", modified=" + modified + "]";
	}

}