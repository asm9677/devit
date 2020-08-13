package com.ssafy.devit.service;

import java.util.List;

import com.ssafy.devit.model.notice.Notice;
import com.ssafy.devit.model.notice.NoticeResponse;
import com.ssafy.devit.model.request.ReplyUpdateRequest;

public interface NoticeService { // to NoticeServiceImpl
	
	public List<NoticeResponse> getNotice(long user_id) throws Exception;
	
	public void setNoticeRead(long notice_id) throws Exception;
	
}
