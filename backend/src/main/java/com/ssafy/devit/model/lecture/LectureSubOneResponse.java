package com.ssafy.devit.model.lecture;

public class LectureSubOneResponse {
	private long lectureId;
	private long subId;
	private long subHisId;
	private int order;
	private String title;
	private int viewCount;
	private int likeCount;
	private String profile;
	private String nickname;
	private String playTime;
	private String playerUrl;
	private String wikiContent;
	private String wikiContentHtml;
	private String tagName;
	private String thumbnailUrl;
	private boolean userLikeYn;
	private int lectureCount;

	public LectureSubOneResponse(long lectureId, long subId, long subHisId, int order, String title, int viewCount,
			int likeCount, String profile, String nickname, String playTime, String playerUrl, String wikiContent,
			String wikiContentHtml, String tagName, String thumbnailUrl, boolean userLikeYn, int lectureCount) {
		super();
		this.lectureId = lectureId;
		this.subId = subId;
		this.subHisId = subHisId;
		this.order = order;
		this.title = title;
		this.viewCount = viewCount;
		this.likeCount = likeCount;
		this.profile = profile;
		this.nickname = nickname;
		this.playTime = playTime;
		this.playerUrl = playerUrl;
		this.wikiContent = wikiContent;
		this.wikiContentHtml = wikiContentHtml;
		this.tagName = tagName;
		this.thumbnailUrl = thumbnailUrl;
		this.userLikeYn = userLikeYn;
		this.lectureCount = lectureCount;
	}

	public LectureSubOneResponse() {
		super();
	}

	public int getLectureCount() {
		return lectureCount;
	}

	public void setLectureCount(int lectureCount) {
		this.lectureCount = lectureCount;
	}

	public long getSubHisId() {
		return subHisId;
	}

	public void setSubHisId(long subHisId) {
		this.subHisId = subHisId;
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

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPlayTime() {
		return playTime;
	}

	public void setPlayTime(String playTime) {
		this.playTime = playTime;
	}

	public String getPlayerUrl() {
		return playerUrl;
	}

	public void setPlayerUrl(String playerUrl) {
		this.playerUrl = playerUrl;
	}

	public String getWikiContent() {
		return wikiContent;
	}

	public void setWikiContent(String wikiContent) {
		this.wikiContent = wikiContent;
	}

	public String getWikiContentHtml() {
		return wikiContentHtml;
	}

	public void setWikiContentHtml(String wikiContentHtml) {
		this.wikiContentHtml = wikiContentHtml;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public boolean isUserLikeYn() {
		return userLikeYn;
	}

	public void setUserLikeYn(boolean userLikeYn) {
		this.userLikeYn = userLikeYn;
	}

	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	@Override
	public String toString() {
		return "LectureSubOneResponse [lectureId=" + lectureId + ", subId=" + subId + ", order=" + order + ", title="
				+ title + ", viewCount=" + viewCount + ", likeCount=" + likeCount + ", nickname=" + nickname
				+ ", playTime=" + playTime + ", playerUrl=" + playerUrl + ", wikiContent=" + wikiContent
				+ ", wikiContentHtml=" + wikiContentHtml + ", tagName=" + tagName + ", userLikeYn=" + userLikeYn + "]";
	}

}
