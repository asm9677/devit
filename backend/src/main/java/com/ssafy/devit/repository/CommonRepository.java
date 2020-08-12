package com.ssafy.devit.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssafy.devit.model.common.Common;
import com.ssafy.devit.model.common.SearchInfoIndexResponse;
import com.ssafy.devit.model.common.SearchInfoLectureResponse;
import com.ssafy.devit.model.lecture.MainResponse;

public interface CommonRepository {
	public List<SearchInfoLectureResponse> selectInfomationLectureBySearch(@Param("userId") long userId, @Param("searchText") String[] searchText, @Param("startPage") int startPage) throws Exception;
	
	public List<SearchInfoIndexResponse> selectInfomationIndexBySearch(@Param("userId") long userId, @Param("searchText") String[] searchText, @Param("startPage") int startPage) throws Exception;
	
	public MainResponse getMainStatus();
}
