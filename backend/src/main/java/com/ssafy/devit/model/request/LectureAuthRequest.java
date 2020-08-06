package com.ssafy.devit.model.request;

public class LectureAuthRequest {
	private long userId;
	private String lectureRole;

	public LectureAuthRequest(long userId, String lectureRole) {
		super();
		this.userId = userId;
		this.lectureRole = lectureRole;
	}

	public LectureAuthRequest() {
		super();
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getLectureRole() {
		return lectureRole;
	}

	public void setLectureRole(String lectureRole) {
		this.lectureRole = lectureRole;
	}

	@Override
	public String toString() {
		return "LectureAuthRequest [userId=" + userId + ", lectureRole=" + lectureRole + "]";
	}

}
