package com.ssafy.devit.service;

import java.util.List;
import java.util.Random;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.devit.model.notice.NoticeResponse;
import com.ssafy.devit.repository.BoardRepository;
import com.ssafy.devit.repository.NoticeRepository;

@Service
public class NoticeServiceImpl implements NoticeService { // to NoticeRepository
	
	@Autowired
	NoticeRepository noticeRepository;
	
	@Override	
		
	public List<NoticeResponse> getNotice(long user_id) throws Exception {
		if(user_id < 1) {
			throw new Exception("잘못된 type이 나왔습니다.");
		}

		return noticeRepository.getNoticeList(user_id);
		
	}
	
	public void setNoticeRead(long notice_id) throws Exception {
		noticeRepository.setNoticeRead(notice_id);
	}
		
	
}
