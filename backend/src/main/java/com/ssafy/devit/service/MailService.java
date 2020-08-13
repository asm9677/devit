package com.ssafy.devit.service;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

public interface MailService { // to MailServiceImpl
	
	//public int confirmEmailCode(@Param("email") String email, @Param("code") int code) throws Exception;
	
	public int checkEmailConfirm(String email) throws Exception;
	
	public void sendEmailConfirmMail(String email_to) throws Exception;
	
	public void updateEmailConfirm(String email) throws Exception;
	
	public void sendPasswordFindConfirmMail(String email) throws Exception;
	
	public void updatePasswordRandom(String email) throws Exception;	
	
}
