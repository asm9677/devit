package com.ssafy.devit.service;

import java.util.List;

import com.ssafy.devit.model.lecture.LectureOneResponse;
import com.ssafy.devit.model.lecture.LectureRoleUsersResponse;
import com.ssafy.devit.model.lecture.LecturesResponse;
import com.ssafy.devit.model.lecture.TagResponse;
import com.ssafy.devit.model.request.LectureRequest;

public interface LectureService {
	public LectureOneResponse createDummyLecture() throws Exception;

	public List<LecturesResponse> getLectures(long userId, int startPage, int type) throws Exception;

	public int getLectureSubCount() throws Exception;
	
	public List<TagResponse> getTags() throws Exception;
	
	public LectureOneResponse getLectureBylectureId(long lectureId, long userId) throws Exception;
	
	public void updateLikeLectureByUserId(long lectureId) throws Exception;
	
	public void updateFoundationLecture(LectureRequest lecture) throws Exception;
	
	public void updateContentLecture(LectureRequest lecture) throws Exception;
	
	public void insertAuthLecture(long lectureId, String role) throws Exception;
	
	public List<LectureRoleUsersResponse> selectRoleUsersByLectureId(long lectureId) throws Exception;
	
	
}