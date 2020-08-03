package com.ssafy.devit.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssafy.devit.model.lecture.LectureOneResponse;
import com.ssafy.devit.model.lecture.LecturesResponse;
import com.ssafy.devit.model.lecture.TagResponse;
import com.ssafy.devit.model.request.LectrueRequest;

public interface LectureService {
	public void registLecture(LectrueRequest lecture) throws Exception;

	public List<LecturesResponse> getLectures(long userId, int startPage, int type) throws Exception;

	public int getLectureSubCount() throws Exception;
	
	public List<TagResponse> getTags() throws Exception;
	
	public LectureOneResponse getLectureBylectureId(@Param("lectureId") long lectureId, @Param("userId") long userId) throws Exception;
	
	public void updateLikeLectureByUserId(long lectureId, int likeType) throws Exception;
}