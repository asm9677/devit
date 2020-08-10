package com.ssafy.devit.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.devit.model.common.Common;
import com.ssafy.devit.model.lecture.LectureOneResponse;
import com.ssafy.devit.model.lecture.LectureRoleUsersResponse;
import com.ssafy.devit.model.lecture.LectureSubIndexResponse;
import com.ssafy.devit.model.lecture.LectureSubOneResponse;
import com.ssafy.devit.model.lecture.LecturesResponse;
import com.ssafy.devit.model.lecture.LikeDTO;
import com.ssafy.devit.model.lecture.TagResponse;
import com.ssafy.devit.model.lecture.TheOhterSubLectureResponse;
import com.ssafy.devit.model.request.HistoryLikeRequest;
import com.ssafy.devit.model.request.LectureAuthRequest;
import com.ssafy.devit.model.request.LectureRequest;
import com.ssafy.devit.model.request.LectureSubHistoryRequest;
import com.ssafy.devit.model.request.LectureSubsRequest;

public interface LectureRepository {
	// common id 생성 및 반환 dao
	@Transactional
	public void insertCommonId(Common common) throws Exception;

	// lecture 생성 및 id 반환 dao
	@Transactional
	public void insertLecture(LectureOneResponse lectureOneResponse) throws Exception;
	
	// lecture 기본 정보 수정
	@Transactional
	public void updateFoundationLecture(LectureRequest lecture) throws Exception;
	
	// tag 수정
	@Transactional
	public void insertTags(@Param("commonId") long commonId, @Param("tags") String[] tags) throws Exception;
	
	// lecture content 내용 수정
	@Transactional
	public void updateContentLecture(LectureRequest lecture) throws Exception;
	
	// sub lecture 생성 dao
	@Transactional
	public void insertSubLecture(LectureSubsRequest lecture) throws Exception;
	
	// sub lecture 업데이트 dao
	@Transactional
	public void updateSubLecture(LectureSubsRequest lecture) throws Exception;
	
	// 목차 상세 정보 가져오기
	public LectureSubOneResponse selectOneSubLecture(LectureSubsRequest request) throws Exception;
	
	// 목차 가져오기
	public List<LectureSubIndexResponse> selectSubLectureIndex(@Param("lectureId") long lectureId) throws Exception;
	
	// 강의, 위키 리퀘스트 요청
	@Transactional
	public void insertSubHistory(LectureSubHistoryRequest lecture) throws Exception;
		
	// 같은 종류의 다른 강의들 가져오기
	public List<TheOhterSubLectureResponse> selectTheOtherSubLectures(@Param("subId") long subId, @Param("userId") long userId) throws Exception;
	
	// 공동 관리자 권한 입력
	@Transactional
	public void insertLectureAuth(LectureAuthRequest request) throws Exception;
	
	// 공동 관리자 권한 변경
	@Transactional
	public void updateLectureAuth(LectureAuthRequest request) throws Exception;
	
	// 공동 관리자 제거
	@Transactional
	public void deleteLectureAuth(@Param("authId") long authId) throws Exception;
	
	// 사용자 프로젝트 좋아요 체크, 삽입, 수정
	public LikeDTO checkLikeLectureByUserId(@Param("userId") long userId, @Param("lectureId") long lectureId) throws Exception;
	public void insertLikeLectureByUserId(@Param("userId") long userId, @Param("lectureId") long lectureId) throws Exception;
	public void updateLikeLectureByUserId(@Param("userId") long userId, @Param("lectureId") long lectureId, @Param("likeFlag") String likeFlag) throws Exception;
	
	// 사용자 히스토리 좋아요 체크, 삽입, 수정
	public LikeDTO checkLikeHistoryByUserId(@Param("userId") long userId, @Param("lectureId") long lectureId, @Param("subId") long subId, @Param("subHisId") long subHisId) throws Exception;
	public void insertLikeHistoryByUserId(@Param("userId") long userId, @Param("lectureId") long lectureId, @Param("subId") long subId, @Param("subHisId") long subHisId) throws Exception;
	public void updateLikeHistoryByUserId(@Param("userId") long userId, @Param("lectureId") long lectureId, @Param("subId") long subId, @Param("subHisId") long subHisId, @Param("likeFlag") String likeFlag) throws Exception;
	
	public List<LecturesResponse> selectLectures(@Param("userId") long userId, @Param("startPage") int startPage, @Param("type") int type) throws Exception;
	
	public LectureOneResponse selectLectureByLectureId(@Param("lectureId") long lectureId, @Param("userId") long userId) throws Exception;
	
	public List<TagResponse> selectTags() throws Exception;
	
	public void updateLectureViewCount(@Param("lectureId") long lectureId) throws Exception;
	
	public void insertAuthLecture(@Param("lectureId") long lectureId, @Param("userId") long userId, @Param("role") String role) throws Exception;
	
	public List<LectureRoleUsersResponse> selectRoleUsersByLectureId(@Param("lectureId") long lectureId) throws Exception;
}
