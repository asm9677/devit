package com.ssafy.devit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.devit.model.common.Common;
import com.ssafy.devit.model.common.SearchInfoResponse;
import com.ssafy.devit.repository.CommonRepository;

@Service
public class CommonServiceImpl implements CommonService{

	@Autowired
	CommonRepository commonRepository;
	
	@Override
	public List<SearchInfoResponse> findInfomationBySearch(long userId, String searchText, int startPage) throws Exception {
		if(searchText.equals("") || searchText.length() == 0) {
			throw new Exception("1자 이상 검색해 주세요.");
		}
		startPage = (startPage-1) * 20;
		return commonRepository.selectInfomationBySearch(userId, searchText, startPage);
	}

	@Override
	public void test(Common common) {
		commonRepository.test(common);
	}
	
}
