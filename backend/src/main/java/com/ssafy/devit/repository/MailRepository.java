package com.ssafy.devit.repository;

import org.apache.ibatis.annotations.Param;

public interface MailRepository {
	public void updateEmailConfirm(@Param("email") String email) throws Exception;
	
	public void updateCode(@Param("email") String email, @Param("code") int code) throws Exception;
	
	public int confirmEmailCode(@Param("email") String email, @Param("code") int code) throws Exception;
}
