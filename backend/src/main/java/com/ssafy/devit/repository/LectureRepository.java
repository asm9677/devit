package com.ssafy.devit.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.devit.model.common.Common;
import com.ssafy.devit.model.lecture.LectureOneResponse;
import com.ssafy.devit.model.lecture.LectureRoleUsersResponse;
import com.ssafy.devit.model.lecture.LecturesResponse;
import com.ssafy.devit.model.lecture.LikeDTO;
import com.ssafy.devit.model.lecture.TagResponse;
import com.ssafy.devit.model.request.LectureRequest;

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
	
	public List<LecturesResponse> selectLectures(@Param("userId") long userId, @Param("startPage") int startPage, @Param("type") int type) throws Exception;
	
	public int getLectureSubCount() throws Exception;
	
	public LectureOneResponse selectLectureByLectureId(@Param("lectureId") long lectureId, @Param("userId") long userId) throws Exception;
	
	public List<TagResponse> selectTags() throws Exception;
	
	public LikeDTO checkLikeLectureByUserId(@Param("userId") long userId, @Param("lectureId") long lectureId) throws Exception;
	
	public void updateLikeLectureByUserId(@Param("userId") long userId, @Param("lectureId") long lectureId, @Param("likeFlag") String likeFlag) throws Exception;
	
	public void insertLikeLectureByUserId(@Param("userId") long userId, @Param("lectureId") long lectureId) throws Exception;
	
	public void updateLectureViewCount(@Param("lectureId") long lectureId) throws Exception;
	
	public void insertAuthLecture(@Param("lectureId") long lectureId, @Param("userId") long userId, @Param("role") String role) throws Exception;
	
	public List<LectureRoleUsersResponse> selectRoleUsersByLectureId(@Param("lectureId") long lectureId) throws Exception;
}
