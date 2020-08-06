package com.ssafy.devit.model.lecture;

public class TheOhterSubLectureResponse {
	private long lectureId;
	private long subId;
	private String title;
	private String playerUrl;
	private String playTime;
	private int viewCount;
	private int likeCount;
	private String thumbnailUrl;
	private boolean userLikeYn;

	public TheOhterSubLectureResponse(long lectureId, long subId, String title, String playerUrl, String playTime,
			int viewCount, int likeCount, String thumbnailUrl, boolean userLikeYn) {
		super();
		this.lectureId = lectureId;
		this.subId = subId;
		this.title = title;
		this.playerUrl = playerUrl;
		this.playTime = playTime;
		this.viewCount = viewCount;
		this.likeCount = likeCount;
		this.thumbnailUrl = thumbnailUrl;
		this.userLikeYn = userLikeYn;
	}

	public TheOhterSubLectureResponse() {
		super();
	}

	public long getLectureId() {
		return lectureId;
	}

	public void setLectureId(long lectureId) {
		this.lectureId = lectureId;
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

	public String getPlayerUrl() {
		return playerUrl;
	}

	public void setPlayerUrl(String playerUrl) {
		this.playerUrl = playerUrl;
	}

	public String getPlayTime() {
		return playTime;
	}

	public void setPlayTime(String playTime) {
		this.playTime = playTime;
	}

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	public boolean isUserLikeYn() {
		return userLikeYn;
	}

	public void setUserLikeYn(boolean userLikeYn) {
		this.userLikeYn = userLikeYn;
	}

	@Override
	public String toString() {
		return "TheOhterSubLectureResponse [lectureId=" + lectureId + ", subId=" + subId + ", title=" + title
				+ ", playerUrl=" + playerUrl + ", playTime=" + playTime + ", viewCount=" + viewCount + ", likeCount="
				+ likeCount + ", thumbnailUrl=" + thumbnailUrl + ", userLikeYn=" + userLikeYn + "]";
	}

}
