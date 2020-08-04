package com.ssafy.devit.service;

import org.apache.ibatis.annotations.Param;

public interface MailService {

	public void updateEmailConfirm(String email) throws Exception;
	
	public int confirmEmailCode(@Param("email") String email, @Param("code") int code) throws Exception;
}
