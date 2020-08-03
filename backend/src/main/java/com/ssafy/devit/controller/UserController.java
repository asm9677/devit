package com.ssafy.devit.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.devit.model.CommonResponse;
import com.ssafy.devit.service.UserService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
	Logger log = LoggerFactory.getLogger(this.getClass());
	
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
	@GetMapping("/{search}")
	@ApiOperation(value = "입력으로 준 닉네임이 포함 되어있는 회원 정보를 가져옴")
	public ResponseEntity<CommonResponse> getUsersByLikeNickname(@PathVariable String search){
		log.info(">> Load : GetUsersByLikeNickname <<");
		ResponseEntity<CommonResponse> response = null;
		final CommonResponse result = new CommonResponse();
		
		try {
			result.result = userService.getUsersByLikeSearchText(search);
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
	
	@GetMapping("/count")
	@ApiOperation(value = "서비스에 등록된 회원 수 가져옴")
	public ResponseEntity<CommonResponse> getUsersCount(){
		log.info(">> Load : getUsersCount <<");
		ResponseEntity<CommonResponse> response = null;
		final CommonResponse result = new CommonResponse();
		
		try {
			result.result = userService.getUsersCount();
			result.msg = "success";
			response = new ResponseEntity<CommonResponse>(result, HttpStatus.OK);
		} catch (Exception e) {
			result.msg = "fail";
			response = new ResponseEntity<CommonResponse>(result, HttpStatus.BAD_REQUEST);
			log.info(">> Error : getUsersCount <<");
			log.info(e.getMessage().toString());
		}
		return response;
	}
}
