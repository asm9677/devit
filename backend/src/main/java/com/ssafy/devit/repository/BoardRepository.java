package com.ssafy.devit.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssafy.devit.model.board.Board;
import com.ssafy.devit.model.board.BoardResponse;

public interface BoardRepository {
	public void upload(Board board) throws Exception;
	public BoardResponse info(long bid) throws Exception;
	public void delete(long bid) throws Exception;
	public void update(@Param("Board") BoardResponse boardResponse, @Param("bid") long bid) throws Exception;
	public List<BoardResponse> listinfo(long type) throws Exception;
}
