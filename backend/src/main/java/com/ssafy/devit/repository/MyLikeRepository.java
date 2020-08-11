package com.ssafy.devit.repository;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.ssafy.devit.model.lecture.BoardResponse;
import com.ssafy.devit.model.lecture.LecturesResponse;
import com.ssafy.devit.model.lecture.TheOhterSubLectureResponse;

public interface MyLikeRepository {
	public List<LecturesResponse> myLikeLectureList(@Param("userId") long userId, @Param("startPage") long startPage, @Param("itemsperpage") long itemsperpage) throws Exception;
	public List<TheOhterSubLectureResponse> myLikeVideoList(@Param("userId") long userId, @Param("startPage") long startPage, @Param("itemsperpage") long itemsperpage) throws Exception;
}
