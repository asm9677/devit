package com.ssafy.devit.model.request;

import java.util.Arrays;

public class LectrueRequest {
	private long userId;
	private long commonId;
	private String title;
	private String content;
	private String thumbnailUrl;
	private int type;
	private String[] tags;

	public LectrueRequest() {

	}

	public LectrueRequest(long userId, long commonId, String title, String content, String thumbnailUrl, int type,
			String[] tags) {
		this.userId = userId;
		this.commonId = commonId;
		this.title = title;
		this.content = content;
		this.thumbnailUrl = thumbnailUrl;
		this.type = type;
		this.tags = tags;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getCommonId() {
		return commonId;
	}

	public void setCommonId(long commonId) {
		this.commonId = commonId;
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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String[] getTags() {
		return tags;
	}

	public void setTags(String[] tags) {
		this.tags = tags;
	}

	@Override
	public String toString() {
		return "LectureRequest [userId=" + userId + ", commonId=" + commonId + ", title=" + title + ", content="
				+ content + ", thumbnailUrl=" + thumbnailUrl + ", type=" + type + ", tags=" + Arrays.toString(tags)
				+ "]";
	}

}
