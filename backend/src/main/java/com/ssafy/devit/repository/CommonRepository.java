package com.ssafy.devit.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssafy.devit.model.common.Common;
import com.ssafy.devit.model.common.SearchInfoResponse;

public interface CommonRepository {
	public List<SearchInfoResponse> selectInfomationBySearch(@Param("userId") long userId, @Param("searchText") String searchText, @Param("startPage") int startPage) throws Exception;
	
	public void test(Common common);
	
}
