package com.ssafy.devit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ssafy.devit.model.lecture.BoardResponse;
import com.ssafy.devit.model.lecture.LecturesResponse;
import com.ssafy.devit.model.lecture.TheOhterSubLectureResponse;
import com.ssafy.devit.model.user.UserAuthDetails;
import com.ssafy.devit.repository.MyBoardRepository;
import com.ssafy.devit.repository.MyLikeRepository;

@Service
public class MyLikeServicelmpl implements MyLikeService {
	
	@Autowired
	MyLikeRepository myLikeRepository;
	
	
	@Override
	public List<LecturesResponse> myLikeLectureList(long startPage, long itemsperpage) throws Exception {
		
		UserAuthDetails user = (UserAuthDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		startPage = (startPage-1) * itemsperpage;
		return myLikeRepository.myLikeLectureList(user.getUserId(), startPage, itemsperpage);
	}
	
	@Override
	public List<TheOhterSubLectureResponse> myLikeVideoList(long startPage, long itemsperpage) throws Exception {
		
		UserAuthDetails user = (UserAuthDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		startPage = (startPage-1) * itemsperpage;
		return myLikeRepository.myLikeVideoList(user.getUserId(), startPage, itemsperpage);
	}
}
