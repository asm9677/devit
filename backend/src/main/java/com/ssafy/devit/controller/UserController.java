package com.ssafy.devit.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.devit.model.CommonResponse;
import com.ssafy.devit.model.request.UserProfileUpdateReqeust;
import com.ssafy.devit.service.UserService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	UserService userService;

	@ApiImplicitParams({
			@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header") })
	@GetMapping()
	@ApiOperation(value = "회원의 모든 정보를 가져옴")
	public ResponseEntity<CommonResponse> getUsers() {
		log.info(">> Load : getUsers <<");
		ResponseEntity<CommonResponse> response = null;
		final CommonResponse result = new CommonResponse();

		try {
			result.result = userService.getUsers();
			result.msg = "success";
			response = new ResponseEntity<CommonResponse>(result, HttpStatus.OK);
		} catch (Exception e) {
			result.msg = "fail";
			response = new ResponseEntity<CommonResponse>(result, HttpStatus.BAD_REQUEST);
			log.info(">> Error : getUsers <<");
			log.info(e.getMessage().toString());
		}
		return response;
	}

	@ApiImplicitParams({
			@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header") })
	@ApiOperation(value = "단일 회원의 정보를 가져옴")
	@GetMapping("/user")
	public ResponseEntity<CommonResponse> getUserByUserId() {
		log.info(">> Load : getUserByUserId <<");
		ResponseEntity<CommonResponse> response = null;
		final CommonResponse result = new CommonResponse();

		try {
			result.result = userService.getUserByUserId();
			result.msg = "success";
			response = new ResponseEntity<CommonResponse>(result, HttpStatus.OK);
		} catch (Exception e) {
			result.msg = "fail";
			response = new ResponseEntity<CommonResponse>(result, HttpStatus.BAD_REQUEST);
			log.info(">> Error : getUserByUserId <<");
			log.info(e.getMessage().toString());
		}
		return response;
	}

	@ApiImplicitParams({
			@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header") })
	@GetMapping("/{lectureid}")
	@ApiOperation(value = "입력으로 준 닉네임이 포함 되어있는 회원 정보를 가져옴")
	public ResponseEntity<CommonResponse> getUsersByLikeNickname(@PathVariable("lectureid") long lectureId,
			@RequestParam("search") String search) {
		log.info(">> Load : GetUsersByLikeNickname <<");
		ResponseEntity<CommonResponse> response = null;
		final CommonResponse result = new CommonResponse();

		try {
			result.result = userService.getUsersByLikeSearchText(search, lectureId);
			result.msg = "success";
			response = new ResponseEntity<CommonResponse>(result, HttpStatus.OK);
		} catch (Exception e) {
			result.msg = "fail";
			response = new ResponseEntity<CommonResponse>(result, HttpStatus.BAD_REQUEST);
			log.info(">> Error : getUsersByLikeNickname <<");
			log.info(e.getMessage().toString());
		}
		return response;
	}

	@ApiImplicitParams({
			@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header") })
	@PutMapping()
	@ApiOperation(value = "회원정보 수정")
	public ResponseEntity<CommonResponse> modifyUserInfo(@RequestBody UserProfileUpdateReqeust request) {
		log.info(">> Load : GetUsersByLikeNickname <<");
		ResponseEntity<CommonResponse> response = null;
		final CommonResponse result = new CommonResponse();

		try {
			if("nickname".equals(request.getModifyType())) {
				//닉네임 중복검사
				
			}else if("profile".equals(request.getModifyType())) {
				
			}else if("password".equals(request.getModifyType())) {
				//기존 비밀번호 일치여부 확인
				
				request.setNewPassword(passwordEncoder.encode(request.getNewPassword()));
			}
			userService.modifyUserInfo(request);
			result.msg = "success";
			result.result = "성공적으로 변경 되었습니다";
			response = new ResponseEntity<CommonResponse>(result, HttpStatus.OK);
		} catch (Exception e) {
			result.msg = "fail";
			response = new ResponseEntity<CommonResponse>(result, HttpStatus.BAD_REQUEST);
			log.info(">> Error : getUsersByLikeNickname <<");
			log.info(e.getMessage().toString());
		}
		return response;
	}
}
