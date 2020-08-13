package com.ssafy.devit.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssafy.devit.model.notice.NoticeResponse;

public interface NoticeRepository { // to Notice.xml	
	
	public List<NoticeResponse> getNoticeList(@Param("userId") long userId) throws Exception;

	public void setNoticeRead(long noticeId) throws Exception;
}
