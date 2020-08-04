package com.ssafy.devit.model.lecture;

import java.util.Date;

public class LectureRoleUsersResponse {
	private String nickname;
	private String profile;
	private String role;
	private Date create;
	private Date modified;

	public LectureRoleUsersResponse() {

	}

	public LectureRoleUsersResponse(String nickname, String profile, String role, Date create, Date modified) {
		super();
		this.nickname = nickname;
		this.profile = profile;
		this.role = role;
		this.create = create;
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
		return create;
	}

	public void setCreate(Date create) {
		this.create = create;
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
				+ ", create=" + create + ", modified=" + modified + "]";
	}

}
