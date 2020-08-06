package com.ssafy.devit.model.reply;

import java.util.Date;

public class ReplyResponse {
	private long boardReplyId;
	private long boardId;	
	private long userId;
	private Date replyCreated;
	private String replyContent;
	private long parentReplyId;
	private Date replyModified;
	private String deleteYn;
	private String isMine;
	
	public ReplyResponse() {}
	
	public ReplyResponse(long boardReplyId, long boardId, long userId, Date replyCreated, String replyContent, long parentReplyId, Date replyModified,
	String deleteYn, String isMine){
		this.boardReplyId = boardReplyId;
		this.boardId = boardId;
		this.userId = userId;
		this.replyCreated = replyCreated;
		this.replyContent = replyContent;
		this.parentReplyId = parentReplyId;
		this.replyModified = replyModified;
		this.deleteYn = deleteYn;
		this.isMine = isMine;
	}

	////////////////////////////////////////////////////////
	public long getBoardReplyId() {
		return boardReplyId;
	}
	public void setBoardReplyId(long boardReplyId) {
		this.boardReplyId = boardReplyId;
	}
	////////////////////////////////////////////////////////
	public long getBoardId() {
		return boardId;
	}
	public void setBoardId(long boardId) {
		this.boardId = boardId;
	}
	////////////////////////////////////////////////////////
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	////////////////////////////////////////////////////////
	public Date getReplyCreated() {
		return replyCreated;
	}
	public void setReplyCreated(Date replyCreated) {
		this.replyCreated = replyCreated;
	}
	////////////////////////////////////////////////////////
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	////////////////////////////////////////////////////////
	public long getParentReplyId() {
		return parentReplyId;
	}
	public void setParentReplyId(long parentReplyId) {
		this.parentReplyId = parentReplyId;
	}	
	////////////////////////////////////////////////////////
	public Date getReplyModified() {
		return replyModified;
	}
	public void setReplyModified(Date replyModified) {
		this.replyModified = replyModified;
	}
	////////////////////////////////////////////////////////
	public String getDeleteYn() {
		return deleteYn;
	}	
	public void setDeleteYn(String deleteYn) {
		this.deleteYn = deleteYn;
	}
	////////////////////////////////////////////////////////
	public String getIsMine() {
		return isMine;
	}	
	public void setIsMine(String isMine) {
		this.isMine = isMine;
	}
	
	
}
