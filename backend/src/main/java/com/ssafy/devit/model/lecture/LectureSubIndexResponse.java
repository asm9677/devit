package com.ssafy.devit.model.lecture;

public class LectureSubIndexResponse {
	private long lectureid;
	private long subId;
	private String title;
	private String playTime;
	private int order;

	public LectureSubIndexResponse() {
		super();
	}

	public LectureSubIndexResponse(long lectureid, long subId, String title, String playTime, int order) {
		super();
		this.lectureid = lectureid;
		this.subId = subId;
		this.title = title;
		this.playTime = playTime;
		this.order = order;
	}

	public long getLectureid() {
		return lectureid;
	}

	public void setLectureid(long lectureid) {
		this.lectureid = lectureid;
	}

	public long getSubId() {
		return subId;
	}

	public void setSubId(long subId) {
		this.subId = subId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPlayTime() {
		return playTime;
	}

	public void setPlayTime(String playTime) {
		this.playTime = playTime;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return "LectureSubIndexResponse [lectureid=" + lectureid + ", subId=" + subId + ", title=" + title
				+ ", playTime=" + playTime + ", order=" + order + "]";
	}

}
