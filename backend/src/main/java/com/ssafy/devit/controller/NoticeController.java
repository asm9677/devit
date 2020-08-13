package com.ssafy.devit.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.devit.model.CommonResponse;
import com.ssafy.devit.model.lecture.BoardResponse;
import com.ssafy.devit.model.notice.Notice;
import com.ssafy.devit.model.notice.NoticeResponse;
import com.ssafy.devit.model.request.BoardUpdateRequest;
import com.ssafy.devit.model.user.User;
import com.ssafy.devit.service.NoticeService;
import com.ssafy.devit.service.UserService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = CommonResponse.class),
		@ApiResponse(code = 403, message = "Forbidden", response = CommonResponse.class),
		@ApiResponse(code = 404, message = "Not Found", response = CommonResponse.class),
		@ApiResponse(code = 500, message = "Failure", response = CommonResponse.class) })

@RestController
@RequestMapping("/api/v1")
public class NoticeController {
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	NoticeService noticeService;
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header") })
	@GetMapping("notice/{userId}")
	@ApiOperation(value = "알림 조회")
	public ResponseEntity<CommonResponse> info() throws Exception {
		log.info(">> notice info <<");
		// PathVariable로 bid를 받아서 해당 게시글을 조회한다.
		ResponseEntity<CommonResponse> response = null;
		final CommonResponse result = new CommonResponse();
		
		try {
			User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
			result.msg = "success";
			result.result = noticeService.getNotice(user.getUserId());
			response = new ResponseEntity<CommonResponse>(result, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.msg = "fail to getNotice";
			System.out.println("fail to get data");
			response = new ResponseEntity<CommonResponse>(result, HttpStatus.BAD_REQUEST);
		} 
		return response;
	}

	@ApiImplicitParams({
		@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header") })
	@PutMapping("/notice")
	@ApiOperation(value = "알림 읽음 처리")
	public ResponseEntity<CommonResponse> setNoticeRead(@RequestParam("notice_id") long noticeId) throws Exception{
		log.info(">> noticeUpdate <<");

		ResponseEntity<CommonResponse> response = null;
		final CommonResponse result = new CommonResponse();
		
		try {
			User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
			noticeService.setNoticeRead(noticeId);
			result.msg = "success";
			
			response = new ResponseEntity<CommonResponse>(result, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.msg = "fail to setNotice";
			System.out.println("fail to set data");
			response = new ResponseEntity<CommonResponse>(result, HttpStatus.BAD_REQUEST);
		} 
		return response;
	}

	
}
