package com.ssafy.devit.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssafy.devit.model.board.Board;
import com.ssafy.devit.model.board.BoardResponse;
import com.ssafy.devit.model.lecture.LectureOneResponse;

public interface BoardRepository {
	public void upload(Board board) throws Exception;
	public BoardResponse info(@Param("bid") long bid, @Param("userId") long userId) throws Exception;
	public void delete(long bid) throws Exception;
	public void update(@Param("Board") BoardResponse boardResponse, @Param("bid") long bid) throws Exception;
	public List<BoardResponse> listinfo(@Param("startPage") long startPage, @Param("type") long type, @Param("itemsperpage") long itemsperpage) throws Exception;
	//public LectureOneResponse selectLectureByLectureId(@Param("lectureId") long lectureId, @Param("userId") long userId) throws Exception;
	
}
