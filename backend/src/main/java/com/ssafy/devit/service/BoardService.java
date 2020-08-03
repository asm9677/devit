package com.ssafy.devit.service;

import java.util.List;

import com.ssafy.devit.model.board.Board;
import com.ssafy.devit.model.board.BoardResponse;

public interface BoardService {
	public void upload(Board board) throws Exception;
	public BoardResponse info(long bid) throws Exception;
	public void delete(long bid) throws Exception;
	public void update(BoardResponse boardResponse, long bid) throws Exception;
	public List<BoardResponse> listinfo(long type) throws Exception;
}
