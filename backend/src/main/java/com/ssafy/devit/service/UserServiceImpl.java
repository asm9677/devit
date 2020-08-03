package com.ssafy.devit.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ssafy.devit.model.user.User;
import com.ssafy.devit.model.user.UserResponse;
import com.ssafy.devit.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	UserRepository userRepository;

	@Override
	public List<UserResponse> getUsers() throws Exception {
		return userRepository.findUsers();
	}

	// UserDetailsService Eamil로 유저 검색(Security 관련)
	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		log.info(">> loadUserByUsername <<");
		User user = null;
		try {
			user = userRepository.findUserByUserId(Integer.parseInt(userId));
			user.setAuthorities(getAuthorities(user.getUserId()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public User getUserByUserEmail(String email) throws Exception {
		if(email.equals("")) {
			throw new Exception("Eamil의 값이 비어있습니다.");
		}
		return userRepository.findUserByUserEmail(email);
	}

	@Override
	public void registry(User user, String role) throws Exception {
		if(user.getEmail().equals("") || user.getNickname().equals("")) {
			throw new Exception("이메일 또는 닉네임이 비어있습니다.");
		}
		userRepository.insertUser(user);
		user = userRepository.findUserByUserEmail(user.getEmail());
		userRepository.insertRole(user.getUserId(), role);
	}

	@Override
	public List<String> getRoles(long userId) throws Exception {
		return userRepository.findRoles(userId);
	}

	@Override
	public void registRole(long userId, String role) throws Exception {
		userRepository.insertRole(userId, role);
	}

	public Collection<GrantedAuthority> getAuthorities(long userId) throws Exception {
		List<String> stringAuthorities = userRepository.findRoles(userId);
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (String authority : stringAuthorities) {
			authorities.add(new SimpleGrantedAuthority(authority));
		}
		return authorities;
	}

	@Override
	public User getUserByUserId(long userId) throws Exception {
		return userRepository.findUserByUserId(userId);
	}

	@Override
	public void signUp(User user) throws Exception {
		if(user.getEmail() == null && user.getNickname() == null) {
			throw new Exception("이메일 또는 닉네임이 비어있습니다.");
		}
		userRepository.signUp(user);
	}

	@Override
	public User getUserByNickname(String nickname) throws Exception {
		if(nickname.equals("")) {
			throw new Exception("Nickname의 값이 비어있습니다.");
		}
		return userRepository.findUserByNickname(nickname);
	}

	@Override
	public List<UserResponse> getUsersByLikeSearchText(String search, long lectureId) throws Exception {
		return userRepository.findUsersByLikeSearchText(search, lectureId);
	}

	@Override
	public int getUsersCount() throws Exception {
		return userRepository.getUsersCount();
	}
}
