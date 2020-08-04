package com.ssafy.devit.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssafy.devit.model.lecture.LectureOneResponse;
import com.ssafy.devit.model.lecture.LectureRoleUsersResponse;
import com.ssafy.devit.model.lecture.LecturesResponse;
import com.ssafy.devit.model.lecture.LikeDTO;
import com.ssafy.devit.model.lecture.TagResponse;
import com.ssafy.devit.model.request.LectureRequest;

public interface LectureRepository {
	public void insertCommonId() throws Exception;

	public int selectCommonId() throws Exception;
	
//	public void insertLecture(LectrueRequest lecture) throws Exception;
	
	public void insertLecture(@Param("userId") long userId, @Param("commonId") long commonId) throws Exception;
	
	public LectureOneResponse selectLectureId(@Param("commonId") long commonId) throws Exception;
	
	public void updateFoundationLecture(LectureRequest lecture) throws Exception;
	
	public void updateContentLecture(LectureRequest lecture) throws Exception;
	
	public List<LecturesResponse> selectLectures(@Param("userId") long userId, @Param("startPage") int startPage, @Param("type") int type) throws Exception;
	
	public void insertTags(@Param("commonId") long commonId, @Param("tagName") String tagName) throws Exception;
	
	public int getLectureSubCount() throws Exception;
	
	public LectureOneResponse selectLectureByLectureId(@Param("lectureId") long lectureId, @Param("userId") long userId) throws Exception;
	
	public List<TagResponse> selectTags() throws Exception;
	
	public LikeDTO checkLikeLectureByUserId(@Param("userId") long userId, @Param("lectureId") long lectureId, @Param("likeType") int likeType) throws Exception;
	
	public void updateLikeLectureByUserId(@Param("userId") long userId, @Param("lectureId") long lectureId, @Param("likeType") int likeType, @Param("likeFlag") String likeFlag) throws Exception;
	
	public void insertLikeLectureByUserId(@Param("userId") long userId, @Param("lectureId") long lectureId, @Param("likeType") int likeType) throws Exception;
	
	public void updateLectureViewCount(@Param("lectureId") long lectureId) throws Exception;
	
	public void insertAuthLecture(@Param("lectureId") long lectureId, @Param("userId") long userId, @Param("role") String role) throws Exception;
	
	public List<LectureRoleUsersResponse> selectRoleUsersByLectureId(@Param("lectureId") long lectureId) throws Exception;
}
