package com.ssafy.devit.model.common;

public class SearchInfoResponse {
	private long lectureId;
	private String title;
	private String content;
	private String thumbnailUrl;
	private String nickname;
	private int lectureCount;
	private int viewCount;
	private int likeCount;
	private String tagName;
	private int type;
	private boolean userLikeYn;

	public SearchInfoResponse() {

	}

	public SearchInfoResponse(long lectureId, String title, String content, String thumbnailUrl, String nickname,
			int lectureCount, int viewCount, int likeCount, String tagName, int type, boolean userLikeYn) {
		this.lectureId = lectureId;
		this.title = title;
		this.content = content;
		this.thumbnailUrl = thumbnailUrl;
		this.nickname = nickname;
		this.lectureCount = lectureCount;
		this.viewCount = viewCount;
		this.likeCount = likeCount;
		this.tagName = tagName;
		this.type = type;
		this.userLikeYn = userLikeYn;
	}

	public long getLectureId() {
		return lectureId;
	}

	public void setLectureId(long lectureId) {
		this.lectureId = lectureId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getLectureCount() {
		return lectureCount;
	}

	public void setLectureCount(int lectureCount) {
		this.lectureCount = lectureCount;
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

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public boolean isUserLikeYn() {
		return userLikeYn;
	}

	public void setUserLikeYn(boolean userLikeYn) {
		this.userLikeYn = userLikeYn;
	}

	@Override
	public String toString() {
		return "SearchInfoResponse [lectureId=" + lectureId + ", title=" + title + ", content=" + content
				+ ", thumbnailUrl=" + thumbnailUrl + ", nickname=" + nickname + ", lectureCount=" + lectureCount
				+ ", viewCount=" + viewCount + ", likeCount=" + likeCount + ", tagName=" + tagName + ", type=" + type
				+ ", userLikeYn=" + userLikeYn + "]";
	}

}
