package com.ssafy.devit.model.lecture;

public class RequestHistoryResponse {
	private String profile;
	private String nickname;
	private String created;
	private String subTitle;
	private String subId;
	private String acceptYn;
	private String reqType;
	private String thumbnailUrl;
	private String hisTitle;
	private String playerUrl;
	private String playTime;
	private String wikiContent;
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
