package com.ssafy.devit.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Base64;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.devit.model.CommonResponse;
import com.ssafy.devit.model.request.SignUpRequest;
import com.ssafy.devit.model.user.UserAuthDetails;
import com.ssafy.devit.service.MailService;
import com.ssafy.devit.service.UserAuthDetailService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = CommonResponse.class),
		@ApiResponse(code = 403, message = "Forbidden", response = CommonResponse.class),
		@ApiResponse(code = 404, message = "Not Found", response = CommonResponse.class),
		@ApiResponse(code = 500, message = "Failure", response = CommonResponse.class) })
@Controller
@RequestMapping("/api/v1/mail")
public class MailController {
	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	MailService mailService;
	
	@Autowired // 회원 관리
	UserAuthDetailService userAuthDetailService;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@Async
	@GetMapping("/confirmCheck")
	// 이메일인증을 마친 계정인지 확인
	public ResponseEntity<CommonResponse> confirmCheck(@RequestParam("email") String email) throws Exception {
		log.info(">> Load : confirmCheck <<");
		ResponseEntity<CommonResponse> response = null;
		final CommonResponse result = new CommonResponse();

		try {
			int temp = mailService.checkEmailConfirm(email);
			if (temp > 0) {
				result.msg = "success: already confirmed";
				result.result = "이미 인증된 계정입니다.";
				response = new ResponseEntity<>(result, HttpStatus.OK);
				return response;
			} else {
				result.msg = "success: need to confirm";
				result.result = "인증이 필요한 계정입니다..";
				response = new ResponseEntity<>(result, HttpStatus.OK);
				return response;
			}
		} catch (Exception e) {
			log.info(">> Error : emailConfirmMail <<");
			log.info(e.getMessage().toString());
			result.msg = "fail";
			result.result = "계정 인증여부 확인 실패.";
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}

		return response;
	}

	@GetMapping("/sendMailToConfirm")
	// 계정인증을 위한 메일 전송
	public ResponseEntity<CommonResponse> emailConfirmMail(@RequestParam("email_to") String email_to) {
		log.info(">> Load : emailConfirmMail <<");
		ResponseEntity<CommonResponse> response = null;
		final CommonResponse result = new CommonResponse();

		try {
			mailService.sendEmailConfirmMail(email_to);
			result.msg = "success";
			result.result = "성공적으로 이메일 확인 메일이 보내졌습니다.";
			response = new ResponseEntity<>(result, HttpStatus.OK);

		} catch (Exception e) {
			log.info(">> Error : emailConfirmMail <<");
			log.info(e.getMessage().toString());
			result.msg = "fail";
			result.result = "계정 인증 메일 발송 실패.";
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}

		return response;
	}

	@GetMapping("/emailConfirm")
	// 이거 하면 user 테이블의 email_confirm이 'Y'가 됨
	public ResponseEntity<CommonResponse> emailConfirm(@RequestParam("email") String email) {
		log.info(">> Load : emailConfirm <<");
		ResponseEntity<CommonResponse> response = null;
		final CommonResponse result = new CommonResponse();

		try {
			mailService.updateEmailConfirm(email);
			result.msg = "success";
			result.result = "성공적으로 계정인증이 완료되었습니다.";
			response = new ResponseEntity<>(result, HttpStatus.OK);

		} catch (Exception e) {
			log.info(">> Error : updateEmailConfirm <<");
			log.info(e.getMessage().toString());
			result.msg = "fail";
			result.result = "계정 인증 실패.";
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}

		return response;

	}

	@GetMapping("/passwordConfirm")
	// 비밀번호 찾기 요청으로써, 실행되면 비밀번호 찾기 요청을 본인이 한게 맞는지 확인하는 메일이 보내진다.
	// (이거는 프론트에서 하는거 맞겠지?)메일로 보낸 링크를 사용자가 클릭하면 passwordChangeRandom를 실행한다.
	public ResponseEntity<CommonResponse> passwordConfirmMail(@RequestParam("email_to") String email_to) {
		log.info(">> Load : passeordConfirmMail <<");
		ResponseEntity<CommonResponse> response = null;
		final CommonResponse result = new CommonResponse();

		try {
			mailService.sendPasswordFindConfirmMail(email_to);
			result.msg = "success";
			result.result = "성공적으로 비밀번호 변경 확인 메일이 보내졌습니다.";
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			log.info(">> Error : passwordConfirmMail <<");
			log.info(e.getMessage().toString());
			result.msg = "fail";
			result.result = "비밀번호 변경 확인 메일이 발송 실패.";
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}
		return response;
	}

	@GetMapping("/passwordChangeRandom")
	// 임의의 비밀번호로 변경이 되고 그 비밀번호가 메일로 보내진다.
	public ResponseEntity<CommonResponse> passwordChangeRandom(@RequestParam("email_to") String email_to) {
		log.info(">> Load : passeordChange <<");
		ResponseEntity<CommonResponse> response = null;
		final CommonResponse result = new CommonResponse();

		try {
			mailService.updatePasswordRandom(email_to);
			result.msg = "success";
			result.result = "성공적으로 비밀번호가 변경 되었습니다.";
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			log.info(">> Error : passwordChagne <<");
			log.info(e.getMessage().toString());
			result.msg = "fail";
			result.result = "비밀번호 변경 실패.";
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}
		return response;
	}

	@PostMapping("/confirm/auth")
	@ApiOperation(value = "회원가입을 위한 이메일 인증")
	public ResponseEntity<CommonResponse> confirmAuthUser(@RequestBody @Valid SignUpRequest request) {
		log.info(">> Load : confirmAuthUser <<");
		final CommonResponse result = new CommonResponse();
		ResponseEntity<CommonResponse> response = null;
		try {
			mailService.sendConfirmEmail(request);
			result.msg = "success";
			result.result = "성공적으로 이메일 확인 메일이 보내졌습니다.";
			response = new ResponseEntity<>(result, HttpStatus.OK);

		} catch (Exception e) {
			log.info(">> Error : emailConfirmMail <<");
			log.info(e.getMessage().toString());
			result.msg = "fail";
			result.result = "계정 인증 메일 발송 실패.";
			response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}

		return response;
	}

	@GetMapping("/confirm/auth")
	@ApiOperation(value = "회원가입을 위한 이메일 인증")
	public String test(@RequestParam("e") String email, @RequestParam("p") String password,
			@RequestParam("n") String nickname) {
		log.info(">> Load : confirmAuthUser <<");
		final CommonResponse result = new CommonResponse();
		ResponseEntity<CommonResponse> response = null;

		byte[] decodedEmail = Base64.getDecoder().decode(email);
		byte[] decodedPassword = Base64.getDecoder().decode(password);
		byte[] decodedNickname = Base64.getDecoder().decode(nickname);
		String decodedEmail2 = new String(decodedEmail);
		String decodedPassword2 = new String(decodedPassword);
		String decodedNickname2 = new String(decodedNickname);
		System.out.println(decodedEmail2);
		System.out.println(decodedPassword2);
		System.out.println(decodedNickname2);

		try {
			UserAuthDetails user = new UserAuthDetails(decodedEmail2, decodedNickname2,
					passwordEncoder.encode(decodedPassword2));
			userAuthDetailService.signUp(user);
			result.msg = "success";
			result.result = "가입이 완료 되었습니다";
			response = new ResponseEntity<CommonResponse>(result, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:http://i3a101.p.ssafy.io/";
	}

}
