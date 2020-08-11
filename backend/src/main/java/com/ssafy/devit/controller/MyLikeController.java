package com.ssafy.devit.controller;

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
import com.ssafy.devit.model.request.BoardRequest;
import com.ssafy.devit.model.request.BoardUpdateRequest;
import com.ssafy.devit.model.request.BoardUploadRequest;
import com.ssafy.devit.model.request.BoardWithLectureRequest;
import com.ssafy.devit.model.user.User;
import com.ssafy.devit.model.user.UserAuthDetails;
import com.ssafy.devit.model.user.UserResponse;
import com.ssafy.devit.service.MyLikeService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = CommonResponse.class),
		@ApiResponse(code = 403, message = "Forbidden", response = CommonResponse.class),
		@ApiResponse(code = 404, message = "Not Found", response = CommonResponse.class),
		@ApiResponse(code = 500, message = "Failure", response = CommonResponse.class) })

@RestController
@RequestMapping("/api/v1")
public class MyLikeController {
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	MyLikeService myLikeService;
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header") })
	@GetMapping("myLikeLecture")
	@ApiOperation(value = "좋아요한 프로젝트 목록 조회")
	public ResponseEntity<CommonResponse> myLikeLectureList(@RequestParam("page") int startPage, @RequestParam("itemsperpage") int itemsperpage) throws Exception {
		
		log.info(">> my like lecture list info <<");
		
		ResponseEntity<CommonResponse> response = null;
		final CommonResponse result = new CommonResponse();
		try {
			
			
			result.msg = "success";
			result.result = myLikeService.myLikeLectureList(startPage, itemsperpage);
			response = new ResponseEntity<CommonResponse>(result, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}
	
	@ApiImplicitParams({
		@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header") })
	@GetMapping("myLikeVideo")
	@ApiOperation(value = "좋아요한 동영상 목록 조회")
	public ResponseEntity<CommonResponse> myLikeVideoList(@RequestParam("page") int startPage, @RequestParam("itemsperpage") int itemsperpage) throws Exception {
		
		log.info(">> my like video list info <<");
		
		ResponseEntity<CommonResponse> response = null;
		final CommonResponse result = new CommonResponse();
		try {
			
			
			result.msg = "success";
			result.result = myLikeService.myLikeVideoList(startPage, itemsperpage);
			response = new ResponseEntity<CommonResponse>(result, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}
}
