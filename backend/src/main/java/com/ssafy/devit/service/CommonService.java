package com.ssafy.devit.service;

import java.util.List;

import com.ssafy.devit.model.common.Common;
import com.ssafy.devit.model.common.SearchInfoResponse;
import com.ssafy.devit.model.lecture.MainResponse;

public interface CommonService {
	public List<SearchInfoResponse> findInfomationBySearch(long userId, String searchText, int startPage) throws Exception;
	public MainResponse getMainStatus() throws Exception;
	public void test(Common common);
}
