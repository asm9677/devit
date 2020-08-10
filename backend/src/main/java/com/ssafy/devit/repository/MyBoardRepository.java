package com.ssafy.devit.repository;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.ssafy.devit.model.lecture.BoardResponse;

public interface MyBoardRepository {
	public List<BoardResponse> myBoardList(@Param("userId") long userId, @Param("startPage") long startPage, @Param("itemsperpage") long itemsperpage) throws Exception;
	public List<BoardResponse> myReplyList(@Param("userId") long userId, @Param("startPage") long startPage, @Param("itemsperpage") long itemsperpage) throws Exception;
}
