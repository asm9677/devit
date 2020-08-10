package com.ssafy.devit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ssafy.devit.model.lecture.BoardResponse;
import com.ssafy.devit.model.user.UserAuthDetails;
import com.ssafy.devit.repository.MyBoardRepository;

@Service
public class MyBoardServicelmpl implements MyBoardService {
	
	@Autowired
	MyBoardRepository myBoardRepository;
	
	
	@Override
	public List<BoardResponse> myBoardList(long startPage, long itemsperpage) throws Exception {
		
		UserAuthDetails user = (UserAuthDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		startPage = (startPage-1) * itemsperpage;
		return myBoardRepository.myBoardList(user.getUserId(), startPage, itemsperpage);
	}
	
	@Override
	public List<BoardResponse> myReplyList(long startPage, long itemsperpage) throws Exception {
		
		UserAuthDetails user = (UserAuthDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		startPage = (startPage-1) * itemsperpage;
		return myBoardRepository.myReplyList(user.getUserId(), startPage, itemsperpage);
	}
}
