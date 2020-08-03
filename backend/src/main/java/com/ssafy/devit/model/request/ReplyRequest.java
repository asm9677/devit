package com.ssafy.devit.model.request;

import java.util.Date;

public class ReplyRequest {
	private long boardId;
	private long userId;
	private Date replyCreated;
	private String replyContent;
	private long parentReplyId;

	ReplyRequest(){}
	
	ReplyRequest(long boardId, long userId, Date replyCreated, String replyContent, long parentReplyId){
		this.boardId = boardId;
		this.userId = userId;
		this.replyCreated = replyCreated;
		this.replyContent = replyContent;
		this.parentReplyId = parentReplyId;
	}
	
	public long getBoardId() {
		return boardId;
	}

	public void setBoardId(long boardId) {
		this.boardId = boardId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public Date getReplyCreated() {
		return replyCreated;
	}

	public void setReplyCreated(Date replyCreated) {
		this.replyCreated = replyCreated;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public long getParentReplyId() {
		return parentReplyId;
	}

	public void setParentReplyId(long parentReplyId) {
		this.parentReplyId = parentReplyId;
	}
	
	
}
