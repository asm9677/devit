package com.ssafy.devit.model.lecture;

import io.swagger.annotations.ApiModelProperty;

public class RequestHistoryResponse {
	@ApiModelProperty(example = "userProfileUrl")
	private String profile;
	@ApiModelProperty(example = "userNickname")
	private String nickname;
	@ApiModelProperty(example = "2020-08-06 05:41:06")
	private String created;
	@ApiModelProperty(example = "변수란!")
	private String subTitle;
	@ApiModelProperty(example = "1")
	private String subId;
	@ApiModelProperty(example = "Y 또는 N 또는 null")
	private String acceptYn;
	@ApiModelProperty(example = "video 또는 wiki")
	private String reqType;
	@ApiModelProperty(example = "videoThumbnailUrl")
	private String thumbnailUrl;
	@ApiModelProperty(example = "이런것이다!")
	private String hisTitle;
	@ApiModelProperty(example = "playerUrl")
	private String playerUrl;
	@ApiModelProperty(example = "13:30")
	private String playTime;
	@ApiModelProperty(example = "texttext.....")
	private String wikiContent;
	@ApiModelProperty(example = "<h1>text...</h1>")
	private String wikiContentHtml;
	
	public RequestHistoryResponse() {

	}

	public RequestHistoryResponse(String profile, String nickname, String created, String subTitle, String subId,
			String acceptYn, String reqType, String thumbnailUrl, String hisTitle, String playerUrl, String playTime,
			String wikiContent, String wikiContentHtml) {
		super();
		this.profile = profile;
		this.nickname = nickname;
		this.created = created;
		this.subTitle = subTitle;
		this.subId = subId;
		this.acceptYn = acceptYn;
		this.reqType = reqType;
		this.thumbnailUrl = thumbnailUrl;
		this.hisTitle = hisTitle;
		this.playerUrl = playerUrl;
		this.playTime = playTime;
		this.wikiContent = wikiContent;
		this.wikiContentHtml = wikiContentHtml;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public String getSubId() {
		return subId;
	}

	public void setSubId(String subId) {
		this.subId = subId;
	}

	public String getAcceptYn() {
		return acceptYn;
	}

	public void setAcceptYn(String acceptYn) {
		this.acceptYn = acceptYn;
	}

	public String getReqType() {
		return reqType;
	}

	public void setReqType(String reqType) {
		this.reqType = reqType;
	}

	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	public String getHisTitle() {
		return hisTitle;
	}

	public void setHisTitle(String hisTitle) {
		this.hisTitle = hisTitle;
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

	@Override
	public String toString() {
		return "RequestHistoryResponse [profile=" + profile + ", nickname=" + nickname + ", created=" + created
				+ ", subTitle=" + subTitle + ", subId=" + subId + ", acceptYn=" + acceptYn + ", reqType=" + reqType
				+ ", thumbnailUrl=" + thumbnailUrl + ", hisTitle=" + hisTitle + ", playerUrl=" + playerUrl
				+ ", playTime=" + playTime + ", wikiContent=" + wikiContent + ", wikiContentHtml=" + wikiContentHtml
				+ "]";
	}

}
