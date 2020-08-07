package com.ssafy.devit.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssafy.devit.model.request.UserProfileUpdateReqeust;
import com.ssafy.devit.model.user.User;
import com.ssafy.devit.model.user.UserResponse;

public interface UserRepository {

	public List<UserResponse> findUsers() throws Exception;
	
	public int fundUserByEmailAndPassword(String eamil, String password) throws Exception;

	public User findUserByUserEmail(String email) throws Exception;

	public User findUserByUserId(long userId) throws Exception;

	public User findUserByNickname(String nickname) throws Exception;

	void signUp(User user) throws Exception;

	public void insertUser(User user) throws Exception;

	public List<String> findRoles(long userId) throws Exception;

	public void insertRole(@Param("userId") long userId, @Param("role") String role) throws Exception;
	
	public List<UserResponse> findUsersByLikeSearchText(@Param("search") String search, @Param("lectureId") long lectureId) throws Exception;
	
	// 사용자 프로필 변경
	public void updateUserInfo(UserProfileUpdateReqeust request) throws Exception; 
}
