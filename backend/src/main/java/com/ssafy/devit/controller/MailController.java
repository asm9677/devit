package com.ssafy.devit.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ssafy.devit.model.CommonResponse;
import com.ssafy.devit.service.MailService;

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

	@Async
	@GetMapping
	public void sendMail(@RequestParam("email") String email) throws Exception {
		log.info(">> Load : sendMail <<");
		try {
			mailService.updateEmailConfirm(email);
		} catch (Exception e) {
			log.info(">> Error : sendMail <<");
			log.info(e.getMessage().toString());
		}
	}

	@GetMapping("/confirm")
	public String confirmMail(@RequestParam("email") String email, @RequestParam("code") int code) {
		log.info(">> Load : confirmMail <<");
		try {
			if (mailService.confirmEmailCode(email, code) >= 1) {
				// 이메일 인증 완료
				mailService.updateEmailConfirm(email);
			} else {

			}
		} catch (Exception e) {
			log.info(">> Error : confirmMail <<");
			log.info(e.getMessage().toString());
		}
		String projectUrl = "";
		return "redirect:http://www.naver.com";
			
	}
}
