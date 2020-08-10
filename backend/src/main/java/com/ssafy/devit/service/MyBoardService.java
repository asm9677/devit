package com.ssafy.devit.service;

import java.util.List;
import com.ssafy.devit.model.lecture.BoardResponse;

public interface MyBoardService {
	public List<BoardResponse> myBoardList(long startPage, long itemsperpage) throws Exception;
	public List<BoardResponse> myReplyList(long startPage, long itemsperpage) throws Exception;

}
