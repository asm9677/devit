package com.ssafy.devit.model.board;

import java.util.Date;

public class BoardResponse {
	private long boardId;
	private long userId;
	private String userName;
	private String boardTitle;
	private String boardContent;
	private long boardType;
	private Date boardCreated;
	private Date boardModified;
	private long boardCount;
	private String isMine;
	private long pageCnt;
	
	public BoardResponse() {}
	
	public BoardResponse(long boardId, long userId, String nickName, String boardTitle, String boardContent, 
			long boardType, Date boardCreated, Date boardModified, long boardCount, String isMine, long pageCnt){
		this.boardId = boardId;
		this.userId = userId;
		this.userName = nickName;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardType = boardType;
		this.boardCreated = boardCreated;
		this.boardModified = boardModified;
		this.boardCount = boardCount;
		this.isMine = isMine;
		this.pageCnt = pageCnt;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public long getBoardType() {
		return boardType;
	}

	public void setBoardType(long boardType) {
		this.boardType = boardType;
	}

	public long getBoardId() {
		return boardId;
	}

	public void setBoardId(long boardId) {
		this.boardId = boardId;
	}

	public String getNickName() {
		return userName;
	}

	public void setNickName(String nickName) {
		this.userName = nickName;
	}

	public Date getBoardCreated() {
		return boardCreated;
	}

	public void setBoardCreated(Date boardCreated) {
		this.boardCreated = boardCreated;
	}

	public Date getBoardModified() {
		return boardModified;
	}

	public void setBoardModified(Date boardModified) {
		this.boardModified = boardModified;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
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

	public long getBoardCount() {
		return boardCount;
	}

	public void setBoardCount(long boardCount) {
		this.boardCount = boardCount;
	}
	
	public String getIsMine() {
		return isMine;
	}
	
	public void setIsMine(String isMine) {
		this.isMine = isMine;
	}
	
	public long getPageCnt() {
		return pageCnt;
	}
	
	public void setPageCnt(long pageCnt) {
		this.pageCnt = pageCnt;
	}
}
