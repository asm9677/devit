package com.ssafy.devit.model.board;

import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class Board {

	private long userId;
	private String boardTitle;
	private String boardContent;
	private long boardType;
	private Date boardCreated;
	private long boardCount;
	private Date boardModified;
	
	public Board() {

	}

	public Board(long userId, String boardTitle, String boardContent, long boardType, long boardCount) {
		this.userId = userId;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardType = boardType;
		this.boardCount = boardCount;
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
