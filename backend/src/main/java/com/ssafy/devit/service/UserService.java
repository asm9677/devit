package com.ssafy.devit.service;

import java.util.List;

import com.ssafy.devit.model.user.User;
import com.ssafy.devit.model.user.UserResponse;

public interface UserService {
	public List<UserResponse> getUsers() throws Exception;

	public User getUserByUserEmail(String email) throws Exception;

	public User getUserByUserId(long userId) throws Exception;

	public User getUserByNickname(String nickname) throws Exception;

	public void registry(User user, String role) throws Exception;

	public List<String> getRoles(long userId) throws Exception;

	public void registRole(long userId, String role) throws Exception;

	public void signUp(User user) throws Exception;
	
	public List<UserResponse> getUsersByLikeSearchText(String search,  long lectureId) throws Exception;
	
	public int getUsersCount() throws Exception;
}
