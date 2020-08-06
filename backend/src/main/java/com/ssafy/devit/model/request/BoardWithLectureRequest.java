package com.ssafy.devit.model.request;

import java.util.Date;

public class BoardWithLectureRequest {

	private long boardId;
	private long lectureId;
	private long subId;
	private long userId;
	private String boardTitle;
	private String boardContent;
	private long boardType;
	private Date boardCreated;
	private long boardCount;
	private Date boardModified;
	

	public BoardWithLectureRequest(long lectureId, long subId, long userId, String boardTitle, String boardContent, long boardType, long boardCount) {
		this.lectureId = lectureId;
		this.subId = subId;
		this.userId = userId;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardType = boardType;
		this.boardCount = boardCount;
	}
	
	public long getBoardId() {
		return boardId;
	}

	public void setBoardId(long boardId) {
		this.boardId = boardId;
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

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public long getBoardType() {
		return boardType;
	}

	public void setBoardType(long boardType) {
		this.boardType = boardType;
	}

	public Date getBoardCreated() {
		return boardCreated;
	}

	public void setBoardCreated(Date boardCreated) {
		this.boardCreated = boardCreated;
	}

	public long getBoardCount() {
		return boardCount;
	}

	public void setBoardCount(long boardCount) {
		this.boardCount = boardCount;
	}

	public Date getBoardModified() {
		return boardModified;
	}

	public void setBoardModified(Date boardModified) {
		this.boardModified = boardModified;
	}
}
