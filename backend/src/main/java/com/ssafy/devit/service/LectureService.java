package com.ssafy.devit.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssafy.devit.model.lecture.LectureOneResponse;
import com.ssafy.devit.model.lecture.LectureRoleUsersResponse;
import com.ssafy.devit.model.lecture.LectureSubIndexResponse;
import com.ssafy.devit.model.lecture.LectureSubOneResponse;
import com.ssafy.devit.model.lecture.LecturesResponse;
import com.ssafy.devit.model.lecture.TagResponse;
import com.ssafy.devit.model.request.LectureRequest;
import com.ssafy.devit.model.request.LectureSubsRequest;

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
	
	// 소강의 생성 및 변경된 개수 반환할 인터페이스 
	public int createSubLectures(List<LectureSubsRequest> lectures) throws Exception;
	
	// 소강의 목차 상세 정보 가져오기
	public LectureSubOneResponse getOneSubLecture(long lectureId, int order) throws Exception;
	
	// 목차 리스트 가져오기
	public List<LectureSubIndexResponse> getSubLectureIndex(@Param("lectureId") long lectureId) throws Exception;
}