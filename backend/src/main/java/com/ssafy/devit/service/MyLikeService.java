package com.ssafy.devit.service;

import java.util.List;
import com.ssafy.devit.model.lecture.BoardResponse;
import com.ssafy.devit.model.lecture.LecturesResponse;
import com.ssafy.devit.model.lecture.TheOhterSubLectureResponse;

public interface MyLikeService {
	public List<LecturesResponse> myLikeLectureList(long startPage, long itemsperpage) throws Exception;
	public List<TheOhterSubLectureResponse> myLikeVideoList(long startPage, long itemsperpage) throws Exception;

}
