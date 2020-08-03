package com.ssafy.devit.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.devit.config.security.JwtTokenProvider;
import com.ssafy.devit.model.CommonResponse;
import com.ssafy.devit.model.request.LoginRequest;
import com.ssafy.devit.model.request.SignUpRequest;
import com.ssafy.devit.model.user.User;
import com.ssafy.devit.service.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = CommonResponse.class),
		@ApiResponse(code = 403, message = "Forbidden", response = CommonResponse.class),
		@ApiResponse(code = 404, message = "Not Found", response = CommonResponse.class),
		@ApiResponse(code = 500, message = "Failure", response = CommonResponse.class) })
@RestController
@RequestMapping("/api/v1/account")
public class AccountController {
	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	UserService userService;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	JwtTokenProvider jwtTokenProvider;

	@PostMapping("/login")
	@ApiOperation(value = "로그인")
	public ResponseEntity<CommonResponse> login(@RequestBody LoginRequest request) {
		log.info(">> loadLogin <<");
		ResponseEntity<CommonResponse> response = null;
		final CommonResponse result = new CommonResponse();

		// 사용자가 입력한 이메일로 DB검색
		User user;
		try {
			user = userService.getUserByUserEmail(request.getEmail());
			// user 정보가 조회 됐다면
			if (user != null) {
				// 비밀번호 매칭
				if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
					result.msg = "fail";
					response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
				} else {
					result.msg = "success";
					result.result = jwtTokenProvider.createToken(user.getUserId() + "",
							userService.getRoles(user.getUserId()));
					response = new ResponseEntity<>(result, HttpStatus.OK);
				}
			} else {
				result.msg = "fail";
				response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			log.debug(">> Error : login <<");
			e.printStackTrace();
		}
		return response;
	}

	@PostMapping("/signup")
	@ApiOperation(value = "가입하기")
	public ResponseEntity<CommonResponse> signUp(@RequestBody SignUpRequest request) {
		log.info(">> loadSignup <<");
		final CommonResponse result = new CommonResponse();
		ResponseEntity<CommonResponse> response = null;
		// DB에 user Email이 있다면 그에 해당하는 user 정보를 / 그렇지 않을 경우 null 반환
		User user = null;
		try {
			// email, nickname 중복 체크
			if (checkUser(request.getEmail(), request.getNickname())) {
				user = new User(request.getEmail(), request.getNickname(),
						passwordEncoder.encode(request.getPassword()));
				userService.registry(user, "ROLE_USER");
				result.msg = "success";
				response = new ResponseEntity<CommonResponse>(result, HttpStatus.CREATED);
			} else {
				// if 중복되는 계정이 있다면 중복
				result.msg = "duplicate";
				response = new ResponseEntity<CommonResponse>(result, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			log.debug(">> Error : signUp <<");
			log.debug(e.getMessage().toString());
			result.msg = "fail";
			response = new ResponseEntity<CommonResponse>(result, HttpStatus.BAD_REQUEST);
		}
		return response;
	}

	@GetMapping("/valid/{nickname}")
	@ApiOperation(value = "닉네임 중복 체크")
	public ResponseEntity<CommonResponse> checkForNickName(@PathVariable String nickname) {
		log.info(">> loadCheckForNickName <<");
		final CommonResponse result = new CommonResponse();
		ResponseEntity<CommonResponse> response = null;

		User user = null;
		try {
			if (userService.getUserByNickname(nickname) == null) {
				result.msg = "success";
				response = new ResponseEntity<CommonResponse>(result, HttpStatus.CREATED);
			} else {
				result.msg = "duplicate";
				response = new ResponseEntity<CommonResponse>(result, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			log.debug(">> Error : checkForNickName <<");
			log.debug(e.getMessage().toString());
			result.msg = "fail";
			response = new ResponseEntity<CommonResponse>(result, HttpStatus.BAD_REQUEST);
		}
		return response;
	}

	public boolean checkUser(String email, String nickname) throws Exception {
		return userService.getUserByUserEmail(email) == null && userService.getUserByNickname(nickname) == null;
	}
}