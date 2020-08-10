package com.ssafy.devit.service;

import java.util.List;

import com.ssafy.devit.model.lecture.LectureOneResponse;
import com.ssafy.devit.model.lecture.LectureRoleUsersResponse;
import com.ssafy.devit.model.lecture.LectureSubIndexResponse;
import com.ssafy.devit.model.lecture.LectureSubOneResponse;
import com.ssafy.devit.model.lecture.LecturesResponse;
import com.ssafy.devit.model.lecture.TagResponse;
import com.ssafy.devit.model.lecture.TheOhterSubLectureResponse;
import com.ssafy.devit.model.request.HistoryLikeRequest;
import com.ssafy.devit.model.request.LectureAuthRequest;
import com.ssafy.devit.model.request.LectureRequest;
import com.ssafy.devit.model.request.LectureSubHistoryRequest;
import com.ssafy.devit.model.request.LectureSubOtherRequest;
import com.ssafy.devit.model.request.LectureSubsRequest;

public interface LectureService {
	public LectureOneResponse createDummyLecture() throws Exception;

	public List<LecturesResponse> getLectures(long userId, int startPage, int type) throws Exception;

	public List<TagResponse> getTags() throws Exception;
	
	public LectureOneResponse getLectureBylectureId(long lectureId, long userId) throws Exception;
	
	public void updateFoundationLecture(LectureRequest lecture) throws Exception;
	
	public void updateContentLecture(LectureRequest lecture) throws Exception;
	
	public void insertAuthLecture(long lectureId, String role) throws Exception;
	
	public List<LectureRoleUsersResponse> selectRoleUsersByLectureId(long lectureId) throws Exception;
	
	// 소강의 생성 및 변경된 개수 반환할 인터페이스 
	public int createSubLectures(List<LectureSubsRequest> lectures) throws Exception;
	
	// 소강의 목차 상세 정보 가져오기
	public LectureSubOneResponse getOneSubLecture(long lectureId, int order) throws Exception;
	
	// 목차 리스트 가져오기
	public List<LectureSubIndexResponse> getSubLectureIndex(long lectureId) throws Exception;
	
	// 강의 위키 요청 리퀘스트
	public void registrySubHistory(LectureSubHistoryRequest lecture) throws Exception;
	
	// 강의 권한 병경
	public void updateLectureAuth(List<LectureAuthRequest> auth) throws Exception;
	
    // 강의 권한 제거
	public void deleteLectureAuth(long authId) throws Exception;
	
	// 같은 종류 다른 강의들 들고오기
	public List<TheOhterSubLectureResponse> getTheOtherSubLectures(long subId) throws Exception;
	
	// 프로젝트 좋아요 업데이트
	public void updateLikeLectureByUserId(long lectureId) throws Exception;
	
	// 히스토리 좋아요 업데이트
	public void updateLikeHistoryByUserId(HistoryLikeRequest request) throws Exception;
	
	// 같은종류 다른영상 상세정보
	public LectureSubOneResponse getOneOtherSubLecture(LectureSubOtherRequest request) throws Exception;
}